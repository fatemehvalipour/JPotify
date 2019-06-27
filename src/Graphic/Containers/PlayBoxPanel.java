package Graphic.Containers;

import Data.Music;
import Graphic.Listeners.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayBoxPanel extends JPanel{
    private JButton playButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton likeButton;
    private JButton shuffleButton;
    private JButton stopButton;
    private JButton repeatButton;
    private Box horizontalBox;
    public PlayBoxPanel() {
        super();
        setBackground(Color.black);
        playButton = new JButton();
        nextButton = new JButton();
        previousButton = new JButton();
        likeButton = new JButton();
        shuffleButton = new JButton();
        stopButton = new JButton();
        repeatButton = new JButton();
        try {
            playButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("play.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            playButton.setBackground(Color.black);
            playButton.setBorder(null);
            playButton.setFocusable(false);
            playButton.setPreferredSize(new Dimension(55, 55));
            playButton.addMouseListener(new PlayButtonListener());
            nextButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("next.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            nextButton.setPreferredSize(new Dimension(55, 55));
            nextButton.addMouseListener(new NextButtonListener());
            nextButton.setBackground(Color.black);
            nextButton.setFocusable(false);
            nextButton.setBorder(null);
            previousButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("previous.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            previousButton.setPreferredSize(new Dimension(55, 55));
            previousButton.setBackground(Color.black);
            previousButton.addMouseListener(new PreviousButtonListener());
            previousButton.setBorder(null);
            previousButton.setFocusable(false);
            likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("heartBlue.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            if(Music.playingMusic == null || !Music.playingMusic.isFavorite()) {
                likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("heartBlue.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            } else {
                likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("..\\Listeners\\heart.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            }
            new Thread(){
                @Override
                public void run() {
                    Music nowMusic = Music.playingMusic;
                    while (true){
                        if (Music.playingMusic != null && !Music.playingMusic.equals(nowMusic)){
                            try {
                                if (Music.playingMusic == null || !Music.playingMusic.isFavorite()) {
                                    likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("heartBlue.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                                } else {
                                    likeButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("..\\Listeners\\heart.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            nowMusic = Music.playingMusic;
                        }
                    }
                }
            }.start();
            likeButton.setPreferredSize(new Dimension(55, 55));
            likeButton.setBackground(Color.black);
            likeButton.setBorder(null);
            likeButton.setFocusable(false);
            likeButton.addMouseListener(new LikeButtonListener());
            shuffleButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("shuffle.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            shuffleButton.setPreferredSize(new Dimension(55, 55));
            shuffleButton.setBackground(Color.black);
            shuffleButton.setBorder(null);
            shuffleButton.setFocusable(false);
            if (Music.shuffle){
                shuffleButton.setBackground(Color.DARK_GRAY);
            }
            shuffleButton.addMouseListener(new ShuffleButtonListener());
            stopButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("stop.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            stopButton.setBackground(Color.black);
            stopButton.setBorder(null);
            stopButton.setFocusable(false);
            stopButton.setPreferredSize(new Dimension(55, 55));
            stopButton.addMouseListener(new PlayButtonListener());
            stopButton.addMouseListener(new StopButtonListener());
            repeatButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("repeat.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
            repeatButton.setBackground(Color.black);
            repeatButton.setBorder(null);
            repeatButton.setFocusable(false);
            if (Music.repeat){
                repeatButton.setBackground(Color.DARK_GRAY);
            }
            repeatButton.setPreferredSize(new Dimension(55, 55));
            repeatButton.addMouseListener(new RepeatbuttonListener());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    horizontalBox = Box.createHorizontalBox();
    horizontalBox.add(Box.createGlue());
    horizontalBox.add(likeButton);
    horizontalBox.add(stopButton);
    horizontalBox.add(previousButton);
    horizontalBox.add(playButton);
    horizontalBox.add(nextButton);
    horizontalBox.add(repeatButton);
    horizontalBox.add(shuffleButton);
    horizontalBox.add(Box.createGlue());
    add(horizontalBox);
    }

}
