package Graphic.Containers;

import Graphic.Listeners.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class PlayBoxPanel extends JPanel{
    private JButton playButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton likeButton;
    private JButton shuffleButton;
    private Box horizontalBox;
    public PlayBoxPanel() {
        super();
        setBackground(Color.black);
        playButton = new JButton();
        nextButton = new JButton();
        previousButton = new JButton();
        likeButton = new JButton();
        shuffleButton = new JButton();
    try {
            playButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("play.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            playButton.setBackground(Color.black);
            playButton.setPreferredSize(new Dimension(100, 100));
            playButton.addMouseListener(new PlayButtonListener());
            nextButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("next.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            nextButton.setPreferredSize(new Dimension(100, 100));
            nextButton.addMouseListener(new NextButtonListener());
            nextButton.setBackground(Color.black);
            previousButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("previous.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            previousButton.setPreferredSize(new Dimension(100, 100));
            previousButton.setBackground(Color.black);
            previousButton.addMouseListener(new PreviousButtonListener());
            likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("heartBlue.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            likeButton.setPreferredSize(new Dimension(100, 100));
            likeButton.setBackground(Color.black);
            likeButton.addMouseListener(new LikeButtonListener());
            shuffleButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("shuffle.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            shuffleButton.setPreferredSize(new Dimension(100, 100));
            shuffleButton.setBackground(Color.black);
            shuffleButton.addMouseListener(new ShuffleButtonListener());

        } catch (Exception ex) {
            System.out.println(ex);
        }
    horizontalBox = Box.createHorizontalBox();
    horizontalBox.add(Box.createGlue());
    horizontalBox.add(likeButton);
    horizontalBox.add(previousButton);
    horizontalBox.add(playButton);
    horizontalBox.add(nextButton);
    horizontalBox.add(shuffleButton);
    horizontalBox.add(Box.createGlue());
    add(horizontalBox);
    }

}
