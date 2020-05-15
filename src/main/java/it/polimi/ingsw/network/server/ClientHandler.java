package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.message.Message;
import it.polimi.ingsw.network.message.MessageSubType;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.utils.ConfigLoader;
import it.polimi.ingsw.utils.ConstantsContainer;
import it.polimi.ingsw.utils.LobbyTimerTask;
import it.polimi.ingsw.utils.ServerPingTimerTask;
import it.polimi.ingsw.view.server.VirtualView;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler implements Runnable, ConnectionInterface {

    private ObjectInputStream objectIn;
    private ObjectOutputStream objectOut;

    private final Object inputLock = new Object();
    private final Object outputLock = new Object();

    private final Socket socket;
    private final Server server;
    private boolean isConnectionActive;
    private VirtualView view;
    private Timer lobbyTimer;
    private Timer pingTimer;
    private int newNickCounter;

    private String userID = ConstantsContainer.USERDIDDEF;
    private String nickName = ConstantsContainer.NICKDEF;

    public ClientHandler(Server server, Socket socket){
        this.socket = socket;
        this.server = server;
        this.isConnectionActive = true;

    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isConnectionActive() {
        return isConnectionActive;
    }

    public void setConnectionActive(boolean connectionActive) {
        isConnectionActive = connectionActive;
    }

    public VirtualView getView() {
        return view;
    }

    public void setView(VirtualView view) {
        this.view = view;
    }

    public synchronized void sendMessage(Message msg){                                  //fare un'altra funzione per mandare in asincrono i messaggi
      synchronized (outputLock) {
          try {
              objectOut.writeObject(msg);
              objectOut.flush();
              objectOut.reset();

          } catch (IOException e) {
              Server.LOGGER.severe(e.getMessage());
          }
      }

    }

    public Message receiveMessage() throws IOException, ClassNotFoundException {
        try{
            return (Message) objectIn.readObject();
        }
        catch (IOException e){
            throw new IOException();
        }
        catch (ClassNotFoundException c){
            throw new ClassNotFoundException();
        }
    }

    public void closeConnection(Message message){
        sendMessage(message);
        server.removeFromConnections(this);
        close();
    }

    public void close(){
        try{
            objectIn.close();
            objectOut.close();
            socket.close();
        }catch (IOException e){
            Server.LOGGER.severe(e.getMessage());
        }
    }

    public void closeAfterDisconnection()
    {
        server.removeFromConnections(this);
        try{
            socket.close();
        }catch (IOException e){
            Server.LOGGER.severe(e.getMessage());
        }
    }

    public void closeConnectionFromServer(){
        sendMessage(new Message(ConstantsContainer.SERVERNAME,MessageType.DISCONNECTION,MessageSubType.SERVERSTOPPED));
        close();
    }

    public void dispatchMessageToVirtualView(Message message){
        view.processMessageReceived(message);
    }

    public void startLobbyTimer(){
        lobbyTimer = new Timer();
        LobbyTimerTask task = new LobbyTimerTask(server,this,userID,nickName);
        lobbyTimer.schedule(task, (long) ConfigLoader.getLobbyTimer() * 1000);
    }

    public void stopLobbyTimer(){
        lobbyTimer.cancel();

    }
    public void startPingTimer(){
        pingTimer = new Timer();
        ServerPingTimerTask task = new ServerPingTimerTask(server,this,userID,nickName);
        pingTimer.schedule(task, (long) ConfigLoader.getPingTimer() * 1000);
    }

    public void stopPingTimer(){
        pingTimer.cancel();
    }


    public void ping(){
        sendMessage(new Message(ConstantsContainer.SERVERNAME,MessageType.PING,MessageSubType.UPDATE));
    }

    public void TurnTimerEnded(Message message){
        server.handleDisconnection(userID,this,message);
    }


    @Override
    public void run() {

            try{
                this.objectOut = new ObjectOutputStream(socket.getOutputStream());
                this.objectIn = new ObjectInputStream(socket.getInputStream());
                startLobbyTimer();
                startPingTimer();
                while(isConnectionActive()) {
                    synchronized (inputLock) {
                        Message input = receiveMessage();

                        if (input.getType() == MessageType.CONFIG && input.getSubType() == MessageSubType.ANSWER) {
                            stopLobbyTimer();
                            this.newNickCounter = 0;
                            server.insertPlayerInGame(input, this, true);
                            server.moveGameStarted();
                        } else if (input.getType() == MessageType.PING) {
                            stopPingTimer();
                            startPingTimer();
                        } else if (input.getType() == MessageType.CONFIG && input.getSubType() == MessageSubType.UPDATE) {
                            stopLobbyTimer();
                            newNickCounter++;
                            if (newNickCounter > ConstantsContainer.MAXTRYTOCHANGENICK) {
                                input.setMessageSubType(MessageSubType.ANSWER);
                                this.newNickCounter = 0;
                                server.handleDisconnection(userID, this, new Message(input.getSender(), input.getNickName(), MessageType.DISCONNECTION, MessageSubType.NICKMAXTRY)); //si deve cambiare
                                server.insertPlayerInGame(input, this, false);
                            } else {
                                dispatchMessageToVirtualView(input);
                            }
                            server.moveGameStarted();
                        } else if ((input.getType() == MessageType.DISCONNECTION)) {
                            server.handleDisconnection(userID, this, input);

                            if (!input.getSubType().equals(MessageSubType.BACK))
                                break;


                            //completare questo
                            //confermare la ricezione del messaggio, è giò fatto
                            //due casi diversi se si disconette prima di aver iniziato la partita o dopo aver iniziato la partita, nel primo si deve chiudere l'app
                            //nel secondo si deve richiedere se si vuole iniziare una nuova parita
                            //terminare la partita
                        } else {
                            dispatchMessageToVirtualView(input); //runnarlo in un altro thread?
                        }

                    }
                }

            }catch (IOException e){
                stopLobbyTimer();
                stopPingTimer();
                isConnectionActive = false;         //sfruttare questo per chiudere la connection, e inviare o no il messaggio. fare messaggi personallzzati
                server.handleDisconnection(userID,this,new Message(userID,nickName,MessageType.DISCONNECTION,MessageSubType.ERROR));
            }
            catch(ClassNotFoundException c){
                Server.LOGGER.severe(c.getMessage());
            }
            finally {
                stopLobbyTimer();
                stopPingTimer();
                if(isConnectionActive)
                    closeConnection(new Message(ConstantsContainer.SERVERNAME, MessageType.DISCONNECTION, MessageSubType.UPDATE));      //vedere se viene chiamata due volte quando scade il lobby timer
                else
                    closeAfterDisconnection();
                Server.LOGGER.info("player disconnected");
            }
        }

        //aggiungere una funziona per pingare il client, se non risponde eliminarlo e far terminare la partita. forse nel server
}
