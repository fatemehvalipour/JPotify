package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

/**
 * makes a Panel with GridLayout
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class GridPanel extends JPanel {
    public GridPanel(BorderPanel borderPanel, int column, Object index) {
        super();
        setBackground(Color.black);
        setLayout(new GridLayout(0, column));
        borderPanel.add(this , index);
        JScrollPane scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        borderPanel.add(scrollPane, BorderLayout.EAST);
    }
}
