package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

/**
 * makes a panel with GridBagLayout
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class GridBagPanel extends JPanel {
    private GridBagConstraints gbc;

    public GridBagPanel(BorderPanel borderPanel) {
        super();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        borderPanel.add(new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }
}
