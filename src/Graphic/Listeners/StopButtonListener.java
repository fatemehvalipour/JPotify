package Graphic.Listeners;

import Data.Library;
import Data.Music;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StopButtonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Music.playingMusic != null){
            Music.playingMusic.stop();
            Music.isPlaying = false;
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
