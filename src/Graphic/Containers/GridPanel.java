package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    public GridPanel(BorderPanel borderPanel, int column, Object index) {
        super();
        setBackground(Color.black);
        setLayout(new GridLayout(0, column));
        borderPanel.add(this , index);
        JScrollPane scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setBounds(100,100, 50,505);
        borderPanel.add(scrollPane, BorderLayout.EAST);
    }
}
