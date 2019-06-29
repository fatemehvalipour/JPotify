package Graphic.Listeners;

import Data.Music;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * a listener for handling the seekbar
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class SeekBarChangeListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Music.playingMusic != null){
            Music.playingMusic.pause();
            long frame = Music.playingMusic.getTotalSongLength() / Music.playingMusic.getDuration();
            int progressBarVal = (int)Math.round(((double)e.getX() / (double)((JProgressBar)e.getSource()).getWidth()) * ((JProgressBar)e.getSource()).getMaximum());
            Music.playingMusic.setEstimatedTime((int)(((double) progressBarVal / 100) * (double)Music.playingMusic.getDuration()));
            Music.playingMusic.setPauseLocation(Music.playingMusic.getTotalSongLength() - (long)(frame * (((double)progressBarVal / 100) * Music.playingMusic.getDuration())));
            ((JProgressBar)e.getSource()).setValue(progressBarVal);
            try {
                Music.playingMusic.resume();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JavaLayerException e1) {
                e1.printStackTrace();
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
