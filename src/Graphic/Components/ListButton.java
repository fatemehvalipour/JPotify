package Graphic.Components;

import Graphic.Containers.GridPanel;

import javax.swing.*;
import java.awt.*;

/**
 * shows some button in the west of frame
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class ListButton extends JButton {
    /**
     * constructor for this class
     * @param gridPanel
     * @param text
     */
    public ListButton(GridPanel gridPanel, String text) {
        super();
        this.setText(text);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(120, 50));
        this.setFont(new Font("Dialog", Font.PLAIN, 24));
        gridPanel.add(this);
        setBorder(null);
        setFocusable(false);
    }
}
