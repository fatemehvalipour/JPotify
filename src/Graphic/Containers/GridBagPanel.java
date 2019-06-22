package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

public class GridBagPanel extends JPanel {
    JButton playButton;
    JButton nextButton;
    JButton previousButton;
    JButton likeButton;
    JButton shuffleButton;

    public GridBagPanel(GridPanel gridPanel) {
        super();
        gridPanel.add(this);
        setBackground(Color.black);
        setLayout(new GridBagLayout());
    }
}
