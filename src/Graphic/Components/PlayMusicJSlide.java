package Graphic.Components;

import Data.Music;

import javax.swing.*;
import java.awt.*;

public class PlayMusicJSlide extends JPanel {
    private JSlider playSlider;
    private long frameCount;
    private Box horizontalBox;
    private JLabel timePlayed;
    private JLabel wholeTime;


    public PlayMusicJSlide() {
        super();
        setBackground(Color.black);
        playSlider = new JSlider(0, 100);
        playSlider.setBackground(Color.black);
        add(playSlider);
        wholeTime = new JLabel("" + (Music.playingMusic.getDuration() / 60) + ":" + (Music.playingMusic.getDuration() % 60));
        timePlayed = new JLabel("0:0");
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    playSlider.setValue((int)((((double) Music.playingMusic.getEstimatedTime()) / ((double) Music.playingMusic.getDuration())) * 100));
                    timePlayed.setText("" + (Music.playingMusic.getEstimatedTime() / 60) + ":" + (Music.playingMusic.getEstimatedTime() % 60));
                    if (Music.playingMusic == null){
                        break;
                    }
                    if (Music.playingMusic.getEstimatedTime() == Music.playingMusic.getDuration()){
                        break;
                    }
                }
            }
        }.start();
        horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(timePlayed);
        horizontalBox.add(playSlider);
        horizontalBox.add(wholeTime);
        horizontalBox.add(Box.createGlue());
        add(horizontalBox);
    }
}
