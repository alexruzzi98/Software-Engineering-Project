package it.polimi.ingsw.view.client.gui;

import it.polimi.ingsw.model.Response;
import it.polimi.ingsw.model.map.Building;
import it.polimi.ingsw.model.map.Square;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.network.message.MessageType;
import it.polimi.ingsw.utils.Observable;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static it.polimi.ingsw.view.client.gui.Gui.LOGGER;
import static it.polimi.ingsw.view.client.gui.EliminateListeners.*;

public class Board extends Observable {
    Gui gui;
    List<Player> allPlayer = new ArrayList<>();
    List<Player> otherPlayers = new ArrayList<>();
    List<String> cardsChosen = new ArrayList<>();
    List<String> godCards = new ArrayList<>();
    String cardChosen = null;
    String firstPlayer = null;
    JFrame f;
    JDesktopPane desktopPane;
    JDesktopPane challengerChoiceCards;
    JDesktopPane waitChallenger;
    JDesktopPane challengerChoiceFirstPlayer;
    JDesktopPane youChosen;
    JDesktopPane chooseCard;
    JDesktopPane placeWorkers;
    JDesktopPane startTurn;
    JDesktopPane updateBoard;
    JInternalFrame frameChat = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameChallenger1 = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameChallenger2 = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameChooseCards = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFramePlaceWorkers = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameStartTurn = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameUpdateBoard = new JInternalFrame("", false, false, false, false);
    JWindow windowPower;
    JScrollPane scrollPane;
    JButton buttonLv1 = new JButton();
    JButton buttonLv2 = new JButton();
    JButton buttonLv3 = new JButton();
    JButton buttonDome = new JButton();
    JButton buttonMove = new JButton();
    JButton buttonBuild = new JButton();
    JButton buttonPower = new JButton();
    JButton buttonChooseCards = new JButton();
    JButton buttonChooseFirst = new JButton();
    JButton buttonChoosePower = new JButton();
    JButton buttonChat = new JButton();
    JButton buttonEndturn = new JButton();
    JButton buttonMultiUse = new JButton();
    JButton buttonExit = new JButton();
    JButton backgroundFrameChat = new JButton();
    JButton sfondoFramePower = new JButton();
    JButton leftBoard = new JButton();
    JButton leftGod = new JButton();
    JButton opponent1 = new JButton();
    JButton opponent2;
    JTextArea chat = new JTextArea();
    JTextField field = new JTextField();
    private final JButton[] mapButtons = new JButton[25];
    int[] mapButtonslvl = new int[25];
    int[] mapMyWorkers = new int[25];
    boolean[] mapButtonsPlayer = new boolean[25];
    static JLabel playerpower = new JLabel();
    JLabel nicknameLabel = new JLabel();
    JLabel nicknameLabel1 = new JLabel();
    JLabel coverBoard;
    JLabel coverChat;
    JLabel coverBackground;
    JLabel coverLeftBoard;
    JLabel coverLeftGod;
    JLabel background;
    JLabel opponents = new JLabel("Opponents:");
    JLabel worker;
    JLabel workerOpponents1;
    JLabel workerOpponents2;
    JLabel workerCyan;
    JLabel workerWhite;
    JLabel workerPurple;
    JLabel lvl1;
    JLabel lvl2;
    JLabel lvl3;
    JLabel lvl1Building;
    JLabel lvl2Building;
    JLabel lvl3Building;
    JLabel domeBuilding;
    JLabel lvl1Worker;
    JLabel lvl2Worker;
    JLabel lvl3Worker;
    JLabel lvl1WorkerOpponents1;
    JLabel lvl2WorkerOpponents1;
    JLabel lvl3WorkerOpponents1;
    JLabel lvl1WorkerOpponents2;
    JLabel lvl2WorkerOpponents2;
    JLabel lvl3WorkerOpponents2;
    JLabel lvl1Cyan;
    JLabel lvl2Cyan;
    JLabel lvl3Cyan;
    JLabel lvl1Purple;
    JLabel lvl2Purple;
    JLabel lvl3Purple;
    JLabel lvl1White;
    JLabel lvl2White;
    JLabel lvl3White;
    JLabel lvl1Dome;
    JLabel lvl2Dome;
    JLabel lvl3Dome;
    JLabel dome;
    JLabel exit;
    JLabel lButtonMove;
    JLabel lButtonBuild;
    JLabel lButtonMovePress;
    JLabel lButtonBuildPress;
    JLabel lButtonPower;
    JLabel lButtonPowerPress;
    JLabel lButtonChat;
    JLabel lButtonChatPress;
    JLabel labelChooseCards = new JLabel("Choose Cards");
    JLabel lButtonChooseCards;
    JLabel lButtonChooseCardsPress;
    JLabel labelChooseFirst = new JLabel("Choose the first player");
    JLabel lbuttonChooseFirst;
    JLabel lbuttonChooseFirstPress;
    JLabel labelChoosePower = new JLabel("Choose your Power");
    JLabel lButtonChoosePower;
    JLabel lButtonChoosePowerPress;
    JLabel labelConfirmPlace = new JLabel("Confirm your positions");
    JLabel labelSeePower = new JLabel("See your power");
    JLabel labelEndturn = new JLabel("End Turn");
    JLabel labelChooseWorker = new JLabel("Choose the worker");
    JLabel labelMove = new JLabel("Move");
    JLabel labelBuild = new JLabel("Build");
    JLabel lbuttonEndturn;
    JLabel lbuttonEndturnPress;
    Dimension frameSize = new Dimension();
    Dimension boardSize = new Dimension();
    Dimension bottomSize = new Dimension();
    Dimension sideSize = new Dimension();
    Dimension buttonMapSize13x13 = new Dimension();
    Dimension scrollSize = new Dimension();
    Dimension labelMapSize = new Dimension();
    Dimension internalFrameSize90x90 = new Dimension();
    Dimension internalFrameSize2 = new Dimension();
    Dimension internalFrameSize40x45 = new Dimension();
    Dimension buttonSize7x7 = new Dimension();
    Dimension buttonSize5x5 = new Dimension();
    Dimension size20x5 = new Dimension();
    Font felixSmall;
    Font felixNormal;
    Font felixBold;
    static final String PALETTE = "JInternalFrame.isPalette";
    Player mePlayer;
    String nickname;
    String nameChoosing;
    int numberOfPlayers = 2;
    it.polimi.ingsw.model.player.Color myColor;
    it.polimi.ingsw.model.player.Color colorOpponent1;
    it.polimi.ingsw.model.player.Color colorOpponent2;
    int placed = 0;
    List<Integer> avaiableWorkers = new ArrayList<>();
    List<Integer> avaiableWorkersPositions = new ArrayList<>();
    List<Integer> avaiableMovePositions = new ArrayList<>();
    List<Integer> avaiableBuildPositions = new ArrayList<>();
    int workerChoosen = 0;
    int tileWorkerChosen = 0;
    Color selectWorkerBorder = Color.CYAN;
    Color selectedWorkerBorder = Color.RED;
    Color moveBorder = Color.WHITE;
    Color buildBorder = Color.WHITE;
    int worker1 = 0;
    int worker2 = 0;
    Response responce = null;
    List<JLabel> myLabels = new ArrayList<>();
    List<JLabel> opponents1Labels = new ArrayList<>();
    List<JLabel> opponents2Labels = new ArrayList<>();

    public void show(Gui instance, Dimension screen, Integer numberOfPlayer, List<Player> players,List<Player> players2, String nickname) throws IOException {

        f = new JFrame();
        gui = instance;
        nicknameLabel.setText("Nickname: ");
        nicknameLabel1.setText(nickname);
        this.nickname = nickname;
        numberOfPlayers = numberOfPlayer;
        allPlayer = players;
        otherPlayers = players2;
        mePlayer = pickNickFromPlayers();
        removeNickFromOtherPlayers();
        myColor = mePlayer.getColor();
        colorOpponent1 = otherPlayers.get(0).getColor();

        double ratio= (screen.getWidth()/screen.getHeight());
        int width = (int) ((screen.getWidth() * 95 / 100) * (1.689999 / ratio));
        int height = (int) (screen.getHeight() * 91 / 100);
        frameSize.setSize(width, height);
        internalFrameSize90x90.setSize(frameSize.width * 90/100, frameSize.height * 90/100);
        internalFrameSize2.setSize(internalFrameSize90x90.width * 70/100, internalFrameSize90x90.height * 70/100);
        internalFrameSize40x45.setSize(frameSize.width * 40/100, frameSize.height * 45/100);


        boardSize.setSize(frameSize.height * 80/100, frameSize.height * 80/100);
        bottomSize.setSize(frameSize.width, frameSize.height * 20/100);
        sideSize.setSize(width * 27/100, height);

        scrollSize.setSize(sideSize.getWidth() * 14/100 , sideSize.getHeight() * 28/100);
        labelMapSize.setSize(height * 13/100, height * 13/100);
        buttonMapSize13x13.setSize(frameSize.height * 13/100, frameSize.height * 13/100);
        buttonSize7x7.setSize(frameSize.width * 7/100, frameSize.height * 7/100);
        buttonSize5x5.setSize(frameSize.width * 5/100, frameSize.width * 5/100);
        size20x5.setSize(frameSize.width * 20/100, frameSize.width * 5/100);

        JLabel coverBoard = ImageHandler.setImage("resources/Graphics/board.png", 100, 100, width, height);
        this.coverBoard = new JLabel(coverBoard.getIcon());

        JLabel coverChat = ImageHandler.setImage("resources/Graphics/panel_chat.png", 100, 100, sideSize.width, sideSize.height);
        this.coverChat = new JLabel(coverChat.getIcon());

        coverBackground = ImageHandler.setImage("resources/Graphics/background2.png", 100, 100, internalFrameSize40x45.width, internalFrameSize40x45.height);
        background = new JLabel(coverBackground.getIcon());

        JLabel coverLeftBoard = ImageHandler.setImage("resources/Graphics/left_board.png", 100, 100, frameSize.width, frameSize.height);
        this.coverLeftBoard = new JLabel(coverLeftBoard.getIcon());

        JLabel coverLeftGod = ImageHandler.setImage("resources/Graphics/left_god_board.png", 100, 100, frameSize.width, frameSize.height);
        this.coverLeftGod = new JLabel(coverLeftGod.getIcon());

        felixSmall = new Font(Gui.FELIX, Font.PLAIN, (int) (13 * screen.getHeight() / 1080));
        felixNormal = new Font(Gui.FELIX, Font.PLAIN, (int) (20 * screen.getHeight() / 1080));
        felixBold = new Font(Gui.FELIX, Font.BOLD, (int) (25 * screen.getHeight() / 1080));


        workerCyan = ImageHandler.setImage("resources/Graphics/worker_cyan.png", 100, 100, labelMapSize.width, labelMapSize.height);
        workerWhite = ImageHandler.setImage("resources/Graphics/worker_white.png", 100, 100, labelMapSize.width, labelMapSize.height);
        workerPurple = ImageHandler.setImage("resources/Graphics/worker_purple.png", 100, 100, labelMapSize.width, labelMapSize.height);
        lvl1 = ImageHandler.setImage("resources/Graphics/lvl1.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2 = ImageHandler.setImage("resources/Graphics/lvl2.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3 = ImageHandler.setImage("resources/Graphics/lvl3.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1Building = ImageHandler.setImage("resources/Graphics/lvl1_building.png", 100, 100, buttonSize5x5.width, buttonSize5x5.height);
        lvl2Building = ImageHandler.setImage("resources/Graphics/lvl2_building.png", 100, 100, buttonSize5x5.width, buttonSize5x5.height);
        lvl3Building = ImageHandler.setImage("resources/Graphics/lvl3_building.png", 100, 100, buttonSize5x5.width, buttonSize5x5.height);
        domeBuilding = ImageHandler.setImage("resources/Graphics/dome_building.png", 100, 100, buttonSize5x5.width, buttonSize5x5.height);
        lvl1Cyan = ImageHandler.setImage("resources/Graphics/lvl1_cyan.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2Cyan = ImageHandler.setImage("resources/Graphics/lvl2_cyan.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3Cyan = ImageHandler.setImage("resources/Graphics/lvl3_cyan.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1Purple = ImageHandler.setImage("resources/Graphics/lvl1_purple.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2Purple = ImageHandler.setImage("resources/Graphics/lvl2_purple.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3Purple = ImageHandler.setImage("resources/Graphics/lvl3_purple.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1White = ImageHandler.setImage("resources/Graphics/lvl1_white.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2White = ImageHandler.setImage("resources/Graphics/lvl2_white.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3White = ImageHandler.setImage("resources/Graphics/lvl3_white.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1Dome = ImageHandler.setImage("resources/Graphics/lvl1_dome.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2Dome = ImageHandler.setImage("resources/Graphics/lvl2_dome.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3Dome = ImageHandler.setImage("resources/Graphics/lvl3_dome.png", 85, 85, labelMapSize.width, labelMapSize.height);
        dome = ImageHandler.setImage("resources/Graphics/dome.png", 85, 85, buttonSize5x5.width, buttonSize5x5.height);
        exit = ImageHandler.setImage("resources/Graphics/exit.png", 75, 75, sideSize.width * 12/100, sideSize.width * 12/100);
        lButtonBuild = ImageHandler.setImage("resources/Graphics/button_build.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonMove = ImageHandler.setImage("resources/Graphics/button_move.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonPower = ImageHandler.setImage("resources/Graphics/button_power.png", 100, 100, frameSize.width * 5/100, frameSize.height * 5/100);
        lButtonChat = ImageHandler.setImage("resources/Graphics/button_chat.png", 100, 100, frameSize.width * 5/100, frameSize.height * 7/100);
        lButtonChooseCards = ImageHandler.setImage("resources/Graphics/button_choose_cards.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lbuttonChooseFirst = ImageHandler.setImage("resources/Graphics/button_choose_first.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonChoosePower = ImageHandler.setImage("resources/Graphics/button_power.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lbuttonEndturn = ImageHandler.setImage("resources/Graphics/button_endturn.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonBuildPress = ImageHandler.setImage("resources/Graphics/button_build_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonMovePress = ImageHandler.setImage("resources/Graphics/button_move_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonPowerPress = ImageHandler.setImage("resources/Graphics/button_power_press.png", 100, 100, frameSize.width * 5/100, frameSize.height * 5/100);
        lButtonChatPress = ImageHandler.setImage("resources/Graphics/button_chat_press.png", 100, 100, frameSize.width * 5/100, frameSize.height * 7/100);
        lButtonChooseCardsPress = ImageHandler.setImage("resources/Graphics/button_choose_cards_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lbuttonChooseFirstPress = ImageHandler.setImage("resources/Graphics/button_choose_first_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonChoosePowerPress = ImageHandler.setImage("resources/Graphics/button_power_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lbuttonEndturnPress = ImageHandler.setImage("resources/Graphics/button_endturn_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);

        setMyColorWorkers();
        setColorWorkers1();

        windowPower = new JWindow();
        windowPower.setBounds((int)(frameSize.width * 35.5/100), (int) (frameSize.height * 37/100), internalFrameSize40x45.width, internalFrameSize40x45.height);

        setInternalFrames(internalFrameChallenger1);
        BasicInternalFrameUI bi = (BasicInternalFrameUI) internalFrameChallenger1.getUI();
        bi.setNorthPane(null);
        setInternalFrames(internalFrameChallenger2);
        BasicInternalFrameUI bi2 = (BasicInternalFrameUI) internalFrameChallenger2.getUI();
        bi2.setNorthPane(null);
        setInternalFrames(internalFrameChooseCards);
        BasicInternalFrameUI bi3 = (BasicInternalFrameUI) internalFrameChooseCards.getUI();
        bi3.setNorthPane(null);
        setInternalFrames(internalFramePlaceWorkers);
        BasicInternalFrameUI bi4 = (BasicInternalFrameUI) internalFrameChooseCards.getUI();
        bi4.setNorthPane(null);
        setInternalFrames(internalFrameStartTurn);
        BasicInternalFrameUI bi5 = (BasicInternalFrameUI) internalFrameStartTurn.getUI();
        bi5.setNorthPane(null);
        setInternalFrames(internalFrameUpdateBoard);
        BasicInternalFrameUI bi6 = (BasicInternalFrameUI) internalFrameUpdateBoard.getUI();
        bi6.setNorthPane(null);


        frameChat.setPreferredSize(sideSize);
        frameChat.setBounds(frameSize.width * 73/100, -20, sideSize.width, sideSize.height);
        internalFrameSetUp(frameChat);
        BasicInternalFrameUI bii = (BasicInternalFrameUI)frameChat.getUI();
        bii.setNorthPane(null);


        desktopPane = new JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage( ((ImageIcon)(Board.this.coverBoard.getIcon())).getImage(), -7, -18, null);
            }};

        desktopPane.setPreferredSize(frameSize);

        leftGod.setBounds(-7,-18, frameSize.width, frameSize.height);
        leftGod.setOpaque(false);
        leftGod.setContentAreaFilled(false);
        leftGod.setBorderPainted(false);
        leftGod.setIcon(coverLeftGod.getIcon());
        leftGod.setVisible(true);


        leftBoard.setBounds(-7,-18, frameSize.width, frameSize.height);
        leftBoard.setOpaque(false);
        leftBoard.setContentAreaFilled(false);
        leftBoard.setBorderPainted(false);
        leftBoard.setIcon(coverLeftBoard.getIcon());
        leftBoard.setVisible(true);


        nicknameLabel.setFont(felixNormal);

        nicknameLabel1.setFont(felixNormal);
        nicknameLabel1.setForeground(getColorPlayer(mePlayer));

        opponents.setFont(felixNormal);

        opponent1.setText(otherPlayers.get(0).getNickName());
        opponent1.setForeground(getColorPlayer(otherPlayers.get(0)));
        opponentsStyleButtons(opponent1);

        if (numberOfPlayers == 3){
            opponent2 = new JButton(otherPlayers.get(1).getNickName());
            opponent2.setForeground(getColorPlayer(otherPlayers.get(1)));
            colorOpponent2 = otherPlayers.get(1).getColor();
            opponentsStyleButtons(opponent2);
            setColorWorkers2();
        }

        chat.setBounds(frameChat.getWidth() * 22/100 , frameChat.getHeight() * 28/100, frameChat.getWidth() * 63/100, frameChat.getHeight() * 38/100);
        chat.setEditable(false);
        chat.setBackground(new Color(232, 222, 208));
        chat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chat.setFont(felixSmall);
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        chat.setVisible(true);
        scrollPane = new JScrollPane(chat);
        scrollPane.setPreferredSize(scrollSize);
        scrollPane.setBounds(frameChat.getWidth() * 22/100 , frameChat.getHeight() * 28/100, frameChat.getWidth() * 63/100, frameChat.getHeight() * 38/100);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frameChat.add(scrollPane);

        field.setBounds(frameChat.getWidth() * 22/100, frameChat.getHeight() * 66/100, frameChat.getWidth() * 63/100, frameChat.getHeight() * 4/100);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        field.setBackground(new Color(232, 222, 208));
        field.addActionListener(new Write());
        field.setFont(felixSmall);
        field.setVisible(true);
        frameChat.add(field);

        buttonExit.setBounds((frameChat.getWidth() * 82/100), (frameChat.getHeight() * 2/100), frameChat.getWidth() * 12/100, frameChat.getWidth() * 12/100);
        buttonExit.addActionListener(new ChatExit());
        chatStyleButtons(buttonExit, exit);
        frameChat.add(buttonExit);

        chatStyleButtons(backgroundFrameChat, this.coverChat);
        frameChat.getContentPane().add(backgroundFrameChat);


        chatStyleButtons(sfondoFramePower, background);
        windowPower.getContentPane().add(sfondoFramePower);


        resetLevel();

        resetPlayer();

        resetMyWorkers();

        mapStyleButtons();

        try{
            String os = System.getProperty("os.name").toLowerCase();

            if(os.contains("win")) { //windows positions
                nicknameLabel.setBounds((int) (frameSize.width * 3.5/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);
                nicknameLabel1.setBounds((int) (frameSize.width * 10.3/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);
                opponents.setBounds((frameSize.width * 2/100), (frameSize.height * 55/100), size20x5.width, size20x5.height);
                opponent1.setBounds((frameSize.width * 3/100), (frameSize.height * 61/100), frameSize.width * 15/100, frameSize.height * 4/100);
                if (numberOfPlayers == 3){
                    opponent2.setBounds((frameSize.width * 3/100), (frameSize.height * 64/100), frameSize.width * 15/100, frameSize.height * 4/100);
                }
                labelChooseCards.setBounds((int) (frameSize.width * 82.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseFirst.setBounds((int) (frameSize.width * 80/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelChoosePower.setBounds((int) (frameSize.width * 81/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelEndturn.setBounds((int) (frameSize.width * 84.25/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelSeePower.setBounds((int) (frameSize.width * 78.75/100), (int) (frameSize.height * 52.75/100), size20x5.width, size20x5.height);
                labelConfirmPlace.setBounds((int) (frameSize.width * 80/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseWorker.setBounds((int) (frameSize.width * 81/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelMove.setBounds((int) (frameSize.width * 85.75/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelBuild.setBounds((int) (frameSize.width * 85.75/100), (int) (frameSize.height * 39.5/100), size20x5.width, size20x5.height);


                mapButtons[0].setBounds((int) (frameSize.width * (29.5)/100) , frameSize.width * 7/100,
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[1].setBounds((int) (frameSize.width * (37.75)/100) , frameSize.width * 7/100,
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[2].setBounds(frameSize.width * 46/100 , frameSize.width * 7/100,
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[3].setBounds((int) (frameSize.width * (54.25)/100) , frameSize.width * 7/100,
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[4].setBounds((int) (frameSize.width * (62.5)/100) , frameSize.width * 7/100,
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[5].setBounds((int) (frameSize.width * (62.5)/100) , (int) (frameSize.width * (15.31)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[6].setBounds((int) (frameSize.width * (62.5)/100) , (int) (frameSize.width * (23.62)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[7].setBounds((int) (frameSize.width * (62.5)/100) , (int) (frameSize.width * (31.93)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[8].setBounds((int) (frameSize.width * (62.5)/100) , (int) (frameSize.width * (40.24)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[9].setBounds((int) (frameSize.width * (54.25)/100) , (int) (frameSize.width * (40.24)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[10].setBounds(frameSize.width * 46/100 , (int) (frameSize.width * (40.24)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[11].setBounds((int) (frameSize.width * (37.75)/100) , (int) (frameSize.width * (40.24)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[12].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (40.24)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[13].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (31.93)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[14].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (23.62)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[15].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (15.31)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[16].setBounds((int) (frameSize.width * (37.75)/100) , (int) (frameSize.width * (15.31)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[17].setBounds(frameSize.width * 46/100 , (int) (frameSize.width * (15.31)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[18].setBounds((int) (frameSize.width * (54.25)/100) , (int) (frameSize.width * (15.31)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[19].setBounds((int) (frameSize.width * (54.25)/100) , (int) (frameSize.width * (23.62)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[20].setBounds((int) (frameSize.width * (54.25)/100) , (int) (frameSize.width * (31.93)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[21].setBounds(frameSize.width * 46/100 , (int) (frameSize.width * (31.93)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[22].setBounds((int) (frameSize.width * (37.75)/100) , (int) (frameSize.width * (31.93)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[23].setBounds((int) (frameSize.width * (37.75)/100) , (int) (frameSize.width * (23.62)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[24].setBounds(frameSize.width * 46/100 , (int) (frameSize.width * (23.62)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);
            }
            else {//mac positions
                nicknameLabel.setBounds((int) (frameSize.width * 4/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);
                nicknameLabel1.setBounds((int) (frameSize.width * 9.3/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);
                opponents.setBounds((frameSize.width * 3/100), (frameSize.height * 55/100), size20x5.width, size20x5.height);
                opponent1.setBounds((frameSize.width * 4/100), (frameSize.height * 61/100), frameSize.width * 15/100, frameSize.height * 4/100);
                if (numberOfPlayers == 3){
                    opponent2.setBounds((frameSize.width * 4/100), (frameSize.height * 64/100), frameSize.width * 15/100, frameSize.height * 4/100);
                }
                labelChooseCards.setBounds((int) (frameSize.width * 84/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseFirst.setBounds((int) (frameSize.width * 81.5/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelChoosePower.setBounds((int) (frameSize.width * 82.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelEndturn.setBounds((int) (frameSize.width * 85/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelSeePower.setBounds((int) (frameSize.width * 79.5/100), (int) (frameSize.height * 52.75/100), size20x5.width, size20x5.height);
                labelConfirmPlace.setBounds((int) (frameSize.width * 81.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseWorker.setBounds((int) (frameSize.width * 83/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelMove.setBounds((int) (frameSize.width * 86.5/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelBuild.setBounds((int) (frameSize.width * 88.5/100), (int) (frameSize.height * 39.5/100), size20x5.width, size20x5.height);


                mapButtons[0].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (7.6)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[1].setBounds((int) (frameSize.width * (37.8)/100) , (int) (frameSize.width * (7.6)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[2].setBounds((int) (frameSize.width * (46.1)/100) , (int) (frameSize.width * (7.6)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[3].setBounds((int) (frameSize.width * (54.4)/100) , (int) (frameSize.width * (7.6)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[4].setBounds((int) (frameSize.width * (62.7)/100) , (int) (frameSize.width * (7.6)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[5].setBounds((int) (frameSize.width * (62.7)/100) , (int) (frameSize.width * (15.9)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[6].setBounds((int) (frameSize.width * (62.7)/100) , (int) (frameSize.width * (24.2)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[7].setBounds((int) (frameSize.width * (62.7)/100) , (int) (frameSize.width * (32.5)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[8].setBounds((int) (frameSize.width * (62.7)/100) , (int) (frameSize.width * (40.8)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[9].setBounds((int) (frameSize.width * (54.4)/100) , (int) (frameSize.width * (40.8)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[10].setBounds((int) (frameSize.width * (46.1)/100) , (int) (frameSize.width * (40.8)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[11].setBounds((int) (frameSize.width * (37.8)/100) , (int) (frameSize.width * (40.8)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[12].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (40.8)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[13].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (32.5)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[14].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (24.2)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[15].setBounds((int) (frameSize.width * (29.5)/100) , (int) (frameSize.width * (15.9)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[16].setBounds((int) (frameSize.width * (37.8)/100) , (int) (frameSize.width * (15.9)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[17].setBounds((int) (frameSize.width * (46.1)/100) , (int) (frameSize.width * (15.9)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[18].setBounds((int) (frameSize.width * (54.4)/100) , (int) (frameSize.width * (15.9)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[19].setBounds((int) (frameSize.width * (54.4)/100) , (int) (frameSize.width * (24.2)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[20].setBounds((int) (frameSize.width * (54.4)/100) , (int) (frameSize.width * (32.5)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[21].setBounds((int) (frameSize.width * (46.1)/100) , (int) (frameSize.width * (32.5)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[22].setBounds((int) (frameSize.width * (37.8)/100) , (int) (frameSize.width * (32.5)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[23].setBounds((int) (frameSize.width * (37.8)/100) , (int) (frameSize.width * (24.2)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);

                mapButtons[24].setBounds((int) (frameSize.width * (46.1)/100) , (int) (frameSize.width * (24.2)/100),
                        buttonMapSize13x13.width, buttonMapSize13x13.height);
            }
        }
        catch(Exception e) {
            LOGGER.severe(e.getMessage());
        }

        desktopPane.add(nicknameLabel);
        desktopPane.add(nicknameLabel1);
        desktopPane.add(opponents);
        desktopPane.add(opponent1);
        if (numberOfPlayers == 3){
            desktopPane.add(opponent2);
        }

        addMapButtons();


        buttonLv1.setBounds(frameSize.width * 81/100, frameSize.height * 19/100, buttonSize5x5.width, buttonSize5x5.height);
        buttonLv1.setIcon(lvl1Building.getIcon());
        buttonLv1.setVisible(false);
        desktopPane.add(buttonLv1);

        buttonLv2.setBounds(frameSize.width * 89/100, frameSize.height * 19/100, buttonSize5x5.width, buttonSize5x5.height);
        buttonLv2.setIcon(lvl2Building.getIcon());
        buttonLv2.setVisible(false);
        desktopPane.add(buttonLv2);

        buttonLv3.setBounds(frameSize.width * 81/100, frameSize.height * 31/100, buttonSize5x5.width, buttonSize5x5.height);
        buttonLv3.setIcon(lvl3Building.getIcon());
        buttonLv3.setVisible(false);
        desktopPane.add(buttonLv3);

        buttonDome.setBounds(frameSize.width * 89/100, frameSize.height * 31/100, buttonSize5x5.width, buttonSize5x5.height);
        buttonDome.setIcon(domeBuilding.getIcon());
        buttonDome.setVisible(false);
        buttonDome.setBorderPainted(false);
        buttonDome.setContentAreaFilled(false);
        desktopPane.add(buttonDome);


        labelMove.setFont(felixNormal);
        labelMove.setVisible(false);
        desktopPane.add(labelMove);
        buttonMove.setBounds(frameSize.width * 84/100, frameSize.height * 34/100, buttonSize7x7.width, buttonSize7x7.height);
        buttonMove.addActionListener(new SeeMove());
        consoleStyleButtons(buttonMove, lButtonMove);

        labelBuild.setFont(felixNormal);
        labelBuild.setVisible(false);
        desktopPane.add(labelBuild);
        buttonBuild.setBounds(frameSize.width * 84/100, frameSize.height * 46/100, buttonSize7x7.width, buttonSize7x7.height);
        buttonBuild.addActionListener(new AddBuildLvl());
        consoleStyleButtons(buttonBuild, lButtonBuild);


        labelSeePower.setFont(felixNormal);
        labelSeePower.setVisible(false);
        desktopPane.add(labelSeePower);
        buttonPower.setBounds(frameSize.width * 81/100, frameSize.height * 59/100, frameSize.width * 5/100, frameSize.height * 5/100);
        consoleStyleButtons(buttonPower, lButtonPower);

        buttonChat.setBounds(frameSize.width * 85/100, frameSize.height * 58/100, frameSize.width * 5/100, frameSize.height * 7/100);
        buttonChat.addActionListener(new Chat());
        consoleStyleButtons(buttonChat, lButtonChat);
        buttonChat.setVisible(true);


        labelChooseCards.setFont(felixNormal);
        labelChooseCards.setVisible(false);
        desktopPane.add(labelChooseCards);
        buttonChooseCards.setBounds((int) (frameSize.width * 84/100), (int) (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonChooseCards, lButtonChooseCards);


        labelChooseFirst.setFont(felixNormal);
        labelChooseFirst.setVisible(false);
        desktopPane.add(labelChooseFirst);
        buttonChooseFirst.setBounds((int) (frameSize.width * 84/100), (int) (frameSize.height * 34/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonChooseFirst, lbuttonChooseFirst);

        labelChoosePower.setFont(felixNormal);
        labelChoosePower.setVisible(false);
        desktopPane.add(labelChoosePower);
        buttonChoosePower.setBounds((int) (frameSize.width * 84/100), (int) (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonChoosePower, lButtonChoosePower);
        buttonChoosePower.setVisible(false);


        labelConfirmPlace.setFont(felixNormal);
        labelConfirmPlace.setVisible(false);
        desktopPane.add(labelConfirmPlace);
        buttonMultiUse.setBounds((int) (frameSize.width * 84/100), (int) (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonMultiUse, lButtonMove);

        labelChooseWorker.setFont(felixNormal);
        labelChooseWorker.setVisible(false);
        desktopPane.add(labelChooseWorker);



        labelEndturn.setFont(felixNormal);
        labelEndturn.setVisible(false);
        desktopPane.add(labelEndturn);
        buttonEndturn.setBounds((int) (frameSize.width * 84/100), (int) (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonEndturn, lbuttonEndturn);
        buttonEndturn.addActionListener(new EndTurn());



        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setPreferredSize(frameSize);

        frameChat.setVisible(false);
        desktopPane.add(frameChat);
        desktopPane.add(internalFrameChallenger1);
        desktopPane.add(internalFrameChallenger2);
        desktopPane.add(internalFrameChooseCards);
        desktopPane.add(internalFramePlaceWorkers);
        desktopPane.add(internalFrameStartTurn);
        desktopPane.add(internalFrameUpdateBoard);

        desktopPane.add(leftBoard);
        desktopPane.add(leftGod);

        f.setContentPane(desktopPane);

        SwingUtilities.updateComponentTreeUI(f);
        f.pack();

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    private Color getColorPlayer(Player player){
        if(player.getColor().toString().equalsIgnoreCase("BLUE")){
            return Color.BLUE;
        }
        else if(player.getColor().toString().equalsIgnoreCase("WHITE")){
            return Color.WHITE;
        }
        else {
            return Color.MAGENTA;
        }
    }

    private void setMyColorWorkers(){
        if (myColor.toString().equalsIgnoreCase("BLUE")){
            worker = workerCyan;
            lvl1Worker = lvl1Cyan;
            lvl2Worker = lvl2Cyan;
            lvl3Worker = lvl3Cyan;
        }
        else if (myColor.toString().equalsIgnoreCase("WHITE")){
            worker = workerWhite;
            lvl1Worker = lvl1White;
            lvl2Worker = lvl2White;
            lvl3Worker = lvl3White;
        }
        else if (myColor.toString().equalsIgnoreCase("PURPLE")){
            worker = workerPurple;
            lvl1Worker = lvl1Purple;
            lvl2Worker = lvl2Purple;
            lvl3Worker = lvl3Purple;
        }
        myLabels.add(worker);
        myLabels.add(lvl1Worker);
        myLabels.add(lvl2Worker);
        myLabels.add(lvl3Worker);
    }

    private void setColorWorkers1(){
        if (colorOpponent1.toString().equalsIgnoreCase("BLUE")){
            workerOpponents1 = workerCyan;
            lvl1WorkerOpponents1 = lvl1Cyan;
            lvl2WorkerOpponents1 = lvl2Cyan;
            lvl3WorkerOpponents1 = lvl3Cyan;
        }
        else if (colorOpponent1.toString().equalsIgnoreCase("WHITE")){
            workerOpponents1 = workerWhite;
            lvl1WorkerOpponents1 = lvl1White;
            lvl2WorkerOpponents1 = lvl2White;
            lvl3WorkerOpponents1 = lvl3White;
        }
        else if (colorOpponent1.toString().equalsIgnoreCase("PURPLE")){
            workerOpponents1 = workerPurple;
            lvl1WorkerOpponents1 = lvl1Purple;
            lvl2WorkerOpponents1 = lvl2Purple;
            lvl3WorkerOpponents1 = lvl3Purple;
        }
        opponents1Labels.add(workerOpponents1);
        opponents1Labels.add(lvl1WorkerOpponents1);
        opponents1Labels.add(lvl2WorkerOpponents1);
        opponents1Labels.add(lvl3WorkerOpponents1);
    }

    private void setColorWorkers2(){
        if (colorOpponent2.toString().equalsIgnoreCase("BLUE")){
            workerOpponents2 = workerCyan;
            lvl1WorkerOpponents2 = lvl1Cyan;
            lvl2WorkerOpponents2 = lvl2Cyan;
            lvl3WorkerOpponents2 = lvl3Cyan;
        }
        else if (colorOpponent2.toString().equalsIgnoreCase("WHITE")){
            workerOpponents2 = workerWhite;
            lvl1WorkerOpponents2 = lvl1White;
            lvl2WorkerOpponents2 = lvl2White;
            lvl3WorkerOpponents2 = lvl3White;
        }
        else if (colorOpponent2.toString().equalsIgnoreCase("PURPLE")){
            workerOpponents2 = workerPurple;
            lvl1WorkerOpponents2 = lvl1Purple;
            lvl2WorkerOpponents2 = lvl2Purple;
            lvl3WorkerOpponents2 = lvl3Purple;
        }
        opponents2Labels.add(workerOpponents2);
        opponents2Labels.add(lvl1WorkerOpponents2);
        opponents2Labels.add(lvl2WorkerOpponents2);
        opponents2Labels.add(lvl3WorkerOpponents2);
    }

    private JLabel getLabelFromBuildLvl(List<JLabel> list, Building build){
        if (build.equals(Building.GROUND)) {
            return list.get(0);
        }
        else if (build.equals(Building.LVL1)) {
            return list.get(1);
        }
        else if (build.equals(Building.LVL2)) {
            return list.get(2);
        }
        else if (build.equals(Building.LVL3)){
            return list.get(3);
        }
        return null;
    }

    private void setInternalFrames(JInternalFrame i){
        i.setPreferredSize(sideSize);
        i.setBounds((int)((frameSize.width * 50/100) - (internalFrameSize90x90.width * 50/100)), (int) ((frameSize.height * 46/100) - (internalFrameSize90x90.height * 50/100)), internalFrameSize90x90.width, internalFrameSize90x90.height);
        internalFrameSetUp(i);
    }

    public static void internalFrameSetUp(JInternalFrame intFrame){
        intFrame.putClientProperty(PALETTE, Boolean.TRUE);
        intFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        intFrame.setOpaque(false);
        intFrame.putClientProperty(PALETTE, Boolean.TRUE);
        intFrame.setBorder(null);
    }

    private void opponentsStyleButtons(JButton button){
        button.setFont(felixNormal);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    private void chatStyleButtons(JButton button, JLabel label){
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setIcon(label.getIcon());
    }

    private void consoleStyleButtons(JButton button, JLabel label){
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(label.getIcon());
        button.addMouseListener(new ButtonPress());
        button.setVisible(false);
        desktopPane.add(button);
    }

    private void mapStyleButtons(){
        for (int x = 0; x < 25; x++){
            mapButtons[x] = new JButton();
            mapButtons[x].setContentAreaFilled(false);
            mapButtons[x].setOpaque(false);
            mapButtons[x].setBorderPainted(false);
            mapButtons[x].addMouseListener(new ColorBorder());
        }
    }

    private void addMapButtons(){
        for (int x = 0; x < 25; x++){
            desktopPane.add(mapButtons[x]);
            mapButtons[x].setName(String.valueOf(x));
        }
    }

    private void resetPlayer(){
        for (int x = 0; x < 25; x++){
            mapButtonsPlayer[x] = false;
        }
    }

    private void resetLevel(){
        for (int x = 0; x < 25; x++){
            mapButtonslvl[x] = 0;
        }
    }

    private void resetMyWorkers(){
        for (int x = 0; x < 25; x++){
            mapMyWorkers[x] = 0;
        }
    }

    private void removeNickFromOtherPlayers(){
        otherPlayers.removeIf(player -> player.getNickName().equalsIgnoreCase(nickname));
    }

    private Player pickNickFromPlayers(){
        for (Player player : otherPlayers){
            if (player.getNickName().equalsIgnoreCase(nickname))
                return player;
        }
        return null;
    }

    public void showChallenger(String name, boolean bool) {
        if (bool){
            try {
                youChosen = new YouHaveBeenChosen(this, internalFrameChallenger1, internalFrameSize2);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            internalFrameChallenger1.setBounds((int)((frameSize.width * 50/100) - (internalFrameSize2.width * 50/100)), (int) ((frameSize.height * 46/100) - (internalFrameSize2.height * 50/100)), internalFrameSize2.width, internalFrameSize2.height);
            internalFrameChallenger1.getContentPane().add(youChosen);
            internalFrameChallenger1.setVisible(true);
            buttonChooseCards.setVisible(true);
            labelChooseCards.setVisible(true);
            buttonChooseCards.addActionListener(new ChooseCards());
            buttonChooseFirst.setVisible(true);
            labelChooseFirst.setVisible(true);
            buttonChooseFirst.addActionListener(new ChooseFirst());
            enableCardsFirst(false);
        }
        else{
            try {
                waitChallenger = new WaitChallenger(internalFrameChallenger1, internalFrameSize40x45, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            internalFrameChallenger1.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
            internalFrameChallenger1.getContentPane().add(waitChallenger);
            internalFrameChallenger1.setVisible(true);
        }
    }

    public void enableCardsFirst(boolean bool){
        buttonChooseCards.setEnabled(bool);
        buttonChooseFirst.setEnabled(bool);
    }

    private class ChooseCards implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrameChallenger1.remove(youChosen);
            try {
                challengerChoiceCards = new ChallengerChoiceCards(gui, gui.board, internalFrameChallenger1, internalFrameSize90x90, numberOfPlayers);
            } catch (IOException ie) {
                LOGGER.severe(ie.getMessage());
            }
            internalFrameChallenger1.setBounds((int)((frameSize.width * 50/100) - (internalFrameSize90x90.width * 50/100)), (int) ((frameSize.height * 46/100) - (internalFrameSize90x90.height * 50/100)), internalFrameSize90x90.width, internalFrameSize90x90.height);
            internalFrameChallenger1.getContentPane().add(challengerChoiceCards);
            internalFrameChallenger1.setVisible(true);
        }
    }

    private class ChooseFirst implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                challengerChoiceFirstPlayer = new ChallengerChoiceFirstPlayer(gui, gui.board, internalFrameChallenger2, internalFrameSize90x90, numberOfPlayers, allPlayer);
            } catch (IOException ie) {
                LOGGER.severe(ie.getMessage());
            }
            internalFrameChallenger2.getContentPane().add(challengerChoiceFirstPlayer);
            internalFrameChallenger2.setVisible(true);
        }
    }

    public void setCardsChosen(List<String> cardsChosen) {
        this.cardsChosen = cardsChosen;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public void callChallengerResponse(){
        if (cardsChosen.size()!=0 && firstPlayer != null){
            gui.challengerResponse(firstPlayer, cardsChosen);
            buttonChooseCards.setVisible(false);
            labelChooseCards.setVisible(false);
            buttonChooseFirst.setVisible(false);
            labelChooseFirst.setVisible(false);
            displayEndturn(true);
        }
    }

    public void showCardChoice(List<String> cards, String name, boolean bool){

        internalFrameChallenger1.dispose();
        internalFrameChallenger2.dispose();
        godCards = cards;
        nameChoosing = name;

        if (chooseCard != null) {
            internalFrameChooseCards.remove(chooseCard);
        }
        if (bool) {
            try {
                chooseCard = new ChooseCard(this, internalFrameChooseCards, internalFrameSize40x45.width, internalFrameSize40x45.height, cards, 4, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            internalFrameChooseCards.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
            buttonChoosePower.setVisible(true);
            enablePower(false);
            labelChoosePower.setVisible(true);
            buttonChoosePower.addActionListener(new ChoosePower());
        }
        else {
            try {
                chooseCard = new ChooseCard(this, internalFrameChooseCards, internalFrameSize40x45.width, internalFrameSize40x45.height, cards, 0, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            internalFrameChooseCards.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        }
        internalFrameChooseCards.getContentPane().add(chooseCard);
        internalFrameChooseCards.setVisible(true);
    }

    public void enablePower(boolean bool){
        buttonChoosePower.setEnabled(bool);
    }

    public void callCardChoiceResponse(){
        if (cardChosen != null){
            gui.cardChoiceResponse(cardChosen);
            buttonChoosePower.setVisible(false);
            labelChoosePower.setVisible(false);
            buttonPower.setName(cardChosen);
            buttonPower.addActionListener(new ShowYourPower());
            buttonPower.setVisible(true);
            buttonChat.setBounds(frameSize.width * 89/100, frameSize.height * 58/100, frameSize.width * 5/100, frameSize.height * 7/100);
            labelSeePower.setVisible(true);
            try {
                coverLeftGod = ImageHandler.setImage("resources/Graphics/gods/" + cardChosen + "_left.png", 100, 100, frameSize.width, frameSize.height);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            leftGod.setIcon(coverLeftGod.getIcon());
            displayEndturn(true);
        }
    }

    public void setCardChosen(String cardChosen) {
        this.cardChosen = cardChosen;
    }

    private class ChoosePower implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrameChooseCards.remove(chooseCard);
            try {
                chooseCard = new ChooseCard(gui.board, internalFrameChooseCards, internalFrameSize90x90.width, internalFrameSize90x90.height, godCards, godCards.size(), nameChoosing);
            } catch (IOException ie) {
                LOGGER.severe(ie.getMessage());
            }
            internalFrameChooseCards.setBounds((int)((frameSize.width * 50/100) - (internalFrameSize90x90.width * 50/100)), (int) ((frameSize.height * 46/100) - (internalFrameSize90x90.height * 50/100)), internalFrameSize90x90.width, internalFrameSize90x90.height);
            internalFrameChooseCards.getContentPane().add(chooseCard);
            internalFrameChooseCards.setVisible(true);
        }
    }

    private class ShowYourPower implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton)e.getSource();
            eliminateActionClass(c, ShowYourPower.class);
            try {
                coverBackground = ImageHandler.setImage("resources/Graphics/gods/" + c.getName() + "_description.png", 100, 100, internalFrameSize40x45.width, internalFrameSize40x45.height);
            } catch (IOException ioException) {
                LOGGER.severe(ioException.getMessage());
            }
            c.setIcon(lButtonPowerPress.getIcon());
            background = new JLabel(coverBackground.getIcon());
            windowPower.getContentPane().removeAll();
            chatStyleButtons(sfondoFramePower, background);
            windowPower.getContentPane().add(sfondoFramePower);
            c.addActionListener(new HidePower());
            windowPower.setVisible(true);
        }
    }

    private class HidePower implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton)e.getSource();
            eliminateActionClass(c, HidePower.class);
            c.setIcon(lButtonPower.getIcon());
            c.addActionListener(new ShowYourPower());
            windowPower.setVisible(false);
        }
    }

    public void showPlaceWorkers(String name, boolean bool){
        internalFrameChooseCards.dispose();
        if (placeWorkers != null){
            internalFramePlaceWorkers.remove(placeWorkers);
        }
        if (bool){
            try {
                placeWorkers = new PlaceWorkers(internalFramePlaceWorkers, internalFrameSize40x45, 0, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            labelConfirmPlace.setVisible(true);
            buttonMultiUse.setVisible(true);
            buttonMultiUse.setEnabled(false);
            addPlaceMove();
        }
        else{
            try {
                placeWorkers = new PlaceWorkers(internalFramePlaceWorkers, internalFrameSize40x45, 1, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }

        }
        internalFramePlaceWorkers.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        internalFramePlaceWorkers.getContentPane().add(placeWorkers);
        internalFramePlaceWorkers.setVisible(true);
    }

    private void addPlaceMove(){
        for (int x = 0; x < 25; x++){
            if (!mapButtonsPlayer[x])
                mapButtons[x].addActionListener(new PlaceWorker());
        }
    }

    private class PlaceWorker implements ActionListener{
        int x;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (placed <= 2) {
                JButton c = (JButton) e.getSource();
                x = Integer.parseInt(c.getName());

                if (worker1 == 0){
                    if (worker2 == 0) {
                        c.setIcon(worker.getIcon());
                        mapButtonsPlayer[x] = true;
                        placed++;
                        mapMyWorkers[x] = 1;
                        worker1 = 1;
                        eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                    }

                    else if (worker2 == 2){
                        if (!mapButtonsPlayer[x] && placed < 2) {
                            c.setIcon(worker.getIcon());
                            mapButtonsPlayer[x] = true;
                            placed++;
                            mapMyWorkers[x] = 1;
                            worker1 = 1;
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }

                        else if (mapButtonsPlayer[x]){
                            c.setIcon(null);
                            mapButtonsPlayer[x] = false;
                            placed--;
                            mapMyWorkers[x] = 0;
                            worker2 = 0;
                            buttonMultiUse.setEnabled(false);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                    }
                }
                else if (worker1 == 1){
                    if (worker2 == 0){
                        if (!mapButtonsPlayer[x] && placed < 2) {
                            c.setIcon(worker.getIcon());
                            mapButtonsPlayer[x] = true;
                            placed++;
                            mapMyWorkers[x] = 2;
                            worker2 = 2;
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }

                        else if (mapButtonsPlayer[x]){
                            c.setIcon(null);
                            mapButtonsPlayer[x] = false;
                            placed--;
                            mapMyWorkers[x] = 0;
                            worker1 = 0;
                            buttonMultiUse.setEnabled(false);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                    }
                    else if (worker2 == 2){
                        if (mapMyWorkers[x] == 1) {
                            c.setIcon(null);
                            mapButtonsPlayer[x] = false;
                            placed--;
                            mapMyWorkers[x] = 0;
                            worker1 = 0;
                            buttonMultiUse.setEnabled(false);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                        else if (mapMyWorkers[x] == 2){
                            c.setIcon(null);
                            mapButtonsPlayer[x] = false;
                            placed--;
                            mapMyWorkers[x] = 0;
                            worker2 = 0;
                            buttonMultiUse.setEnabled(false);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                    }
                }
                if (placed == 2) {
                    buttonMultiUse.setEnabled(true);
                    buttonMultiUse.addActionListener(new ConfirmPlace());
                }
            }
        }
    }

    private class ConfirmPlace implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Integer> tiles;
            tiles = modifiedTiles();
            gui.placeWorkersResponse(tiles.get(0) + 1, tiles.get(1) + 1);
            buttonMultiUse.setVisible(false);
            labelConfirmPlace.setVisible(false);
            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
            removePlaceWorker();
            displayEndturn(true);
        }
    }

    private List<Integer> modifiedTiles(){
        List<Integer> tiles = new ArrayList<>();
        for (int y = 1; y < 3; y++) {
            for (int x = 0; x < 25; x++) {
                if (mapMyWorkers[x] == y) {
                    tiles.add(x);
                    x = 24;
                }
            }
        }
        return tiles;
    }

    private void  removePlaceWorker(){
        for (int x = 0; x < 25; x++) {
            eliminateActionClass(mapButtons[x], PlaceWorker.class);
        }
    }

    public void updatePlacedWorkers(List<Square> squares){
        internalFramePlaceWorkers.setVisible(false);
        for (Square square : squares){
            if(square.getPlayer().getColor().toString().equalsIgnoreCase("BLUE")){
                mapButtons[square.getTile() - 1].setIcon(workerCyan.getIcon());
                mapButtonsPlayer[square.getTile() - 1] = true;
            }
            else if(square.getPlayer().getColor().toString().equalsIgnoreCase("WHITE")){
                mapButtons[square.getTile() - 1].setIcon(workerWhite.getIcon());
                mapButtonsPlayer[square.getTile() - 1] = true;
            }
            else {
                mapButtons[square.getTile() - 1].setIcon(workerPurple.getIcon());
                mapButtonsPlayer[square.getTile() - 1] = true;
            }
        }
        powerToOpponents(squares.get(0).getPlayer().getNickName(), squares.get(0).getPlayer().getPower().getName());
    }

    private void powerToOpponents(String player, String card){
        if (opponent1.getText().equalsIgnoreCase(player)) {
            opponent1.setName(card);
            opponent1.addMouseListener(new SeeEnemyPower());
        }
        else if (numberOfPlayers == 3 && opponent2.getText().equalsIgnoreCase(player)){
            opponent2.setName(card);
            opponent2.addMouseListener(new SeeEnemyPower());
        }

    }

    private class SeeEnemyPower extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton c = (JButton)e.getSource();
            try {
                coverBackground = ImageHandler.setImage("resources/Graphics/gods/" + c.getName() + "_description.png", 100, 100, internalFrameSize40x45.width, internalFrameSize40x45.height);
            } catch (IOException ioException) {
                LOGGER.severe(ioException.getMessage());
            }
            background = new JLabel(coverBackground.getIcon());
            windowPower.getContentPane().removeAll();
            chatStyleButtons(sfondoFramePower, background);
            windowPower.getContentPane().add(sfondoFramePower);
            windowPower.setVisible(true);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            windowPower.setVisible(false);
        }
    }

    public void startTurn(String nick, boolean isYourPlayer){
        internalFramePlaceWorkers.dispose();
        if (startTurn != null){
            internalFrameStartTurn.remove(startTurn);
        }
        if (isYourPlayer){
            try {
                startTurn = new StartTurn(internalFrameStartTurn, internalFrameSize40x45, 0, nick);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            labelChooseWorker.setVisible(true);
            buttonMultiUse.setVisible(true);
            buttonMultiUse.addActionListener(new AvaiableWorkers());
        }
        else{
            try {
                startTurn = new StartTurn(internalFrameStartTurn, internalFrameSize40x45, 1, nick);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }

        }
        internalFrameStartTurn.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        internalFrameStartTurn.getContentPane().add(startTurn);
        internalFrameStartTurn.setVisible(true);
    }

    private class AvaiableWorkers implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (avaiableMovePositions.size() != 0){
                eliminatePreviousPositionsActions(avaiableMovePositions);
            }
            avaiableWorkers = gui.availableWorkers();
            for (Integer x : avaiableWorkers){
                for (int y = 0; y < 25; y++){
                    if (mapMyWorkers[y] == x){
                        eliminateMouseClass(mapButtons[y], ColorBorder.class);
                        eliminateActionClass(mapButtons[y], SelectWorker.class);
                        mapButtons[y].setBorder(BorderFactory.createLineBorder(selectWorkerBorder, 5));
                        mapButtons[y].setBorderPainted(true);
                        mapButtons[y].addActionListener(new SelectWorker());
                        avaiableWorkersPositions.add(y);
                        y = 24;
                    }
                }
            }
        }
    }

    private void eliminatePreviousPositionsActions(List<Integer> previousPositions){
        for (Integer x : previousPositions){
            eliminateActionClass(mapButtons[x - 1], Move.class);
            mapButtons[x - 1].setBorderPainted(false);
            mapButtons[x - 1].addMouseListener(new ColorBorder());
        }
        avaiableMovePositions.clear();
    }

    private class SelectWorker implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton) e.getSource();
            int pos = Integer.parseInt(c.getName());
            gui.setWorker(mapMyWorkers[pos]);
            for (Integer k : avaiableWorkersPositions){
                eliminateActionClass(mapButtons[k], SelectWorker.class);
                mapButtons[k].setBorderPainted(false);

                if (pos != k){
                    mapButtons[k].addMouseListener(new ColorBorder());
                }
            }
            mapButtons[pos].setBorder(BorderFactory.createLineBorder(selectedWorkerBorder, 5));
            mapButtons[pos].setBorderPainted(true);
            labelChooseWorker.setVisible(false);
            buttonMultiUse.setVisible(false);
            workerChoosen = mapMyWorkers[pos];
            tileWorkerChosen = pos;
            avaiableWorkersPositions.clear();
        }
    }

    public void displayButtons(List<MessageType> actions){
        System.out.println(actions.toString());
        for (MessageType mess : actions){
            switch (mess){
                case MOVEWORKER:
                    displayMove(true);
                    break;

                case BUILDWORKER:
                    displayBuild(true);
                    break;

                case WORKERCHOICE:
                    displayChoose(true);
                    break;

                case ENDTURN:
                    displayEndturn(true);
                    break;
                default:
            }
        }
    }

    private class SeeMove implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            avaiableMovePositions = gui.availableMoveSquare();

            for (Integer x : avaiableMovePositions){
                eliminateMouseClass(mapButtons[x - 1], ColorBorder.class);
                mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(moveBorder, 5));
                mapButtons[x - 1].setBorderPainted(true);
                mapButtons[x - 1].addActionListener(new Move());
            }
        }
    }

    private class Move implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton) e.getSource();
            responce = gui.moveWorker(Integer.parseInt(c.getName()) + 1);
            for (Integer x : avaiableMovePositions){
                eliminateActionClass(mapButtons[x - 1], Move.class);
                mapButtons[x - 1].setBorderPainted(false);
                mapButtons[x - 1].addMouseListener(new ColorBorder());

            }
            displayModifications(gui.getModifiedsquare());
            displayChoose(false);
            displayMove(false);
            displayBuild(false);
            gui.mapNextAction(responce);
        }
    }

    public void updateBoard(String nick, List<Square> squares, MessageType type){
        System.out.println(squares.size());
        List<JLabel> list = null;

        if (updateBoard != null) {
            internalFrameUpdateBoard.remove(updateBoard);
        }
        try {
            updateBoard = new UpdateBoard(this, internalFrameUpdateBoard, internalFrameSize40x45, nick, type);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        internalFrameUpdateBoard.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        internalFrameUpdateBoard.getContentPane().add(updateBoard);
        internalFrameUpdateBoard.setVisible(true);


        switch (type){
            case MOVEWORKER:
            case BUILDWORKER:
                displayModifications(squares);
                break;

            default:
        }
    }

    private void displayModifications(List<Square> squares){
        List<JLabel> list = null;
        for (Square square : squares){
            if (square.hasPlayer()) {

                if (square.getPlayer().getNickName().equalsIgnoreCase(opponent1.getText())){
                    list = opponents1Labels;
                }
                else if (square.getPlayer().getNickName().equalsIgnoreCase(opponent1.getText())){
                    list = opponents2Labels;
                }
                else {
                    list = myLabels;
                }
                if (square.getBuilding().equals(Building.GROUND)) {
                    mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.GROUND)).getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = true;
                } else if (square.getBuilding().equals(Building.LVL1)) {
                    mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.LVL1)).getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = true;
                } else if (square.getBuilding().equals(Building.LVL2)) {
                    mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.LVL2)).getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = true;
                } else if (square.getBuilding().equals(Building.LVL3)) {
                    mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.LVL3)).getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = true;
                }
            }
            else {
                if (square.getBuilding().equals(Building.GROUND)) {
                    mapButtons[square.getTile() - 1].setIcon(null);
                    mapButtonsPlayer[square.getTile() - 1] = false;
                } else if (square.getBuilding().equals(Building.LVL1)) {
                    mapButtons[square.getTile() - 1].setIcon(lvl1.getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = false;
                } else if (square.getBuilding().equals(Building.LVL2)) {
                    mapButtons[square.getTile() - 1].setIcon(lvl2.getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = false;
                } else if (square.getBuilding().equals(Building.LVL3)) {
                    mapButtons[square.getTile() - 1].setIcon(lvl3.getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = false;
                } else {
                    mapButtons[square.getTile() - 1].setIcon(lvl3Dome.getIcon());
                    mapButtonsPlayer[square.getTile() - 1] = false;
                }
            }
        }
    }

    private class EndTurn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.endTurn();
            displayEndturn(false);
        }
    }

    private void displayEndturn(boolean bool){
        labelEndturn.setVisible(bool);
        buttonEndturn.setVisible(bool);
    }

    private void displayMove(boolean bool){
        labelMove.setVisible(bool);
        buttonMove.setVisible(bool);
    }

    private void displayBuild(boolean bool){
        labelBuild.setVisible(bool);
        buttonBuild.setVisible(bool);
    }

    private void displayChoose(boolean bool){
        labelChooseWorker.setVisible(bool);
        buttonMultiUse.setVisible(bool);
    }

    private void enableChoose(boolean bool){
        buttonMultiUse.setEnabled(bool);
    }

    private class Chat implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frameChat.setVisible(true);
        }
    }

    private class ChatExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frameChat.setVisible(false);
        }
    }

    private class AddBuildLvl implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            avaiableBuildPositions = gui.availableBuildSquare();

            buttonLv1.addActionListener(new AddBuildLvl1());
            buttonLv2.addActionListener(new AddBuildLvl2());
            buttonLv3.addActionListener(new AddBuildLvl3());
            buttonDome.addActionListener(new AddBuildDome());
            displayLevel(true);
            buttonMove.setVisible(false);
        }
    }

    private void displayLevel(boolean bool){
        buttonLv1.setVisible(bool);
        buttonLv2.setVisible(bool);
        buttonLv3.setVisible(bool);
        buttonDome.setVisible(bool);
    }

    private void removeBuildLvl() {
        eliminateActionClass(buttonLv1, AddBuildLvl1.class);
        eliminateActionClass(buttonLv2, AddBuildLvl2.class);
        eliminateActionClass(buttonLv3, AddBuildLvl3.class);
        eliminateActionClass(buttonDome, AddBuildDome.class);
    }

    private void removeBuildBorder(List<Integer> positions){
        for (Integer x : positions){
            mapButtons[x - 1].setBorderPainted(false);
            eliminateActionClass(mapButtons[x - 1], BuildLvl1.class);
            eliminateActionClass(mapButtons[x - 1], BuildLvl2.class);
            eliminateActionClass(mapButtons[x - 1], BuildLvl3.class);
            eliminateActionClass(mapButtons[x - 1], BuildDome.class);
        }
    }

    private class AddBuildLvl1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            removeBuildBorder(avaiableBuildPositions);

            for (Integer x : avaiableBuildPositions){

                if (mapButtonslvl[x - 1] == 0 && !mapButtonsPlayer[x - 1]){
                    eliminateMouseClass(mapButtons[x - 1], ColorBorder.class);
                    mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(buildBorder, 5));
                    mapButtons[x - 1].setBorderPainted(true);
                    mapButtons[x - 1].addActionListener(new BuildLvl1());
                }
            }
        }
    }

    private class BuildLvl1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton c = (JButton) e.getSource();
            for (int x = 0; x < 25; x++){

                if (mapButtons[x] == c){
                    c.setIcon(lvl1.getIcon());
                    mapButtonslvl[x] = 1;
                    responce = gui.buildWorker(x + 1, Building.LVL1);
                }

            }
            displayChoose(false);
            displayMove(false);
            displayBuild(false);
            displayLevel(false);
            removeBuildBorder(avaiableBuildPositions);
            removeBuild();
            removeBuildLvl();
            gui.mapNextAction(responce);
        }
    }

    private class AddBuildLvl2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            removeBuildBorder(avaiableBuildPositions);

            for (Integer x : avaiableBuildPositions){

                if (mapButtonslvl[x - 1] == 1 && !mapButtonsPlayer[x - 1]){
                    mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(buildBorder, 5));
                    mapButtons[x - 1].setBorderPainted(true);
                    mapButtons[x - 1].addActionListener(new BuildLvl2());
                }
            }
        }
    }

    private class BuildLvl2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton c = (JButton) e.getSource();
            for (int x = 0; x < 25; x++){

                if (mapButtons[x] == c){
                    c.setIcon(lvl2.getIcon());
                    mapButtonslvl[x] = 2;
                    responce = gui.buildWorker(x + 1, Building.LVL2);
                }

            }
            displayChoose(false);
            displayMove(false);
            displayBuild(false);
            displayLevel(false);
            removeBuildBorder(avaiableBuildPositions);
            removeBuild();
            removeBuildLvl();
            gui.mapNextAction(responce);
        }
    }

    private class AddBuildLvl3 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            removeBuildBorder(avaiableBuildPositions);

            for (Integer x : avaiableBuildPositions){

                if (mapButtonslvl[x - 1] == 2 && !mapButtonsPlayer[x - 1]){
                    mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(buildBorder, 5));
                    mapButtons[x - 1].setBorderPainted(true);
                    mapButtons[x - 1].addActionListener(new BuildLvl3());
                }
            }
        }
    }

    private class BuildLvl3 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton c = (JButton) e.getSource();
            for (int x = 0; x < 25; x++){

                if (mapButtons[x] == c){
                    c.setIcon(lvl3.getIcon());
                    mapButtonslvl[x] = 3;
                    responce = gui.buildWorker(x + 1, Building.LVL3);
                }

            }
            displayChoose(false);
            displayMove(false);
            displayBuild(false);
            displayLevel(false);
            removeBuildBorder(avaiableBuildPositions);
            removeBuild();
            removeBuildLvl();
            gui.mapNextAction(responce);
        }
    }

    private class AddBuildDome implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            removeBuildBorder(avaiableBuildPositions);

            for (Integer x : avaiableBuildPositions){

                if (mapButtonslvl[x - 1] == 3 && !mapButtonsPlayer[x - 1]){
                    mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(buildBorder, 5));
                    mapButtons[x - 1].setBorderPainted(true);
                    mapButtons[x - 1].addActionListener(new BuildDome());
                }
                else if (mePlayer.getPower().getName().equalsIgnoreCase("atlas") && !mapButtonsPlayer[x - 1]){
                    mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(buildBorder, 5));
                    mapButtons[x - 1].setBorderPainted(true);
                    mapButtons[x - 1].addActionListener(new BuildDome());
                }
            }
        }
    }

    private class BuildDome implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton c = (JButton) e.getSource();
            for (int x = 0; x < 25; x++){

                if (mapButtons[x] == c && !mapButtonsPlayer[x]){
                    c.setIcon(lvl3Dome.getIcon());
                    mapButtonslvl[x]++;
                    responce = gui.buildWorker(x + 1, Building.DOME);
                }

            }
            displayChoose(false);
            displayMove(false);
            displayBuild(false);
            displayLevel(false);
            removeBuildBorder(avaiableBuildPositions);
            removeBuild();
            removeBuildLvl();
            gui.mapNextAction(responce);
        }
    }

    private void removeBuild() {
        for (int x = 0; x < 25; x++){
            for (int y = 0; y < mapButtons[x].getActionListeners().length; y++){
                if (mapButtons[x].getActionListeners()[y].getClass().equals(BuildLvl1.class)  || mapButtons[x].getActionListeners()[y].getClass().equals(BuildLvl2.class) ||
                        mapButtons[x].getActionListeners()[y].getClass().equals(BuildLvl3.class)  || mapButtons[x].getActionListeners()[y].getClass().equals(BuildDome.class))
                    mapButtons[x].removeActionListener(mapButtons[x].getActionListeners()[y]);
            }
            mapButtons[x].setBorderPainted(false);
            mapButtons[x].addMouseListener(new ColorBorder());
        }
    }

    private static class ColorBorder extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton c = (JButton)e.getSource();
            c.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            c.setBorderPainted(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton c = (JButton)e.getSource();
            c.setBorderPainted(false);
        }
    }

    private class ButtonPress extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            JButton c = (JButton) e.getSource();
            if (buttonMove == c) {
                buttonMove.setIcon(lButtonMovePress.getIcon());
            }
            else if (buttonMultiUse == c){
                buttonMultiUse.setIcon(lButtonMovePress.getIcon());
            }
            else if (buttonBuild == c) {
                buttonBuild.setIcon(lButtonBuildPress.getIcon());
            }
            /*else if (buttonPower == c) {
                buttonPower.setIcon(lButtonPowerPress.getIcon());
            }*/
            else if (buttonChat == c){
                buttonChat.setIcon(lButtonChatPress.getIcon());
            }
            else if (buttonChooseCards == c) {
                buttonChooseCards.setIcon(lButtonChooseCardsPress.getIcon());
            }
            else if (buttonChooseFirst == c) {
                buttonChooseFirst.setIcon(lbuttonChooseFirstPress.getIcon());
            }
            else if (buttonChoosePower == c) {
                buttonChoosePower.setIcon(lButtonChoosePowerPress.getIcon());
            }
            else if (buttonEndturn == c) {
                buttonEndturn.setIcon(lbuttonEndturnPress.getIcon());
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton c = (JButton)e.getSource();

            if (buttonMove == c){
                buttonMove.setIcon(lButtonMove.getIcon());
            }
            else if (buttonMultiUse == c){
                buttonMultiUse.setIcon(lButtonMove.getIcon());
            }
            else if (buttonBuild == c){
                buttonBuild.setIcon(lButtonBuild.getIcon());
            }
            /*else if (buttonPower == c){
                buttonPower.setIcon(lButtonPower.getIcon());
            }*/
            else if(buttonChat == c) {
                buttonChat.setIcon(lButtonChat.getIcon());
            }
            else if (buttonChooseCards == c) {
                buttonChooseCards.setIcon(lButtonChooseCards.getIcon());
            }
            else if (buttonChooseFirst == c) {
                buttonChooseFirst.setIcon(lbuttonChooseFirst.getIcon());
            }
            else if (buttonChoosePower == c) {
                buttonChoosePower.setIcon(lButtonChoosePower.getIcon());
            }
            else if (buttonEndturn == c) {
                buttonEndturn.setIcon(lbuttonEndturn.getIcon());
            }
        }
    }

    public void writeInChat(String name, String mess){
        chat.append(name + ": " + mess + "\n");
        chat.setCaretPosition(chat.getDocument().getLength());
        field.setText("");
    }

    private class Write implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!field.getText().equals("")) {
                String stringa = field.getText().toLowerCase();
                chat.append(mePlayer.getNickName() + ": " + field.getText().toLowerCase() + "\n");
                chat.setCaretPosition(chat.getDocument().getLength());
                field.setText("");
                gui.sendChatMessage(stringa);
            }
        }
    }
}
