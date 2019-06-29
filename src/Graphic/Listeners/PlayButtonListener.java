package Graphic.Listeners;

import Data.Music;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * a listener for play and pause the musics
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class PlayButtonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Music.playingMusic != null) {
            if (Music.isPlaying) {
                Music.playingMusic.pause();
                Music.isPlaying = false;
            } else {
                try {
                    if (Music.playingMusic.isPaused()) {
                        Music.playingMusic.resume();
                    } else {
                        Music.playingMusic.play();
                    }
                    Music.isPlaying = true;
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (JavaLayerException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
