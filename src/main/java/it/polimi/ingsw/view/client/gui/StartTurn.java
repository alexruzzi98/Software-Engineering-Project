package it.polimi.ingsw.view.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static it.polimi.ingsw.utils.ConstantsContainer.TEXT;
import static it.polimi.ingsw.view.client.gui.Board.getBoldDimension;
import static it.polimi.ingsw.view.client.gui.Gui.*;
import static it.polimi.ingsw.view.client.gui.GuiUtils.backAndCloseSetter;

/**
 * Class that extends JDesktopPane for the build of the pane for warning the start of the turn
 * @author Luigi Scibona
 * @version 1.0
 * @since 2020/06/13
 */

public class StartTurn extends JDesktopPane{
    Dimension frameSize = new Dimension();
    Dimension buttonSize = new Dimension();
    MyButton close = new MyButton(3);
    JInternalFrame intFrame;
    String nameChoosing;
    double bold = getBoldDimension();

    /**
     * Public constructor
     * @param intFrame Reference to the JInternalFrame where the current JDesktopPane StartTurn will be inserted
     * @param intFrameSize Size of the JInternalFrame
     * @param numberOfPanel Parameter indicating the JDesktopPane to be built based on who starts the turn
     * @param name Name of the Player who starts the turn
     * @throws IOException if the loading of the inscription was not successful
     */

    public StartTurn(JInternalFrame intFrame, Dimension intFrameSize, int numberOfPanel, String name) throws IOException {
        frameSize.setSize(intFrameSize);
        this.intFrame = intFrame;
        nameChoosing = name;
        buttonSize.setSize((getD().getWidth() * 13 / 100), (getD().getHeight() * 5 / 100));
        setPreferredSize(frameSize);
        setLayout(null);



        JLabel label = ImageHandler.setImage(TEXT + "is_starting_the_turn.png", 99, 99, frameSize.width * 85/100, frameSize.height * 22/100);
        JLabel label2 = ImageHandler.setImage(TEXT + "it's_your_turn.png", 99, 99, frameSize.width * 60/100, frameSize.height * 22/100);
        label.setBounds((int) (frameSize.width * 7.5/100), (int) (frameSize.height * 39.5/100), frameSize.width * 85/100, frameSize.height * 22/100);
        label2.setBounds((frameSize.width * 20/100), frameSize.height * 39/100, frameSize.width * 60/100, frameSize.height * 22/100);
        JLabel otherName = new JLabel(nameChoosing);
        otherName.setBounds((int) (((double)frameSize.width * 48/100) - ((otherName.getText().length() * bold) / 2)), (int) (frameSize.height * 34.5/100), frameSize.width * 60/100, frameSize.width * 5/100);
        otherName.setFont(getFelixBold());

        if (numberOfPanel == 0){
            add(label2);
        }
        else if (numberOfPanel == 1){
            add(label);
            add(otherName);
        }


        close.addActionListener(new Close());
        backAndCloseSetter(this, frameSize, buttonSize, close);
    }

    /**
     * Class that implements ActionListener for the JButton Close which closes the current JInternalFrame
     */

    private class Close implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            intFrame.setVisible(false);
        }
    }
}
