package it.polimi.ingsw.network;

import it.polimi.ingsw.network.message.Message;

import java.io.IOException;

/**
 * Interface that represent the basic function of the connection, both server and client side
 */

public interface ConnectionInterface {

    /**
     * Check if the connection is active
     * @return true if the connection is active, false otherwise
     */

    boolean isConnectionActive();

    /**
     *
     * @param message
     */

    void sendMessage(Message message);

    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */

    Message receiveMessage() throws IOException, ClassNotFoundException;

}
