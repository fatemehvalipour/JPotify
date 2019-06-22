package Graphic.Containers;

import javax.imageio.ImageIO;
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
        playButton = new JButton();
        nextButton = new JButton();
        previousButton = new JButton();
        likeButton = new JButton();
        shuffleButton = new JButton();
//        try {
//            playButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("play.png"))));
//            playButton.setSize(new Dimension(1000000, 100000));
//            nextButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("next.png"))));
//            previousButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("previous.png"))));
//            likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("heartBlue.png"))));
//            shuffleButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("shuffle.png"))));
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }

        setLayout(new GridBagLayout());
        setBackground(Color.black);

        add(likeButton);
        add(previousButton);
        add(playButton);
        add(nextButton);
        add(shuffleButton);
        gridPanel.add(this);

    }
}
