package Graphic.Components;

import Graphic.Containers.GridPanel;

import javax.swing.*;
import java.awt.*;

public class ListButton extends JButton {
    public ListButton(GridPanel gridPanel, String text) {
        super();
        this.setText(text);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(120, 100));
        this.setForeground(Color.white);
        this.setFont(new Font("Dialog", Font.PLAIN, 24));
        gridPanel.add(this);

    }
}
