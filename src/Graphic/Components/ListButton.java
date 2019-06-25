package Graphic.Components;

import Graphic.Containers.GridPanel;

import javax.swing.*;
import java.awt.*;

public class ListButton extends JButton {
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
