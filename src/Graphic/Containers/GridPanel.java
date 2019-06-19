package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    public GridPanel(BorderPanel borderPanel, int column) {
        super();
        setBackground(Color.black);
        setLayout(new GridLayout(0, column));
        borderPanel.add(this , BorderLayout.NORTH);
    }
}
