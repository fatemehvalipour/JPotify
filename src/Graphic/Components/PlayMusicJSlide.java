package Graphic.Components;

import Data.Music;

import javax.swing.*;
import java.awt.*;

public class PlayMusicJSlide extends JPanel {
    private JSlider playSlider;
    private long frameCount;
    private Box horizontalBox;
    //private JLabel timePlayed;
    private JLabel wholeTime;


    public PlayMusicJSlide(Music music) {
        super();
        setBackground(Color.black);
        playSlider = new JSlider(0, 100);
        playSlider.setBackground(Color.black);
        playSlider.setPaintTrack(true);
        playSlider.setPaintTicks(true);
        playSlider.setPaintLabels(true);
        add(playSlider);
        wholeTime = new JLabel("" + (music.getDuration() / 60) + ":" + (music.getDuration() % 60));
        horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createGlue());
//        horizontalBox.add(timePlayed);
        horizontalBox.add(playSlider);
        horizontalBox.add(wholeTime);
        horizontalBox.add(Box.createGlue());
        add(horizontalBox);
    }
}
