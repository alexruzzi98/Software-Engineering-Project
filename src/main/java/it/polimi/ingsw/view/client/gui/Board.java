package it.polimi.ingsw.view.client.gui;

import it.polimi.ingsw.model.Response;
import it.polimi.ingsw.model.map.Building;
import it.polimi.ingsw.model.map.Square;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.WorkerName;
import it.polimi.ingsw.network.message.MessageType;


import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static it.polimi.ingsw.utils.ConstantsContainer.*;
import static it.polimi.ingsw.view.client.gui.GuiUtils.*;
import static it.polimi.ingsw.view.client.gui.Gui.*;

/**
 * Class that build the main Board
 * @author Luigi Scibona
 * @version 1.0
 * @since 2020/06/13
 */

public class Board {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    double ratio= (screen.getWidth()/screen.getHeight());
    int width = (int) ((screen.getWidth() * 95 / 100) * (1.689999 / ratio));
    int height = (int) (screen.getHeight() * 91 / 100);
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
    JDesktopPane notTurn;
    JDesktopPane updateBoard;
    JDesktopPane seeConstraint;
    JInternalFrame frameChat = new JInternalFrame("", false, false, false, false);
    JInternalFrame frameBuildings = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameChallenger1 = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameChallenger2 = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameChooseCards = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFramePlaceWorkers = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameStartTurn = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameUpdateBoard = new JInternalFrame("", false, false, false, false);
    JInternalFrame internalFrameConstraint = new JInternalFrame("", false, false, false, false);
    JWindow windowPower;
    JScrollPane scrollPane;
    MyButton newGame = new MyButton(2);
    MyButton close = new MyButton(3);
    MyButton closeTutorial = new MyButton(3);
    MyButton continueTutorial = new MyButton(5);
    MyButton keepWatching = new MyButton(4);
    JButton buttonLvl1 = new JButton();
    JButton buttonLvl2 = new JButton();
    JButton buttonLvl3 = new JButton();
    JButton buttonDome = new JButton();
    JButton buttonMove = new JButton();
    JButton buttonBuild = new JButton();
    JButton buttonPower = new JButton();
    JButton buttonChooseCards = new JButton();
    JButton buttonChooseFirst = new JButton();
    JButton buttonChoosePower = new JButton();
    JButton buttonChat = new JButton();
    JButton buttonEndTurn = new JButton();
    JButton buttonMultiUse = new JButton();
    JButton buttonExit = new JButton();
    JButton backgroundFrameChat = new JButton();
    JButton backgroundFrameBuildings = new JButton();
    JButton backgroundFramePower = new JButton();
    JButton tutorial = new JButton();
    JButton tutorial1 = new JButton();
    JButton tutorial2 = new JButton();
    JButton leftBoard = new JButton();
    JButton leftGod = new JButton();
    JButton winLose = new JButton();
    JButton winner = new JButton();
    JButton loser1 = new JButton();
    JButton loser2 = new JButton();
    JButton glow = new JButton();
    JButton opponent1 = new JButton();
    JButton opponent2;
    MyButton backButton = new MyButton(1);
    JTextPane chat = new JTextPane();
    Style colorStyle;
    StyledDocument doc;
    JTextField field = new JTextField();
    private final JButton[] mapButtons = new JButton[25];
    int[] mapMyWorkers = new int[25];
    boolean[] mapButtonsPlayer = new boolean[25];
    JLabel nicknameLabel = new JLabel();
    JLabel nicknameLabel1 = new JLabel();
    JLabel coverBoard;
    JLabel coverChat;
    JLabel coverBuildings;
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
    JLabel lButtonPowerPing;
    JLabel lButtonPowerPress;
    JLabel lButtonPowerPressPing;
    JLabel lButtonChat;
    JLabel lButtonChatPress;
    JLabel lButtonChatPing;
    JLabel lButtonChatPressPing;
    JLabel labelChooseCards = new JLabel("Choose Cards");
    JLabel lButtonChooseCards;
    JLabel lButtonChooseCardsPress;
    JLabel labelChooseFirst = new JLabel("Choose the first player");
    JLabel lButtonChooseFirst;
    JLabel lButtonChooseFirstPress;
    JLabel labelChoosePower = new JLabel("Choose your Power");
    JLabel lButtonChoosePower;
    JLabel lButtonChoosePowerPress;
    JLabel labelConfirmPlace = new JLabel("Confirm your positions");
    JLabel labelSeePower = new JLabel("See your power");
    JLabel labelEndTurn = new JLabel("End Turn");
    JLabel labelChooseWorker = new JLabel("Choose the worker");
    JLabel labelMove = new JLabel("Move");
    JLabel labelBuild = new JLabel("Build");
    JLabel lLvl1 = new JLabel("Lvl1");
    JLabel lLvl2 = new JLabel("Lvl2");
    JLabel lLvl3 = new JLabel("Lvl3");
    JLabel lDome = new JLabel("Dome");
    JLabel lButtonEndTurn;
    JLabel lButtonEndTurnPress;
    JLabel border;
    JLabel lWinner = null;
    JLabel lLoser1 = null;
    JLabel lLoser2 = null;
    JLabel lGlow;
    JLabel lTutorial;
    JLabel lTutorial1;
    JLabel lTutorial2;
    JLabel lLost = new JLabel();
    Dimension frameSize = new Dimension();
    Dimension boardSize = new Dimension();
    Dimension bottomSize = new Dimension();
    Dimension sideSize = new Dimension();
    Dimension buttonMapSize13x13 = new Dimension();
    Dimension scrollSize = new Dimension();
    Dimension labelMapSize = new Dimension();
    Dimension internalFrameSize90x90 = new Dimension();
    Dimension internalFrameSize80x80 = new Dimension();
    Dimension internalFrameSize70x70 = new Dimension();
    Dimension internalFrameSize40x45 = new Dimension();
    Dimension buttonSize = new Dimension();
    Dimension buttonSize7x7 = new Dimension();
    Dimension buttonSize5x5 = new Dimension();
    Dimension size20x5 = new Dimension();
    Dimension chatDimension = new Dimension();
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
    List<Integer> availableWorkers = new ArrayList<>();
    List<Integer> availableWorkersPositions = new ArrayList<>();
    List<Integer> availableMovePositions = new ArrayList<>();
    List<Integer> availableBuildPositions = new ArrayList<>();
    int workerChosen = 0;
    int tileWorkerChosen = 0;
    Color selectWorkerBorder = Color.CYAN;
    Color selectedWorkerBorder = Color.RED;
    Color moveBorder = Color.WHITE;
    Color buildBorder = Color.WHITE;
    Color modifiedBorder = Color.ORANGE;
    Color chooseTile = Color.YELLOW;
    int worker1 = 0;
    int worker2 = 0;
    Response response = null;
    List<JLabel> myLabels = new ArrayList<>();
    List<JLabel> opponents1Labels = new ArrayList<>();
    List<JLabel> opponents2Labels = new ArrayList<>();
    boolean chatOpen = false;
    int tileBuildChosen = 0;
    private static final double BOLDDIMENSION = (25 * screenSize.getHeight() / 1080);
    List<String> constraint = new ArrayList<>();
    private static final MP3 click = new MP3(MUSIC + "Click.mp3");
    MP3 place;
    MP3 build;
    MP3 loopSound;
    MP3 win;
    MP3 lose;
    MP3 yourTurn;
    MP3 tower;

    /**
     * Public constructor for the board
     * @param instance Reference to the client GUI class
     * @param screenSize Size of the screen
     * @param numberOfPlayer Number of Players in game
     * @param players List of all the Players in game
     * @param players2 List of all the Players in game
     * @param nickname Name of the Player of the client
     * @throws IOException if the loading of the inscription or the descriptions of the gods or the image to build the Board was not successful
     */

    public void show(Gui instance, Dimension screenSize, Integer numberOfPlayer, List<Player> players, List<Player> players2, String nickname) throws IOException {

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


        frameSize.setSize(width, height);
        internalFrameSize90x90.setSize(frameSize.width * 90/100, frameSize.height * 90/100);
        internalFrameSize80x80.setSize(frameSize.width * 80/100, frameSize.height * 80/100);
        internalFrameSize70x70.setSize(internalFrameSize90x90.width * 70/100, internalFrameSize90x90.height * 70/100);
        internalFrameSize40x45.setSize(frameSize.width * 40/100, frameSize.height * 45/100);


        boardSize.setSize(frameSize.height * 80/100, frameSize.height * 80/100);
        bottomSize.setSize(frameSize.width, frameSize.height * 20/100);
        sideSize.setSize(width * 27/100, height);

        scrollSize.setSize(sideSize.getWidth() * 14/100 , sideSize.getHeight() * 28/100);
        labelMapSize.setSize(height * 13/100, height * 13/100);
        buttonSize.setSize((getD().getWidth() * 13 / 100), (getD().getHeight() * 5 / 100));
        buttonMapSize13x13.setSize(frameSize.height * 13/100, frameSize.height * 13/100);
        buttonSize7x7.setSize(frameSize.width * 7/100, frameSize.height * 7/100);
        buttonSize5x5.setSize(frameSize.width * 5/100, frameSize.width * 5/100);
        size20x5.setSize(frameSize.width * 20/100, frameSize.width * 5/100);

        JLabel coverBoard1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "board.png", 100, 100, width, height);
        coverBoard = new JLabel(coverBoard1.getIcon());

        JLabel coverChat1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "panel_chat.png", 100, 100, sideSize.width, sideSize.height);
        coverChat = new JLabel(coverChat1.getIcon());

        JLabel coverBuildings1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "panel_buildings.png", 100, 100, sideSize.width, sideSize.height);
        coverBuildings = new JLabel(coverBuildings1.getIcon());

        coverBackground = ImageHandler.setImage(RESOURCES_GRAPHICS + "background2.png", 100, 100, internalFrameSize40x45.width, internalFrameSize40x45.height);
        background = new JLabel(coverBackground.getIcon());

        JLabel coverLeftBoard1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "left_board.png", 100, 100, frameSize.width, frameSize.height);
        coverLeftBoard = new JLabel(coverLeftBoard1.getIcon());

        JLabel coverLeftGod1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "left_god_board.png", 100, 100, frameSize.width, frameSize.height);
        coverLeftGod = new JLabel(coverLeftGod1.getIcon());


        felixSmall = new Font(Gui.FELIX, Font.PLAIN, (int) (13 * screenSize.getHeight() / 1080));
        felixNormal = new Font(Gui.FELIX, Font.PLAIN, (int) (20 * screenSize.getHeight() / 1080));
        felixBold = new Font(Gui.FELIX, Font.BOLD, (int) (25 * screenSize.getHeight() / 1080));


        workerCyan = ImageHandler.setImage(RESOURCES_GRAPHICS + "worker_cyan.png", 100, 100, labelMapSize.width, labelMapSize.height);
        workerWhite = ImageHandler.setImage(RESOURCES_GRAPHICS + "worker_white.png", 100, 100, labelMapSize.width, labelMapSize.height);
        workerPurple = ImageHandler.setImage(RESOURCES_GRAPHICS + "worker_purple.png", 100, 100, labelMapSize.width, labelMapSize.height);
        lvl1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl1.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2 = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl2.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3 = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl3.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1Building = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl1.png", 95, 95, buttonSize5x5.width, buttonSize5x5.height);
        lvl2Building = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl2.png", 95, 95, buttonSize5x5.width, buttonSize5x5.height);
        lvl3Building = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl3.png", 95, 95, buttonSize5x5.width, buttonSize5x5.height);
        domeBuilding = ImageHandler.setImage(RESOURCES_GRAPHICS + "dome.png", 95, 95, buttonSize5x5.width, buttonSize5x5.height);
        lvl1Cyan = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl1_cyan.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2Cyan = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl2_cyan.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3Cyan = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl3_cyan.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1Purple = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl1_purple.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2Purple = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl2_purple.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3Purple = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl3_purple.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1White = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl1_white.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2White = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl2_white.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3White = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl3_white.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl1Dome = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl1_dome.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl2Dome = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl2_dome.png", 85, 85, labelMapSize.width, labelMapSize.height);
        lvl3Dome = ImageHandler.setImage(RESOURCES_GRAPHICS + "lvl3_dome.png", 85, 85, labelMapSize.width, labelMapSize.height);
        dome = ImageHandler.setImage(RESOURCES_GRAPHICS + "dome.png", 85, 85, buttonSize5x5.width, buttonSize5x5.height);
        exit = ImageHandler.setImage(RESOURCES_GRAPHICS + "exit.png", 75, 75, sideSize.width * 12/100, sideSize.width * 12/100);
        lButtonBuild = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_build.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonMove = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_move.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonPower = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_power.png", 100, 100, frameSize.width * 5/100, frameSize.height * 5/100);
        lButtonPowerPing = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_power_ping.png", 100, 100, frameSize.width * 5/100, frameSize.height * 5/100);
        lButtonChat = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_chat.png", 100, 100, frameSize.width * 5/100, frameSize.height * 7/100);
        lButtonChatPing = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_chat_ping.png", 100, 100, frameSize.width * 5/100, frameSize.height * 7/100);
        lButtonChooseCards = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_choose_cards.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonChooseFirst = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_choose_first.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonChoosePower = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_power.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonEndTurn = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_endturn.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonBuildPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_build_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonMovePress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_move_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonPowerPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_power_press.png", 100, 100, frameSize.width * 5/100, frameSize.height * 5/100);
        lButtonPowerPressPing = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_power_press_ping.png", 100, 100, frameSize.width * 5/100, frameSize.height * 5/100);
        lButtonChatPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_chat_press.png", 100, 100, frameSize.width * 5/100, frameSize.height * 7/100);
        lButtonChatPressPing = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_chat_press_ping.png", 100, 100, frameSize.width * 5/100, frameSize.height * 7/100);
        lButtonChooseCardsPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_choose_cards_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonChooseFirstPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_choose_first_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonChoosePowerPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_power_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        lButtonEndTurnPress = ImageHandler.setImage(RESOURCES_GRAPHICS + "button_endturn_press.png", 100, 100, buttonSize7x7.width, buttonSize7x7.height);
        border = ImageHandler.setImage(LOSEBORDER, 100, 100, frameSize.width * 50/100, frameSize.height * 80/100);
        lGlow = ImageHandler.setImage(PODIUM + "glow.png", 100, 100, frameSize.width * 15/100, frameSize.height * 35/100);
        lTutorial = ImageHandler.setImage(RESOURCES_GRAPHICS + "tutorial.png", 100, 100, frameSize.width, frameSize.height);
        lTutorial1 = ImageHandler.setImage(RESOURCES_GRAPHICS + "tutorial1.png", 100, 100, frameSize.width, frameSize.height);
        lTutorial2 = ImageHandler.setImage(RESOURCES_GRAPHICS + "tutorial2.png", 100, 100, frameSize.width, frameSize.height);

        loopSound = new MP3(MUSIC + "Atlantis.mp3");
        place = new MP3(MUSIC + "Place.mp3");
        build = new MP3(MUSIC + "Build.mp3");
        win = new MP3(MUSIC + "win.mp3");
        lose = new MP3(MUSIC + "lose.mp3");
        yourTurn = new MP3(MUSIC + "your_turn.mp3");
        tower = new MP3(MUSIC + "complete_tower1.mp3");



        setMyColorWorkers();
        setColorWorkers1();

        windowPower = new JWindow();
        windowPower.setBounds((int)(frameSize.width * 35.5/100), (frameSize.height * 37/100), internalFrameSize40x45.width, internalFrameSize40x45.height);

        setDimensionInternalFrames(internalFrameChallenger1);
        BasicInternalFrameUI bi = (BasicInternalFrameUI) internalFrameChallenger1.getUI();
        bi.setNorthPane(null);
        setDimensionInternalFrames(internalFrameChallenger2);
        BasicInternalFrameUI bi2 = (BasicInternalFrameUI) internalFrameChallenger2.getUI();
        bi2.setNorthPane(null);
        setDimensionInternalFrames(internalFrameChooseCards);
        BasicInternalFrameUI bi3 = (BasicInternalFrameUI) internalFrameChooseCards.getUI();
        bi3.setNorthPane(null);
        setDimensionInternalFrames(internalFramePlaceWorkers);
        BasicInternalFrameUI bi4 = (BasicInternalFrameUI) internalFrameChooseCards.getUI();
        bi4.setNorthPane(null);
        setDimensionInternalFrames(internalFrameStartTurn);
        BasicInternalFrameUI bi5 = (BasicInternalFrameUI) internalFrameStartTurn.getUI();
        bi5.setNorthPane(null);
        setDimensionInternalFrames(internalFrameUpdateBoard);
        BasicInternalFrameUI bi6 = (BasicInternalFrameUI) internalFrameUpdateBoard.getUI();
        bi6.setNorthPane(null);
        setDimensionInternalFrames(internalFrameConstraint);
        BasicInternalFrameUI bi7 = (BasicInternalFrameUI) internalFrameConstraint.getUI();
        bi7.setNorthPane(null);

        internalFrameConstraint.setPreferredSize(internalFrameSize70x70);


        frameChat.setPreferredSize(sideSize);
        frameChat.setBounds(frameSize.width * 73/100, -25, sideSize.width, sideSize.height);
        internalFrameSetUp(frameChat);
        BasicInternalFrameUI bii = (BasicInternalFrameUI)frameChat.getUI();
        bii.setNorthPane(null);

        chatDimension.setSize(frameChat.getWidth() * 60/100, frameChat.getHeight() * 35/100);

        frameBuildings.setPreferredSize(sideSize);
        frameBuildings.setBounds(frameSize.width * 73/100, -25, sideSize.width, sideSize.height);
        internalFrameSetUp(frameBuildings);
        BasicInternalFrameUI bii2 = (BasicInternalFrameUI)frameBuildings.getUI();
        bii2.setNorthPane(null);


        desktopPane = new JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage( ((ImageIcon)(Board.this.coverBoard.getIcon())).getImage(), -7, -18, null);
            }};

        desktopPane.setPreferredSize(frameSize);

        newGame.setBounds(((frameSize.width * 40/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
        newGame.setVisible(false);
        close.setBounds(((frameSize.width * 58/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
        close.setVisible(false);
        close.addActionListener(new Close());
        keepWatching.setBounds(((frameSize.width * 50/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
        keepWatching.setVisible(false);
        keepWatching.addActionListener(new KeepWatching());

        closeTutorial.setBounds(((frameSize.width * 40/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
        closeTutorial.setVisible(false);
        closeTutorial.addActionListener(new CloseTutorial());
        continueTutorial.setBounds(((frameSize.width * 58/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
        continueTutorial.setVisible(false);
        continueTutorial.addActionListener(new ContinueTutorial());

        tutorial.setBounds(-7,-22, frameSize.width, frameSize.height);
        tutorial.setOpaque(false);
        tutorial.setContentAreaFilled(false);
        tutorial.setBorderPainted(false);
        tutorial.setIcon(lTutorial.getIcon());
        tutorial.setVisible(false);

        tutorial1.setBounds(-7,-22, frameSize.width, frameSize.height);
        tutorial1.setOpaque(false);
        tutorial1.setContentAreaFilled(false);
        tutorial1.setBorderPainted(false);
        tutorial1.setIcon(lTutorial1.getIcon());
        tutorial1.setVisible(false);

        tutorial2.setBounds(-7,-22, frameSize.width, frameSize.height);
        tutorial2.setOpaque(false);
        tutorial2.setContentAreaFilled(false);
        tutorial2.setBorderPainted(false);
        tutorial2.setIcon(lTutorial2.getIcon());
        tutorial2.setVisible(false);

        winner.setBounds((frameSize.width * 39/100),frameSize.height * 20/100, frameSize.width * 30/100, frameSize.height * 50/100);
        winner.setOpaque(false);
        winner.setContentAreaFilled(false);
        winner.setBorderPainted(false);
        winner.setVisible(false);

        glow.setBounds((int) (frameSize.width * 46.5/100),frameSize.height * 33/100, frameSize.width * 15/100, frameSize.height * 35/100);
        glow.setOpaque(false);
        glow.setContentAreaFilled(false);
        glow.setBorderPainted(false);
        glow.setIcon(lGlow.getIcon());
        glow.setVisible(false);

        loser1.setBounds((frameSize.width * 29/100),frameSize.height * 30/100, frameSize.width * 30/100, frameSize.height * 50/100);
        loser1.setOpaque(false);
        loser1.setContentAreaFilled(false);
        loser1.setBorderPainted(false);
        loser1.setVisible(false);

        loser2.setBounds((frameSize.width * 44/100),frameSize.height * 30/100, frameSize.width * 30/100, frameSize.height * 50/100);
        loser2.setOpaque(false);
        loser2.setContentAreaFilled(false);
        loser2.setBorderPainted(false);
        loser2.setVisible(false);

        winLose.setBounds(-7,-22, frameSize.width, frameSize.height);
        winLose.setOpaque(false);
        winLose.setContentAreaFilled(false);
        winLose.setBorderPainted(false);
        winLose.setIcon(border.getIcon());
        winLose.setVisible(false);

        leftGod.setBounds(-7,-22, frameSize.width, frameSize.height);
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


        lLvl1.setFont(felixNormal);
        lLvl1.setVisible(true);
        frameBuildings.add(lLvl1);
        buttonLvl1.setBounds((frameChat.getWidth() * 55/ 100), (frameChat.getHeight() * 21 / 100), buttonSize5x5.width, buttonSize5x5.height);
        buttonLvl1.setVisible(true);
        chatStyleButtons(buttonLvl1, lvl1Building);
        frameBuildings.add(buttonLvl1);

        lLvl2.setFont(felixNormal);
        lLvl2.setVisible(true);
        frameBuildings.add(lLvl2);
        buttonLvl2.setBounds((frameChat.getWidth() * 55/ 100), (frameChat.getHeight() * 33 / 100), buttonSize5x5.width, buttonSize5x5.height);
        buttonLvl2.setVisible(true);
        chatStyleButtons(buttonLvl2, lvl2Building);
        frameBuildings.add(buttonLvl2);

        lLvl3.setFont(felixNormal);
        lLvl3.setVisible(true);
        frameBuildings.add(lLvl3);
        buttonLvl3.setBounds((frameChat.getWidth() * 55/ 100), (frameChat.getHeight() * 45 / 100), buttonSize5x5.width, buttonSize5x5.height);
        buttonLvl3.setVisible(true);
        chatStyleButtons(buttonLvl3, lvl3Building);
        frameBuildings.add(buttonLvl3);

        lDome.setFont(felixNormal);
        lDome.setVisible(true);
        frameBuildings.add(lDome);
        buttonDome.setBounds((frameChat.getWidth() * 55/ 100), (frameChat.getHeight() * 58 / 100), buttonSize5x5.width, buttonSize5x5.height);
        buttonDome.setVisible(true);
        chatStyleButtons(buttonDome, domeBuilding);
        frameBuildings.add(buttonDome);

        backButton.setBounds((int) (((double)frameChat.getWidth() * 55/ 100) - (getD().getWidth() * 6 / 100)), (frameChat.getHeight() * 71 / 100), (int) (getD().getWidth() * 13 / 100), (int) (getD().getHeight() * 5 / 100));
        frameBuildings.add(backButton);
        backButton.addActionListener(new BackLevel());

        chat = new JTextPane(){
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return getUI().getPreferredSize(this).width <= getParent().getSize().width;
            }
        };

        doc = chat.getStyledDocument();

        chat.setSize(new Dimension(200,200));
        chat.setPreferredSize(chatDimension);
        chat.setBounds(frameChat.getWidth() * 23/100 , frameChat.getHeight() * 28/100, frameChat.getWidth() * 63/100, frameChat.getHeight() * 38/100);
        chat.setEditable(false);
        chat.setBackground(new Color(232, 222, 208));
        chat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        chat.setFont(felixNormal);
        chat.setVisible(true);

        scrollPane = new JScrollPane(chat);
        scrollPane.setPreferredSize(chatDimension);
        scrollPane.setBounds(frameChat.getWidth() * 23/100 , frameChat.getHeight() * 28/100, frameChat.getWidth() * 63/100, frameChat.getHeight() * 38/100);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(chat);
        frameChat.add(scrollPane);

        colorStyle = chat.addStyle("colorStyle", null);
        StyleConstants.setAlignment(colorStyle, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), colorStyle, false);


        field.setBounds((int) (frameChat.getWidth() * 23.4/100), frameChat.getHeight() * 66/100, (int) (frameChat.getWidth() * 62.8/100), frameChat.getHeight() * 4/100);
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

        chatStyleButtons(backgroundFrameChat, coverChat);
        frameChat.getContentPane().add(backgroundFrameChat);

        chatStyleButtons(backgroundFrameBuildings, coverBuildings);
        frameBuildings.getContentPane().add(backgroundFrameBuildings);

        chatStyleButtons(backgroundFramePower, background);
        windowPower.getContentPane().add(backgroundFramePower);


        resetPlayer();

        resetMyWorkers();

        mapStyleButtons();

        enableLevels(false);

        nicknameLabel.setBounds((int) (frameSize.width * 3.5/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);
        opponents.setBounds((frameSize.width * 2/100), (frameSize.height * 55/100), size20x5.width, size20x5.height);
        opponent1.setBounds((frameSize.width * 3/100), (frameSize.height * 61/100), frameSize.width * 15/100, frameSize.height * 4/100);
        if (numberOfPlayers == 3){
            opponent2.setBounds((frameSize.width * 3/100), (frameSize.height * 64/100), frameSize.width * 15/100, frameSize.height * 4/100);
        }


        lLvl1.setBounds((sideSize.width * 35/100), (sideSize.height * 21/100), size20x5.width, size20x5.height);
        lLvl2.setBounds((sideSize.width * 35/100), (sideSize.height * 33/100), size20x5.width, size20x5.height);
        lLvl3.setBounds((sideSize.width * 35/100), (sideSize.height * 45/100), size20x5.width, size20x5.height);
        lDome.setBounds((sideSize.width * 35/100), (sideSize.height * 58/100), size20x5.width, size20x5.height);

        try{
            String os = System.getProperty("os.name").toLowerCase();

            if(os.contains("win")) { //windows positions

                nicknameLabel1.setBounds((int) (frameSize.width * 10.3/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);

                labelChooseCards.setBounds((int) (frameSize.width * 82.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseFirst.setBounds((frameSize.width * 80/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelChoosePower.setBounds((frameSize.width * 81/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelEndTurn.setBounds((int) (frameSize.width * 84.25/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelSeePower.setBounds((int) (frameSize.width * 78.75/100), (int) (frameSize.height * 52.75/100), size20x5.width, size20x5.height);
                labelConfirmPlace.setBounds((frameSize.width * 80/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseWorker.setBounds((frameSize.width * 81/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
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
                nicknameLabel1.setBounds((int) (frameSize.width * 10.5/100), (int) (frameSize.height * 2.5/100), size20x5.width, size20x5.height);

                labelChooseCards.setBounds((frameSize.width * 83/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseFirst.setBounds((int) (frameSize.width * 80.5/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelChoosePower.setBounds((int) (frameSize.width * 81.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelEndTurn.setBounds((int) (frameSize.width * 84.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelSeePower.setBounds((int) (frameSize.width * 79.25/100), (int) (frameSize.height * 52.75/100), size20x5.width, size20x5.height);
                labelConfirmPlace.setBounds((int) (frameSize.width * 80.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelChooseWorker.setBounds((int) (frameSize.width * 81.5/100), (int) (frameSize.height * 15.5/100), size20x5.width, size20x5.height);
                labelMove.setBounds((int) (frameSize.width * 85.87/100), (int) (frameSize.height * 27.5/100), size20x5.width, size20x5.height);
                labelBuild.setBounds((int) (frameSize.width * 85.87/100), (int) (frameSize.height * 39.5/100), size20x5.width, size20x5.height);

                windowPower.setBounds((int)(frameSize.width * 29.65/100), (int) (frameSize.height * 30.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);


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

        desktopPane.add(lLost);

        desktopPane.add(closeTutorial);
        desktopPane.add(continueTutorial);
        desktopPane.add(tutorial);
        desktopPane.add(tutorial1);
        desktopPane.add(tutorial2);
        desktopPane.add(newGame);
        desktopPane.add(close);
        desktopPane.add(keepWatching);
        desktopPane.add(nicknameLabel);
        desktopPane.add(nicknameLabel1);
        desktopPane.add(opponents);
        desktopPane.add(opponent1);
        if (numberOfPlayers == 3){
            desktopPane.add(opponent2);
        }

        loopSound.playLoop();

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
        buttonChooseCards.setBounds((frameSize.width * 84/100), (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonChooseCards, lButtonChooseCards);


        labelChooseFirst.setFont(felixNormal);
        labelChooseFirst.setVisible(false);
        desktopPane.add(labelChooseFirst);
        buttonChooseFirst.setBounds((frameSize.width * 84/100), (frameSize.height * 34/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonChooseFirst, lButtonChooseFirst);

        labelChoosePower.setFont(felixNormal);
        labelChoosePower.setVisible(false);
        desktopPane.add(labelChoosePower);
        buttonChoosePower.setBounds((frameSize.width * 84/100), (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonChoosePower, lButtonChoosePower);
        buttonChoosePower.setVisible(false);


        labelConfirmPlace.setFont(felixNormal);
        labelConfirmPlace.setVisible(false);
        desktopPane.add(labelConfirmPlace);
        buttonMultiUse.setBounds((frameSize.width * 84/100), (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonMultiUse, lButtonMove);

        labelChooseWorker.setFont(felixNormal);
        labelChooseWorker.setVisible(false);
        desktopPane.add(labelChooseWorker);


        labelEndTurn.setFont(felixNormal);
        labelEndTurn.setVisible(false);
        desktopPane.add(labelEndTurn);
        buttonEndTurn.setBounds((frameSize.width * 84/100), (frameSize.height * 22/100), buttonSize7x7.width, buttonSize7x7.height);
        consoleStyleButtons(buttonEndTurn, lButtonEndTurn);
        buttonEndTurn.addActionListener(new EndTurn());



        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setPreferredSize(frameSize);


        frameChat.setVisible(false);
        desktopPane.add(frameChat);
        frameBuildings.setVisible(false);
        desktopPane.add(frameBuildings);
        desktopPane.add(internalFrameChallenger1);
        desktopPane.add(internalFrameChallenger2);
        desktopPane.add(internalFrameChooseCards);
        desktopPane.add(internalFramePlaceWorkers);
        desktopPane.add(internalFrameStartTurn);
        desktopPane.add(internalFrameUpdateBoard);
        desktopPane.add(internalFrameConstraint);


        desktopPane.add(winner);
        desktopPane.add(glow);
        desktopPane.add(loser1);
        desktopPane.add(loser2);
        desktopPane.add(winLose);

        addMapButtons();

        desktopPane.add(leftBoard);
        desktopPane.add(leftGod);

        f.setContentPane(desktopPane);

        SwingUtilities.updateComponentTreeUI(f);
        f.pack();

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }

    /**
     * Method that stop the playground music
     */

    public void stopMusic(){
        loopSound.stop();
    }

    /**
     * Class that implements ActionListener that makes the Buildings pane disappear
     */

    private class BackLevel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameBuildings.setVisible(false);
            removeBuildLvl();
            enableLevels(false);
        }
    }

    /**
     * Method that gives the color of the Player provided
     * @param player Player provided
     * @return Color of the Player
     */

    private Color getColorPlayer(Player player){
        if(player.getColor().equals(it.polimi.ingsw.model.player.Color.BLUE)){
            return Color.BLUE;
        }
        else if(player.getColor().equals(it.polimi.ingsw.model.player.Color.WHITE)){
            return Color.WHITE;
        }
        else {
            return Color.MAGENTA;
        }
    }

    /**
     * Method that gives the color of the opponent name Player provided
     * @param player Name of the opponent Player
     * @return The color of the opponent name Player provided
     */

    private Color getColorOpponent(String player){
        if(player.equals(otherPlayers.get(0).getNickName())){
            return getColorPlayer(otherPlayers.get(0));
        }
        else if(player.equals(otherPlayers.get(1).getNickName())){
            return getColorPlayer(otherPlayers.get(1));
        }
        else {
            return null;
        }
    }

    /**
     * Method that set the client player icons according to his color
     */

    private void setMyColorWorkers(){
        if (myColor.equals(it.polimi.ingsw.model.player.Color.BLUE)){
            worker = workerCyan;
            lvl1Worker = lvl1Cyan;
            lvl2Worker = lvl2Cyan;
            lvl3Worker = lvl3Cyan;
        }
        else if (myColor.equals(it.polimi.ingsw.model.player.Color.WHITE)){
            worker = workerWhite;
            lvl1Worker = lvl1White;
            lvl2Worker = lvl2White;
            lvl3Worker = lvl3White;
        }
        else if (myColor.equals(it.polimi.ingsw.model.player.Color.PURPLE)){
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

    /**
     * Method that set the first opponent player icons according to his color
     */

    private void setColorWorkers1(){
        if (colorOpponent1.equals(it.polimi.ingsw.model.player.Color.BLUE)){
            workerOpponents1 = workerCyan;
            lvl1WorkerOpponents1 = lvl1Cyan;
            lvl2WorkerOpponents1 = lvl2Cyan;
            lvl3WorkerOpponents1 = lvl3Cyan;
        }
        else if (colorOpponent1.equals(it.polimi.ingsw.model.player.Color.WHITE)){
            workerOpponents1 = workerWhite;
            lvl1WorkerOpponents1 = lvl1White;
            lvl2WorkerOpponents1 = lvl2White;
            lvl3WorkerOpponents1 = lvl3White;
        }
        else if (colorOpponent1.equals(it.polimi.ingsw.model.player.Color.PURPLE)){
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

    /**
     * Method that set the second opponent player icons according to his color
     */

    private void setColorWorkers2(){
        if (colorOpponent2.equals(it.polimi.ingsw.model.player.Color.BLUE)){
            workerOpponents2 = workerCyan;
            lvl1WorkerOpponents2 = lvl1Cyan;
            lvl2WorkerOpponents2 = lvl2Cyan;
            lvl3WorkerOpponents2 = lvl3Cyan;
        }
        else if (colorOpponent2.equals(it.polimi.ingsw.model.player.Color.WHITE)){
            workerOpponents2 = workerWhite;
            lvl1WorkerOpponents2 = lvl1White;
            lvl2WorkerOpponents2 = lvl2White;
            lvl3WorkerOpponents2 = lvl3White;
        }
        else if (colorOpponent2.equals(it.polimi.ingsw.model.player.Color.PURPLE)){
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

    /**
     * Method that gives the icon according to the Building provided
     * @param list List of icon
     * @param build Building provided
     * @return Icon from the Building
     */

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

    /**
     * Method that set the dimension of the JInternalFrame provided
     * @param intFrame JInternalFrame provided
     */

    private void setDimensionInternalFrames(JInternalFrame intFrame){
        intFrame.setPreferredSize(sideSize);
        intFrame.setBounds(((frameSize.width * 50/100) - (internalFrameSize90x90.width * 50/100)), ((frameSize.height * 46/100) - (internalFrameSize90x90.height * 50/100)), internalFrameSize90x90.width, internalFrameSize90x90.height);
        internalFrameSetUp(intFrame);
    }

    /**
     * Method that set the JInternalFrame provided
     * @param intFrame JInternalFrame provided
     */

    public static void internalFrameSetUp(JInternalFrame intFrame){
        intFrame.putClientProperty(PALETTE, Boolean.TRUE);
        intFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        intFrame.setOpaque(false);
        intFrame.putClientProperty(PALETTE, Boolean.TRUE);
        intFrame.setBorder(null);
    }

    /**
     * Method that set the Opponent Player button provided
     * @param button Opponent Player button provided
     */

    private void opponentsStyleButtons(JButton button){
        button.setFont(felixNormal);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    /**
     * Method that set the button with the icon provided
     * @param button Button provided
     * @param label Icon provided
     */

    private void chatStyleButtons(JButton button, JLabel label){
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setIcon(label.getIcon());
    }

    /**
     * Method that set the style for the console buttons with the icon provided
     * @param button Button provided
     * @param label Icon provided
     */

    private void consoleStyleButtons(JButton button, JLabel label){
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(label.getIcon());
        button.addMouseListener(new ButtonPress());
        button.setVisible(false);
        desktopPane.add(button);
    }

    /**
     * Method that set the board buttons
     */

    private void mapStyleButtons(){
        for (int x = 0; x < 25; x++){
            mapButtons[x] = new JButton();
            mapButtons[x].setContentAreaFilled(false);
            mapButtons[x].setOpaque(false);
            mapButtons[x].setBorderPainted(false);
        }
    }

    /**
     * Method that add the ColorBorder MouseListener to the board buttons
     */

    private void addColorBorderToMap(){
        for (int x = 0; x < 25; x++){
            mapButtons[x].addMouseListener(new ColorBorder());
        }
    }

    /**
     * Method that remove the border from the board buttons
     */

    private void removeModifiedBorder(){
        for (int x = 0; x < 25; x++){
            mapButtons[x].setBorderPainted(false);
        }
    }

    /**
     * Method that remove the ColorBorder MouseListener to the board buttons
     */

    private void removeColorBorderToMap(){
        for (int x = 0; x < 25; x++){
            eliminateMouseClass(mapButtons[x], ColorBorder.class);
        }
    }

    /**
     * Method that set up the board buttons
     */

    private void addMapButtons(){
        for (int x = 0; x < 25; x++){
            desktopPane.add(mapButtons[x]);
            mapButtons[x].setName(String.valueOf(x));
        }
    }

    /**
     * Method that reset the presence of Players on the Board
     */

    private void resetPlayer(){
        for (int x = 0; x < 25; x++){
            mapButtonsPlayer[x] = false;
        }
    }

    /**
     * Method that reset the presence of the client Workers on the Board
     */

    private void resetMyWorkers(){
        for (int x = 0; x < 25; x++){
            mapMyWorkers[x] = 0;
        }
    }

    /**
     * Method that remove the client Player from the list otherPlayers
     */

    private void removeNickFromOtherPlayers(){
        otherPlayers.removeIf(player -> player.getNickName().equalsIgnoreCase(nickname));
    }

    /**
     * Method that pick the client Player from the list otherPlayers
     * @return Client Player
     */

    private Player pickNickFromPlayers(){
        for (Player player : otherPlayers){
            if (player.getNickName().equalsIgnoreCase(nickname))
                return player;
        }
        return null;
    }

    /**
     * Method that visualize the phase message of Challenger choosing
     * @param name Name of the designated Challenger
     * @param bool Boolean saying if the client Player is the Challenger
     */

    public void showChallenger(String name, boolean bool) {
        if (bool){
            try {
                youChosen = new YouHaveBeenChosen(this, internalFrameChallenger1, internalFrameSize70x70);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            internalFrameChallenger1.setBounds(((frameSize.width * 50/100) - (internalFrameSize70x70.width * 50/100)), ((frameSize.height * 46/100) - (internalFrameSize70x70.height * 50/100)), internalFrameSize70x70.width, internalFrameSize70x70.height);
            internalFrameChallenger1.getContentPane().add(youChosen);
            tutorial.setVisible(true);
            closeTutorial.setVisible(true);
            continueTutorial.setVisible(true);
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
            tutorial.setVisible(true);
            closeTutorial.setVisible(true);
            continueTutorial.setVisible(true);
        }
    }

    /**
     * Method that enable/disable the buttonChooseCards and the buttonChooseFirst
     * @param bool Boolean saying to enable or disable the buttons
     */

    public void enableCardsFirst(boolean bool){
        buttonChooseCards.setEnabled(bool);
        buttonChooseFirst.setEnabled(bool);
    }

    /**
     * Class that implements ActionListener that visualize the JInternalFrame that make the Challenger choose the gods
     */

    private class ChooseCards implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrameChallenger1.remove(youChosen);
            try {
                challengerChoiceCards = new ChallengerChoiceCards(gui, gui.board, internalFrameChallenger1, internalFrameSize90x90, numberOfPlayers);
            } catch (IOException ie) {
                LOGGER.severe(ie.getMessage());
            }
            internalFrameChallenger1.setBounds(((frameSize.width * 50/100) - (internalFrameSize90x90.width * 50/100)), ((frameSize.height * 46/100) - (internalFrameSize90x90.height * 50/100)), internalFrameSize90x90.width, internalFrameSize90x90.height);
            internalFrameChallenger1.getContentPane().add(challengerChoiceCards);
            internalFrameChallenger1.setVisible(true);
        }
    }

    /**
     * Class that implements ActionListener that visualize the JInternalFrame that make the Challenger choose the First Player
     */

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

    /**
     * Setter of the List of gods chosen
     * @param cardsChosen List of God chosen
     */

    public void setCardsChosen(List<String> cardsChosen) {
        this.cardsChosen = cardsChosen;
    }

    /**
     * Setter of the First Player chosen
     * @param firstPlayer First Player chosen
     */

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    /**
     * Method that communicate the choices made by the Challenger
     */

    public void callChallengerResponse(){
        if (!cardsChosen.isEmpty() && firstPlayer != null){
            gui.challengerResponse(firstPlayer, cardsChosen);
            buttonChooseCards.setVisible(false);
            labelChooseCards.setVisible(false);
            buttonChooseFirst.setVisible(false);
            labelChooseFirst.setVisible(false);
            setVisibleEndTurn(true);
        }
    }

    /**
     * Method that visualize the phase message Choose your god
     * @param cards List of gods that can be selected
     * @param name Name of the Player that is actually choosing
     * @param bool Boolean saying if the client Player is the one who has to choose
     */

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

    /**
     * Method that enable/disable the buttonChoosePower
     * @param bool Boolean saying to enable or disable the button
     */

    public void enablePower(boolean bool){
        buttonChoosePower.setEnabled(bool);
    }

    /**
     * Method that communicate the god chosen by the client Player
     */

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
                coverLeftGod = ImageHandler.setImage(GODS + cardChosen + "_left.png", 100, 100, frameSize.width, frameSize.height);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            leftGod.setIcon(coverLeftGod.getIcon());
            setVisibleEndTurn(true);
        }
    }

    /**
     * Method that set the god chosen
     * @param cardChosen God chosen
     */

    public void setCardChosen(String cardChosen) {
        this.cardChosen = cardChosen;
        constraint.add(cardChosen);
    }

    /**
     * Class that implements ActionListener that visualize the JInternalFrame that make the Player choose the god
     */

    private class ChoosePower implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            internalFrameChooseCards.remove(chooseCard);
            try {
                chooseCard = new ChooseCard(gui.board, internalFrameChooseCards, internalFrameSize90x90.width, internalFrameSize90x90.height, godCards, godCards.size(), nameChoosing);
            } catch (IOException ie) {
                LOGGER.severe(ie.getMessage());
            }
            internalFrameChooseCards.setBounds(((frameSize.width * 50/100) - (internalFrameSize90x90.width * 50/100)), ((frameSize.height * 46/100) - (internalFrameSize90x90.height * 50/100)), internalFrameSize90x90.width, internalFrameSize90x90.height);
            internalFrameChooseCards.getContentPane().add(chooseCard);
            internalFrameChooseCards.setVisible(true);
        }
    }

    /**
     * Class that implements ActionListener that visualize the JInternalFrame that show the god chosen
     */

    private class ShowYourPower implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton)e.getSource();
            eliminateActionClass(c, ShowYourPower.class);
            try {
                coverBackground = ImageHandler.setImage(GODS + c.getName() + DESCRIPTION, 100, 100, internalFrameSize40x45.width, internalFrameSize40x45.height);
            } catch (IOException ioException) {
                LOGGER.severe(ioException.getMessage());
            }
            c.setIcon(lButtonPowerPress.getIcon());
            background = new JLabel(coverBackground.getIcon());
            windowPower.getContentPane().removeAll();
            chatStyleButtons(backgroundFramePower, background);
            windowPower.getContentPane().add(backgroundFramePower);
            c.addActionListener(new HidePower());
            windowPower.setVisible(true);
        }
    }

    /**
     * Class that implements ActionListener that hide the JInternalFrame that show the god chosen
     */

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

    /**
     * Method that visualize the phase message Place your workers
     * @param name Name of the Player that is placing the workers
     * @param bool Boolean saying if the client Player is the one who has to place the workers
     */

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
            addColorBorderToMap();
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

    /**
     * Method that add the PlaceWorker ActionListener to the board buttons
     */

    private void addPlaceMove(){
        for (int x = 0; x < 25; x++){
            if (!mapButtonsPlayer[x])
                mapButtons[x].addActionListener(new PlaceWorker());
        }
    }

    /**
     * Class that implements ActionListener that place/remove the worker in the Board position chosen
     */

    private class PlaceWorker implements ActionListener{
        int x;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (placed <= 2) {

                JButton c = (JButton) e.getSource();
                x = Integer.parseInt(c.getName());

                if (worker1 == 0){
                    if (worker2 == 0) {
                        placing(c, x, 1);
                        eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                    }

                    else if (worker2 == 2){
                        if (!mapButtonsPlayer[x] && placed < 2) {
                            placing(c, x, 1);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }

                        else if (mapButtonsPlayer[x]){
                            removing(c, x, 2);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                    }
                }
                else if (worker1 == 1){
                    if (worker2 == 0){
                        if (!mapButtonsPlayer[x] && placed < 2) {
                            placing(c, x, 2);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }

                        else if (mapButtonsPlayer[x]){
                            removing(c, x, 1);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                    }
                    else if (worker2 == 2){
                        if (mapMyWorkers[x] == 1) {
                            removing(c, x, 1);
                            eliminateActionClass(buttonMultiUse, ConfirmPlace.class);
                        }
                        else if (mapMyWorkers[x] == 2){
                            removing(c, x, 2);
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

        private void placing(JButton c, int pos, int number){
            place.play();
            c.setIcon(worker.getIcon());
            mapButtonsPlayer[pos] = true;
            placed++;
            mapMyWorkers[pos] = number;
            if (number == 1) {
                worker1 = number;
            }
            else if (number == 2){
                worker2 = number;
            }
        }

        private void removing(JButton c, int pos, int number){
            c.setIcon(null);
            mapButtonsPlayer[pos] = false;
            placed--;
            mapMyWorkers[pos] = 0;
            if (number == 1) {
                worker1 = 0;
            }
            else if (number == 2){
                worker2 = 0;
            }
            buttonMultiUse.setEnabled(false);
        }
    }

    /**
     * Class that implements ActionListener that confirm the workers positions chosen
     */

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
            setVisibleEndTurn(true);
        }

        private List<Integer> modifiedTiles(){
            boolean exitFor;
            List<Integer> tiles = new ArrayList<>();
            for (int y = 1; y < 3; y++) {
                exitFor = false;
                for (int x = 0; x < 25 && !exitFor; x++) {
                    if (mapMyWorkers[x] == y) {
                        tiles.add(x);
                        exitFor = true;
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
    }

    /**
     * Method that update the position of the workers positioned
     * @param squares Positions of the placed workers
     */

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

    /**
     * Method that set the Opponent Player god in his opponentButton
     * @param player Opponent Player
     * @param card Opponent Player god
     */

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

    /**
     * Class that extends MouseAdapter that show the description of the Opponent god as the mouse cursor moves over the opponentButton
     */

    private class SeeEnemyPower extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton c = (JButton)e.getSource();
            try {
                coverBackground = ImageHandler.setImage(GODS + c.getName() + DESCRIPTION, 100, 100, internalFrameSize40x45.width, internalFrameSize40x45.height);
            } catch (IOException ioException) {
                LOGGER.severe(ioException.getMessage());
            }
            background = new JLabel(coverBackground.getIcon());
            windowPower.getContentPane().removeAll();
            chatStyleButtons(backgroundFramePower, background);
            windowPower.getContentPane().add(backgroundFramePower);
            windowPower.setVisible(true);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            windowPower.setVisible(false);
        }
    }

    /**
     * Method that visualize the phase message Start your turn
     * @param name Name of the Player that start the turn
     * @param isYourPlayer Boolean saying if the client Player is the one that start the turn
     */

    public void startTurn(String name, boolean isYourPlayer){
        internalFramePlaceWorkers.dispose();
        internalFrameUpdateBoard.setVisible(false);
        if (startTurn != null){
            internalFrameStartTurn.remove(startTurn);
        }
        if (isYourPlayer){
            try {
                startTurn = new StartTurn(internalFrameStartTurn, internalFrameSize40x45, 0, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            yourTurn.play();
            labelChooseWorker.setVisible(true);
            buttonMultiUse.setVisible(true);
            buttonMultiUse.addActionListener(new AvailableWorkers());
            addColorBorderToMap();
        }
        else{
            try {
                startTurn = new StartTurn(internalFrameStartTurn, internalFrameSize40x45, 1, name);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }

        }
        removeModifiedBorder();
        internalFrameStartTurn.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        internalFrameStartTurn.getContentPane().add(startTurn);
        internalFrameStartTurn.setVisible(true);
    }

    /**
     * Class that implements ActionListener that show which worker you can choose on the Board
     */

    private class AvailableWorkers implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!availableMovePositions.isEmpty()){
                eliminatePreviousPositionsActions(availableMovePositions);
            }
            if (!availableBuildPositions.isEmpty()){
                removeBuildBorder(availableBuildPositions);
                availableBuildPositions.clear();
            }

            availableWorkers = gui.availableWorkers();
            boolean exitFor;
            for (Integer x : availableWorkers){
                exitFor = false;
                for (int y = 0; y < 25 && !exitFor; y++){
                    if (mapMyWorkers[y] == x){
                        eliminateMouseClass(mapButtons[y], ColorBorder.class);
                        eliminateActionClass(mapButtons[y], SelectWorker.class);
                        mapButtons[y].setBorder(BorderFactory.createLineBorder(selectWorkerBorder, 5));
                        mapButtons[y].setBorderPainted(true);
                        mapButtons[y].addActionListener(new SelectWorker());
                        availableWorkersPositions.add(y);
                        exitFor = true;
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
            availableMovePositions.clear();
        }
    }

    /**
     * Class that implements ActionListener that communicate the selected worker
     */

    private class SelectWorker implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton) e.getSource();
            int pos = Integer.parseInt(c.getName());
            gui.setWorker(mapMyWorkers[pos]);
            for (Integer k : availableWorkersPositions){
                eliminateActionClass(mapButtons[k], SelectWorker.class);
                mapButtons[k].setBorderPainted(false);
            }
            mapButtons[pos].setBorder(BorderFactory.createLineBorder(selectedWorkerBorder, 5));
            mapButtons[pos].setBorderPainted(true);
            labelChooseWorker.setVisible(false);
            buttonMultiUse.setVisible(false);
            workerChosen = mapMyWorkers[pos];
            tileWorkerChosen = pos;
            availableWorkersPositions.clear();
        }
    }

    /**
     * Method that enable tha console buttons according to the possible actions
     * @param actions List of possible actions
     */

    public void setVisibleButtons(List<MessageType> actions){
        setVisibleMove(true);
        setVisibleBuild(true);
        setEnableMove(false);
        setEnableBuild(false);
        setEnableEndTurn(false);
        for (MessageType mess : actions){
            switch (mess){
                case BUILDWORKER:
                    setEnableBuild(true);
                    break;

                case MOVEWORKER:
                    setEnableMove(true);
                    break;

                case WORKERCHOICE:
                    setVisibleChoose(true);
                    break;

                case ENDTURN:
                    setEnableEndTurn(true);
                    break;
                default:
            }
        }
    }

    /**
     * Class that implements ActionListener that show where you can move the selected worker on the Board
     */

    private class SeeMove implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            clearMap();
            availableMovePositions = gui.availableMoveSquare();

            for (Integer x : availableMovePositions){
                eliminateMouseClass(mapButtons[x - 1], ColorBorder.class);
                eliminateActionClass(mapButtons[x - 1], Move.class);
                eliminateActionClass(mapButtons[x - 1], ShowButtonsBuild.class);
                mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(moveBorder, 5));
                mapButtons[x - 1].setBorderPainted(true);
                mapButtons[x - 1].addActionListener(new Move());
            }
        }
    }

    /**
     * Class that implements ActionListener that communicate the position to move the worker selected on the Board
     */

    private class Move implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            place.play();
            JButton c = (JButton) e.getSource();
            response = gui.moveWorker(Integer.parseInt(c.getName()) + 1);
            for (Integer x : availableMovePositions){
                eliminateActionClass(mapButtons[x - 1], Move.class);
                mapButtons[x - 1].setBorderPainted(false);

            }
            displayModifications(gui.getModifiedsquare(), true);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            setVisibleEndTurn(true);
            gui.mapNextAction(response);
        }
    }

    /**
     * Method that visualize the message of an Update in the Board
     * @param name Name of the Player that made the action
     * @param squares Positions modified
     * @param type Type of action made
     */

    public void updateBoard(String name, List<Square> squares, MessageType type){

        if (updateBoard != null) {
            internalFrameUpdateBoard.remove(updateBoard);
        }
        try {
            updateBoard = new UpdateBoard(this, internalFrameUpdateBoard, internalFrameSize40x45, name, type);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        internalFrameUpdateBoard.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        internalFrameUpdateBoard.getContentPane().add(updateBoard);
        internalFrameUpdateBoard.setVisible(true);

        switch (type){
            case MOVEWORKER:
            case BUILDWORKER:
                displayModifications(squares, false);
                break;

            default:
        }
    }

    /**
     * Method that clean the modified positions in the Board
     * @param x Positions modified
     */

    private void cleanIcon(Integer x){
        eliminateMouseClass(mapButtons[x], ColorBorder.class);
        mapButtons[x].setBorderPainted(false);
        mapButtons[x].addMouseListener(new ColorBorder());
    }

    /**
     * Method that display the modifications on the Board
     * @param squares Positions modified
     * @param isMe Boolean saying if the client Player is the one who has modified the Board
     */

    private void displayModifications(List<Square> squares, boolean isMe){

        List<JLabel> list;
        removeModifiedBorder();

        for (Square square : squares){
            cleanIcon(square.getTile() - 1);

            if (square.hasPlayer()) {

                list = getPlayerIconsList(square);

                mapButtonsPlayer[square.getTile() - 1] = true;

                setPlayerIconToMap(square, list);
            }
            else {

                mapMyWorkers[square.getTile() - 1] = 0;
                mapButtonsPlayer[square.getTile() - 1] = false;

                setFreeIconToMap(square);
            }
            if (!isMe) {
                mapButtons[square.getTile() - 1].setBorder(BorderFactory.createLineBorder(modifiedBorder, 5));
                mapButtons[square.getTile() - 1].setBorderPainted(true);
            }
        }
        if (!isMe){
            removeColorBorderToMap();
        }
    }

    /**
     * Method that gives the icon list of the Player in the position provided
     * @param square Position provided
     * @return Icon list
     */

    private List<JLabel> getPlayerIconsList(Square square){
        if (square.getPlayer().getNickName().equalsIgnoreCase(opponent1.getText())){
            mapMyWorkers[square.getTile() - 1] = 0;
            return opponents1Labels;
        }
        else if (numberOfPlayers == 3 && square.getPlayer().getNickName().equalsIgnoreCase(opponent2.getText())){
            mapMyWorkers[square.getTile() - 1] = 0;
            return opponents2Labels;
        }
        else if (square.getPlayer().getNickName().equalsIgnoreCase(mePlayer.getNickName())){
            mapMyWorkers[square.getTile() - 1] = WorkerName.getNumberWorker(square.getWorker().getName());
            return myLabels;
        }
        else return new ArrayList<>();
    }

    /**
     * Method that set the icon in the position provided with the icons Player list provided according to the Building in that position
     * @param square Position provided
     * @param list Icons Player list provided
     */

    private void setPlayerIconToMap(Square square, List<JLabel> list){
        if (square.getBuilding().equals(Building.GROUND)) {
            mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.GROUND)).getIcon());
        } else if (square.getBuilding().equals(Building.LVL1)) {
            mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.LVL1)).getIcon());
        } else if (square.getBuilding().equals(Building.LVL2)) {
            mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.LVL2)).getIcon());
        } else if (square.getBuilding().equals(Building.LVL3)) {
            mapButtons[square.getTile() - 1].setIcon(Objects.requireNonNull(getLabelFromBuildLvl(list, Building.LVL3)).getIcon());
        }
    }

    /**
     * Method that set the icon in the position provided according to the Building in that position
     * @param square Position provided
     */

    private void setFreeIconToMap(Square square){
        if (square.getBuilding().equals(Building.GROUND)) {
            mapButtons[square.getTile() - 1].setIcon(null);
        }
        else if (square.getBuilding().equals(Building.LVL1)) {
            mapButtons[square.getTile() - 1].setIcon(lvl1.getIcon());
        }
        else if (square.getBuilding().equals(Building.LVL2)) {
            mapButtons[square.getTile() - 1].setIcon(lvl2.getIcon());
        }
        else if (square.getBuilding().equals(Building.LVL3)) {
            mapButtons[square.getTile() - 1].setIcon(lvl3.getIcon());
        }
        else if (square.getBuilding().equals(Building.DOME) && square.getBuildingLevel() == 1) {
            mapButtons[square.getTile() - 1].setIcon(dome.getIcon());
        }
        else if (square.getBuilding().equals(Building.DOME) && square.getBuildingLevel() == 2) {
            mapButtons[square.getTile() - 1].setIcon(lvl1Dome.getIcon());
        }
        else if (square.getBuilding().equals(Building.DOME) && square.getBuildingLevel() == 3) {
            mapButtons[square.getTile() - 1].setIcon(lvl2Dome.getIcon());
        }
        else {
            mapButtons[square.getTile() - 1].setIcon(lvl3Dome.getIcon());
        }
    }

    /**
     * Class that implements ActionListener that communicate the end of the turn
     */

    private class EndTurn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.endTurn();
            setVisibleEndTurn(false);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            removeColorBorderToMap();
        }
    }

    /**
     * Method that visualize/hide the buttonEndTurn
     * @param bool Boolean saying to visualize or hide the button
     */

    private void setVisibleEndTurn(boolean bool){
        labelEndTurn.setVisible(bool);
        buttonEndTurn.setVisible(bool);
    }

    /**
     * Method that visualize/remove the buttonMove
     * @param bool Boolean saying to visualize or hide the button
     */

    private void setVisibleMove(boolean bool){
        labelMove.setVisible(bool);
        buttonMove.setVisible(bool);
    }

    /**
     * Method that visualize/remove the buttonBuild
     * @param bool Boolean saying to visualize or hide the button
     */

    private void setVisibleBuild(boolean bool){
        labelBuild.setVisible(bool);
        buttonBuild.setVisible(bool);
    }

    /**
     * Method that visualize/remove the buttonMultiUse
     * @param bool Boolean saying to visualize or hide the button
     */

    private void setVisibleChoose(boolean bool){
        labelChooseWorker.setVisible(bool);
        buttonMultiUse.setVisible(bool);
    }

    /**
     * Method that enable/disable the buttonEndTurn
     * @param bool Boolean saying to enable or disable the button
     */

    private void setEnableEndTurn(boolean bool){
        buttonEndTurn.setEnabled(bool);
    }

    /**
     * Method that enable/disable the buttonMove
     * @param bool Boolean saying to enable or disable the button
     */

    private void setEnableMove(boolean bool){
        buttonMove.setEnabled(bool);
    }

    /**
     * Method that enable/disable the buttonBuild
     * @param bool Boolean saying to enable or disable the button
     */

    private void setEnableBuild(boolean bool){
        buttonBuild.setEnabled(bool);
    }

    /**
     * Method that visualize the defeat screen for the eliminated player
     * @param name Name of the eliminated Player
     * @param isYourPlayer Boolean saying if the client Player is the one eliminated
     */

    public void displayLose(String name, boolean isYourPlayer) {
        internalFrameUpdateBoard.setVisible(false);
        internalFrameStartTurn.setVisible(false);
        newGame.addActionListener(new NewGameLoose());
        newGame.setBounds((frameSize.width * 35/100) - (buttonSize.width / 2), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
        close.setBounds((frameSize.width * 65/100) - (buttonSize.width / 2), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);

        try {
            if (isYourPlayer) {
                border = ImageHandler.setImage(LOSEBORDER, 100, 100, frameSize.width * 50 / 100, frameSize.height * 80 / 100);
                lLoser1 = ImageHandler.setImage(PODIUM + mePlayer.getPower().getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
            }
            else {
                border = ImageHandler.setImage(PODIUM + "lost_border.png", 100, 100, frameSize.width * 50 / 100, frameSize.height * 80 / 100);
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }

        displayModifications(gui.getModifiedsquare(), false);

        if (isYourPlayer) {
            eliminateAllFromAll();
            loopSound.stop();
            lose.play();
            winLose.setIcon(border.getIcon());
            loser1.setIcon(lLoser1.getIcon());
            loser1.setBounds((frameSize.width * 34/100),frameSize.height * 30/100, frameSize.width * 30/100, frameSize.height * 50/100);
            winLose.setVisible(true);
            loser1.setVisible(true);
            newGame.setVisible(true);
            close.setVisible(true);
            keepWatching.setVisible(true);
        }
        else {
            winLose.setIcon(border.getIcon());
            winLose.setVisible(true);
            lLost.setText(name);
            lLost.setFont(felixBold);
            lLost.setBounds((int) (((double)frameSize.width * 49/100) - ((lLost.getText().length() * getBoldDimension()) / 2)), (frameSize.height * 50 / 100), frameSize.width * 50/100, frameSize.height * 5/100);
            lLost.setVisible(true);
            close.setBounds((frameSize.width * 48/100) - (buttonSize.width / 2), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
            eliminateActionClass(close, Close.class);
            close.setVisible(true);
            close.addActionListener(new CloseLost());
        }

    }

    /**
     * Method that visualize the win/defeat screen
     * @param name Name of the winner Player
     */

    public void displayWinLose(String name){
        internalFrameUpdateBoard.setVisible(false);
        internalFrameStartTurn.setVisible(false);
        boolean winnerBool = false;
        if (name.equalsIgnoreCase(mePlayer.getNickName())){
            winnerBool = true;
        }
        newGame.addActionListener(new NewGameWin());
        eliminateAllFromAll();
        eliminateAllActionClass(buttonChat);
        loopSound.stop();
        try {
            if (winnerBool) {
                border = ImageHandler.setImage(PODIUM + "win_border.png", 100, 100, frameSize.width * 50/100, frameSize.height * 80/100);
                lWinner = ImageHandler.setImage(PODIUM + mePlayer.getPower().getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
                lLoser1 = ImageHandler.setImage(PODIUM + opponent1.getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
                if (numberOfPlayers == 3){
                    lLoser2 = ImageHandler.setImage(PODIUM + opponent2.getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
                }
                win.play();
            }
            else {
                border = ImageHandler.setImage(LOSEBORDER, 100, 100, frameSize.width * 50/100, frameSize.height * 80/100);
                lWinner = ImageHandler.setImage(PODIUM + opponent1.getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
                lLoser1 = ImageHandler.setImage(PODIUM + mePlayer.getPower().getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
                if (numberOfPlayers == 3){
                    lLoser2 = ImageHandler.setImage(PODIUM + opponent2.getName() + PNG, 100, 100, frameSize.width * 30/100, frameSize.height * 50/100);
                }
                lose.play();
            }
        } catch (IOException ioException) {
            LOGGER.severe(ioException.getMessage());
        }
        winLose.setIcon(border.getIcon());
        winner.setIcon(lWinner.getIcon());
        loser1.setIcon(lLoser1.getIcon());


        if (numberOfPlayers == 3){
            loser2.setIcon(lLoser2.getIcon());
            winner.setBounds((frameSize.width * 34/100),frameSize.height * 20/100, frameSize.width * 30/100, frameSize.height * 50/100);
            glow.setBounds((int) (frameSize.width * 41.5/100),frameSize.height * 33/100, frameSize.width * 15/100, frameSize.height * 35/100);
            loser1.setBounds((frameSize.width * 24/100),frameSize.height * 30/100, frameSize.width * 30/100, frameSize.height * 50/100);
            loser2.setBounds((frameSize.width * 44/100),frameSize.height * 30/100, frameSize.width * 30/100, frameSize.height * 50/100);
        }
        setVisibleWin(true, numberOfPlayers == 3);
    }

    /**
     * Method that visualize the message that it's not the turn of the client Player
     */

    public void notTurn(){
        internalFrameStartTurn.setVisible(false);
        internalFrameUpdateBoard.setVisible(false);
        if (notTurn != null){
            internalFrameStartTurn.remove(notTurn);
        }
        if (startTurn != null){
            internalFrameStartTurn.remove(startTurn);
        }
        try {
            notTurn = new NotTurn(internalFrameStartTurn, internalFrameSize40x45);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        internalFrameStartTurn.setBounds((int) (frameSize.width * 29.5/100), (int) (frameSize.height * 25.5/100), internalFrameSize40x45.width, internalFrameSize40x45.height);
        internalFrameStartTurn.getContentPane().add(notTurn);
        internalFrameStartTurn.setVisible(true);
    }

    /**
     * Method that change the JInternalFrame for the visualization of the power if was activated/deactivated a limitation caused by other god
     * @param name Name of the god that activated the limitation
     * @param isEliminated Boolean saying if the limitation is deleted or added
     */

    public void displayConstraint(String name, boolean isEliminated){

        if (!isEliminated) {
            constraint.add(name);
        }
        else {
            constraint.remove(name);
        }
        if (constraint.size() == 1){
            eliminateAllActionClass(buttonPower);
            buttonPower.setIcon(lButtonPower.getIcon());
            buttonPower.addActionListener(new ShowYourPower());
        }
        else {
            if (seeConstraint != null){
                internalFrameConstraint.remove(seeConstraint);
            }
            try {
                seeConstraint = new SeeConstraint(this, internalFrameConstraint, internalFrameSize80x80, constraint);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
            internalFrameConstraint.setBounds(((frameSize.width * 50 / 100) - (internalFrameSize80x80.width / 2)), ((frameSize.height * 46 / 100) - (internalFrameSize80x80.height / 2)), internalFrameSize80x80.width, internalFrameSize80x80.height);
            internalFrameConstraint.getContentPane().add(seeConstraint);
            eliminateActionClass(buttonPower, ShowYourPower.class);
            buttonPower.addActionListener(new ShowConstraint());
            buttonPower.setIcon(lButtonPowerPing.getIcon());
        }
    }

    /**
     * Class that implements ActionListener that show the JInternalFrame for visualize the actual limitation
     */

    public class ShowConstraint implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton)e.getSource();
            eliminateActionClass(c, ShowConstraint.class);
            c.setIcon(lButtonPowerPressPing.getIcon());
            internalFrameConstraint.setVisible(true);
            c.addActionListener(new HideConstraint());
        }
    }

    /**
     * Class that implements ActionListener that hide the JInternalFrame for visualize the actual limitation
     */

    public class HideConstraint implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton)e.getSource();
            eliminateActionClass(c, HideConstraint.class);
            c.addActionListener(new ShowConstraint());
            c.setIcon(lButtonPowerPing.getIcon());
            internalFrameConstraint.setVisible(false);
        }
    }

    /**
     * Getter of the name of the client Player
     * @return Name of the client Player
     */

    public String getMyName(){
        return mePlayer.getNickName();
    }

    /**
     * Method that visualize/hide the win/lose screen
     * @param bool Boolean saying to visualize or hide the win/lose screen
     * @param isIn3 Boolean saying if it's a 3 Player game
     */

    private void setVisibleWin(boolean bool, boolean isIn3){
        winLose.setVisible(bool);
        winner.setVisible(bool);
        glow.setVisible(bool);
        loser1.setVisible(bool);
        loser2.setVisible(isIn3);
        newGame.setVisible(bool);
        close.setVisible(bool);
    }

    /**
     * Method that eliminate all Listener from the buttons to not let the eliminated Player interfere with the game he is watching
     */

    public void eliminateAllFromAll(){
        for (JButton button : mapButtons){
            eliminateAllMouseClass(button);
            eliminateAllActionClass(button);
        }
        eliminateAllMouseClass(buttonPower);
        eliminateAllActionClass(buttonPower);
        eliminateAllMouseClass(buttonMultiUse);
        eliminateAllActionClass(buttonMultiUse);
        eliminateAllMouseClass(buttonEndTurn);
        eliminateAllActionClass(buttonEndTurn);
        eliminateAllMouseClass(buttonMove);
        eliminateAllActionClass(buttonMove);
        eliminateAllMouseClass(buttonBuild);
        eliminateAllActionClass(buttonBuild);
    }

    /**
     * Method that enable/disable the Building buttons
     * @param bool Boolean saying to enable or disable the Buildings button
     */

    private void enableLevels(boolean bool){
        buttonLvl1.setEnabled(bool);
        buttonLvl2.setEnabled(bool);
        buttonLvl3.setEnabled(bool);
        buttonDome.setEnabled(bool);
    }

    /**
     * Class that implements ActionListener that visualize the buildable positions of the chosen worker
     */

    private class AddBuildLvl implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            clearMap();
            availableBuildPositions = gui.availableBuildSquare();

            for (Integer x : availableBuildPositions){

                eliminateMouseClass(mapButtons[x - 1], ColorBorder.class);
                eliminateActionClass(mapButtons[x - 1], ShowButtonsBuild.class);
                eliminateActionClass(mapButtons[x - 1], Move.class);
                mapButtons[x - 1].setBorder(BorderFactory.createLineBorder(buildBorder, 5));
                mapButtons[x - 1].setBorderPainted(true);
                mapButtons[x - 1].addActionListener(new ShowButtonsBuild());

            }
        }
    }

    /**
     * Method that clean the Board for the next action
     */

    private void clearMap(){
        for (int x = 0; x < 25; x++){
            eliminateMouseClass(mapButtons[x], ColorBorder.class);
            eliminateActionClass(mapButtons[x], ShowButtonsBuild.class);
            eliminateActionClass(mapButtons[x], Move.class);
            mapButtons[x].setBorderPainted(false);
        }
    }

    /**
     * Class that implements ActionListener that enable the buttons of the buildable Buildings in the selected position
     */

    private class ShowButtonsBuild implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton c = (JButton) e.getSource();
            removeBuildLvl();
            tileBuildChosen = Integer.parseInt(c.getName());
            frameBuildings.setVisible(true);
            if (gui.getLevel(Integer.parseInt(c.getName()) + 1) == 0){
                buttonLvl1.addActionListener(new BuildLvl1());
                buttonLvl1.setEnabled(true);
            }
            if (gui.getLevel(Integer.parseInt(c.getName()) + 1) == 1){
                buttonLvl2.addActionListener(new BuildLvl2());
                buttonLvl2.setEnabled(true);
            }
            if (gui.getLevel(Integer.parseInt(c.getName()) + 1) == 2){
                buttonLvl3.addActionListener(new BuildLvl3());
                buttonLvl3.setEnabled(true);
            }
            if (gui.getLevel(Integer.parseInt(c.getName()) + 1) == 3 && !mePlayer.getPower().getName().equalsIgnoreCase("atlas")){
                buttonDome.addActionListener(new BuildDome());
                buttonDome.setEnabled(true);
            }
            if (mePlayer.getPower().getName().equalsIgnoreCase("atlas")){
                buttonDome.addActionListener(new BuildDomeAtlas());
                buttonDome.setEnabled(true);
            }
        }
    }

    /**
     * Method that clean the Building buttons for the next build
     */

    private void removeBuildLvl() {
        enableLevels(false);
        eliminateActionClass(buttonLvl1, BuildLvl1.class);
        eliminateActionClass(buttonLvl2, BuildLvl2.class);
        eliminateActionClass(buttonLvl3, BuildLvl3.class);
        eliminateActionClass(buttonDome, BuildDome.class);
        eliminateActionClass(buttonDome, BuildDomeAtlas.class);
    }

    /**
     * Method that clean the positions provided for the next build
     * @param positions Positions provided
     */

    private void removeBuildBorder(List<Integer> positions){
        for (Integer x : positions){
            mapButtons[x - 1].setBorderPainted(false);
            eliminateActionClass(mapButtons[x - 1], ShowButtonsBuild.class);
        }
    }

    /**
     * Class that implements ActionListener that communicate to the server and build the First level build in the selected position
     */

    private class BuildLvl1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            build.play();
            response = gui.buildWorker(tileBuildChosen + 1, Building.LVL1);

            if (mePlayer.getPower().getName().equalsIgnoreCase("zeus") && mapMyWorkers[tileBuildChosen] != 0){
                mapButtons[tileBuildChosen].setIcon(myLabels.get(1).getIcon());
            }
            else {
                mapButtons[tileBuildChosen].setIcon(lvl1.getIcon());
            }

            frameBuildings.setVisible(false);
            enableLevels(false);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            setVisibleEndTurn(true);
            removeBuildBorder(availableBuildPositions);
            removeBuildLvl();
            gui.mapNextAction(response);
        }
    }

    /**
     * Class that implements ActionListener that communicate to the server and build the Second level build in the selected position
     */

    private class BuildLvl2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            build.play();
            response = gui.buildWorker(tileBuildChosen + 1, Building.LVL2);

            if (mePlayer.getPower().getName().equalsIgnoreCase("zeus") && mapMyWorkers[tileBuildChosen] != 0){
                mapButtons[tileBuildChosen].setIcon(myLabels.get(2).getIcon());
            }
            else {
                mapButtons[tileBuildChosen].setIcon(lvl2.getIcon());
            }


            frameBuildings.setVisible(false);
            enableLevels(false);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            setVisibleEndTurn(true);
            removeBuildBorder(availableBuildPositions);
            removeBuildLvl();
            gui.mapNextAction(response);
        }
    }

    /**
     * Class that implements ActionListener that communicate to the server and build the Third level build in the selected position
     */

    private class BuildLvl3 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            build.play();
            response = gui.buildWorker(tileBuildChosen + 1, Building.LVL3);

            if (mePlayer.getPower().getName().equalsIgnoreCase("zeus") && mapMyWorkers[tileBuildChosen] != 0){
                mapButtons[tileBuildChosen].setIcon(myLabels.get(3).getIcon());
            }
            else {
                mapButtons[tileBuildChosen].setIcon(lvl3.getIcon());
            }

            frameBuildings.setVisible(false);
            enableLevels(false);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            setVisibleEndTurn(true);
            removeBuildBorder(availableBuildPositions);
            removeBuildLvl();
            gui.mapNextAction(response);
        }
    }

    /**
     * Class that implements ActionListener that communicate to the server and build the Dome build in the selected position
     */

    private class BuildDome implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            tower.play();
            response = gui.buildWorker(tileBuildChosen + 1, Building.DOME);
            mapButtons[tileBuildChosen].setIcon(lvl3Dome.getIcon());


            frameBuildings.setVisible(false);
            enableLevels(false);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            setVisibleEndTurn(true);
            removeBuildBorder(availableBuildPositions);
            removeBuildLvl();
            gui.mapNextAction(response);
        }
    }

    /**
     * Class that implements ActionListener that communicate to the server and build the Dome at the current level build in the selected position
     */

    private class BuildDomeAtlas implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gui.getLevel(tileBuildChosen + 1) == 3){
                tower.play();
            }
            else {
                build.play();
            }
            if (gui.getLevel(tileBuildChosen + 1) == 0){
                mapButtons[tileBuildChosen].setIcon(dome.getIcon());
            }
            else if (gui.getLevel(tileBuildChosen + 1) == 1){
                mapButtons[tileBuildChosen].setIcon(lvl1Dome.getIcon());
            }
            else if (gui.getLevel(tileBuildChosen + 1) == 2){
                mapButtons[tileBuildChosen].setIcon(lvl2Dome.getIcon());
            }
            else if (gui.getLevel(tileBuildChosen + 1) == 3){
                mapButtons[tileBuildChosen].setIcon(lvl3Dome.getIcon());
            }

            response = gui.buildWorker(tileBuildChosen + 1, Building.DOME);

            frameBuildings.setVisible(false);
            enableLevels(false);
            setVisibleChoose(false);
            setVisibleMove(false);
            setVisibleBuild(false);
            setVisibleEndTurn(true);
            removeBuildBorder(availableBuildPositions);
            removeBuildLvl();
            gui.mapNextAction(response);
        }
    }

    /**
     * Class that extends MouseAdapter that color the border of the position as the mouse cursor moves over it
     */

    private class ColorBorder extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton c = (JButton)e.getSource();
            c.setBorder(BorderFactory.createLineBorder(chooseTile, 5));
            c.setBorderPainted(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton c = (JButton)e.getSource();
            c.setBorderPainted(false);
        }
    }

    /**
     * Getter for click audio
     * @return Click MP3
     */

    public static MP3 getClick() {
        return click;
    }

    /**
     * Getter of dimension of bold character
     * @return BoldDimension
     */

    public static double getBoldDimension() {
        return BOLDDIMENSION;
    }

    /**
     * Class that extends MouseAdapter that animate the click of the buttons
     */

    private class ButtonPress extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            getClick().play();
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
            else if (buttonChat == c){
                buttonChat.setIcon(lButtonChatPress.getIcon());
            }
            else if (buttonChooseCards == c) {
                buttonChooseCards.setIcon(lButtonChooseCardsPress.getIcon());
            }
            else if (buttonChooseFirst == c) {
                buttonChooseFirst.setIcon(lButtonChooseFirstPress.getIcon());
            }
            else if (buttonChoosePower == c) {
                buttonChoosePower.setIcon(lButtonChoosePowerPress.getIcon());
            }
            else if (buttonEndTurn == c) {
                buttonEndTurn.setIcon(lButtonEndTurnPress.getIcon());
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
            else if(buttonChat == c) {
                buttonChat.setIcon(lButtonChat.getIcon());
            }
            else if (buttonChooseCards == c) {
                buttonChooseCards.setIcon(lButtonChooseCards.getIcon());
            }
            else if (buttonChooseFirst == c) {
                buttonChooseFirst.setIcon(lButtonChooseFirst.getIcon());
            }
            else if (buttonChoosePower == c) {
                buttonChoosePower.setIcon(lButtonChoosePower.getIcon());
            }
            else if (buttonEndTurn == c) {
                buttonEndTurn.setIcon(lButtonEndTurn.getIcon());
            }
        }
    }

    /**
     * Class that implements ActionListener that visualize the Chat pane
     */

    private class Chat implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frameChat.setVisible(true);
            chatOpen = true;
        }
    }

    /**
     * Class that implements ActionListener that hide the Chat pane
     */

    private class ChatExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frameChat.setVisible(false);
            chatOpen = false;
        }
    }

    /**
     * Method that write in chat the message provided
     * @param name Name of the Player that sent the message
     * @param mess Message sent by the Player
     */

    public void writeInChat(String name, String mess){
        if (!chatOpen){
            buttonChat.setIcon(lButtonChatPing.getIcon());
        }
        StyleConstants.setForeground(colorStyle, getColorOpponent(name));
        try{
            doc.insertString(doc.getLength(), name, colorStyle);
        }catch (BadLocationException e){
            LOGGER.severe(INSERTFAILED);
        }
        StyleConstants.setForeground(colorStyle, Color.BLACK);
        try{
            doc.insertString(doc.getLength(), ": " + mess + "\n", colorStyle);
        }catch (BadLocationException e){
            LOGGER.severe(INSERTFAILED);
        }
        chat.setCaretPosition(chat.getDocument().getLength());
        field.setText("");
    }

    /**
     * Class that implements ActionListener that send the message written by the client Player
     */

    private class Write implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!field.getText().equals("")) {
                String string = field.getText().toLowerCase();
                StyleConstants.setForeground(colorStyle, getColorPlayer(mePlayer));
                try{
                    doc.insertString(doc.getLength(), mePlayer.getNickName(), colorStyle);
                }catch (BadLocationException be){
                    LOGGER.severe(INSERTFAILED);
                }
                StyleConstants.setForeground(colorStyle, Color.BLACK);
                try{
                    doc.insertString(doc.getLength(), ": " + field.getText().toLowerCase() + "\n", colorStyle);
                }catch (BadLocationException be){
                    LOGGER.severe(INSERTFAILED);
                }
                chat.setCaretPosition(chat.getDocument().getLength());
                field.setText("");
                gui.sendChatMessage(string);
            }
        }
    }

    /**
     * Class that implements ActionListener that bring back to the login for a new game after a finished game
     */

    private class NewGameWin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            gui.backToLogin(false, false);
            gui.frame.setVisible(true);
            loopSound.stop();
        }
    }

    /**
     * Class that implements ActionListener that bring back to the login for a new game after an elimination
     */

    private class NewGameLoose implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            gui.backToLogin(false, false);
            gui.frame.setVisible(true);
            gui.handleLoseExit();
            loopSound.stop();
        }
    }

    /**
     * Class that implements ActionListener that allows the client Player to continue watching the game
     */

    private class KeepWatching implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisibleWin(false, false);
            keepWatching.setVisible(false);
        }
    }

    /**
     * Class that implements ActionListener for the JButton Close which close the game
     */

    private class Close implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
            System.exit(0);
        }
    }

    /**
     * Class that implements ActionListener for the JButton CloseTutorial which close the tutorial screen
     */

    private class CloseTutorial implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            tutorial.setVisible(false);
            tutorial1.setVisible(false);
            tutorial2.setVisible(false);
            closeTutorial.setVisible(false);
            continueTutorial.setVisible(false);
            internalFrameChallenger1.setVisible(true);
        }
    }

    /**
     * Class that implements ActionListener for the JButton Continue which allow to navigate through the tutorial
     */

    private class ContinueTutorial implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tutorial.isVisible()){
                tutorial.setVisible(false);
                tutorial1.setVisible(true);
            }
            else if (tutorial1.isVisible()){
                tutorial1.setVisible(false);
                tutorial2.setVisible(true);
                continueTutorial.setVisible(false);
                closeTutorial.setBounds(((frameSize.width * 50/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
            }
        }
    }

    /**
     * Class that implements ActionListener for the JButton Close which close the message after the elimination
     */

    private class CloseLost implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            winLose.setVisible(false);
            eliminateActionClass(close, CloseLost.class);
            close.setBounds(((frameSize.width * 60/100) - (buttonSize.width / 2)), (int) (frameSize.height * 79.5 / 100), buttonSize.width, buttonSize.height);
            close.setVisible(false);
            lLost.setVisible(false);
            close.addActionListener(new Close());
        }
    }
}

