package Graphic.Components;

import Data.Music;
import Graphic.Listeners.SeekBarChangeListener;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * handles JSlide while playing Music
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class PlayMusicJSlide extends JPanel {
    private JProgressBar playSlider;
    private long frameCount;
    private Box horizontalBox;
    private JLabel timePlayed;
    private JLabel wholeTime;

    /**
     * constructor for this class
     */
    public PlayMusicJSlide() {
        super();
        setBackground(Color.black);
        setLayout(new BorderLayout());
        playSlider = new JProgressBar(0, 100);
        playSlider.setForeground(Color.pink);
        playSlider.addMouseListener(new SeekBarChangeListener());
        playSlider.setBackground(Color.black);
        playSlider.setValue(0);
        add(playSlider, BorderLayout.CENTER);
        if (Music.playingMusic != null) {
            wholeTime = new JLabel("" + (Music.playingMusic.getDuration() / 60) + ":" + (Music.playingMusic.getDuration() % 60));
        } else {
            wholeTime = new JLabel("0:0");
        }
        timePlayed = new JLabel("0:0");
        new Thread(() -> {
            Music nowMusic = Music.playingMusic;
            while (true) {
                if (Music.playingMusic == null){
                    continue;
                }
                playSlider.setValue((int)((((double) Music.playingMusic.getEstimatedTime()) / ((double) Music.playingMusic.getDuration())) * 100));
                if ((Music.playingMusic.getEstimatedTime() / 60) > 9 && (Music.playingMusic.getEstimatedTime() % 60) > 9) {
                    timePlayed.setText("" + (Music.playingMusic.getEstimatedTime() / 60) + ":" + (Music.playingMusic.getEstimatedTime() % 60));
                } else if ((Music.playingMusic.getEstimatedTime() / 60) < 10 && (Music.playingMusic.getEstimatedTime() % 60) < 10){
                    timePlayed.setText("0" + (Music.playingMusic.getEstimatedTime() / 60) + ":0" + (Music.playingMusic.getEstimatedTime() % 60));
                } else if ((Music.playingMusic.getEstimatedTime() / 60) > 9 && (Music.playingMusic.getEstimatedTime() % 60) < 10){
                    timePlayed.setText("" + (Music.playingMusic.getEstimatedTime() / 60) + ":0" + (Music.playingMusic.getEstimatedTime() % 60));
                } else {
                    timePlayed.setText("0" + (Music.playingMusic.getEstimatedTime() / 60) + ":" + (Music.playingMusic.getEstimatedTime() % 60));
                }
                if (!Music.playingMusic.equals(nowMusic)){
                    nowMusic = Music.playingMusic;
                    wholeTime.setText("" + (Music.playingMusic.getDuration() / 60) + ":" + (Music.playingMusic.getDuration() % 60));
                }
                if (Music.playingMusic.getEstimatedTime() == Music.playingMusic.getDuration()){
                    if (!Music.repeat) {
                        Music.next();
                    } else {
                        Music.playingMusic.stop();
                        try {
                            Music.playingMusic.play();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        wholeTime.setForeground(Color.white);
        timePlayed.setForeground(Color.white);
        horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createGlue());
        horizontalBox.add(timePlayed);
        horizontalBox.add(playSlider);
        horizontalBox.add(wholeTime);
        horizontalBox.add(Box.createGlue());
        add(horizontalBox);
    }
}
