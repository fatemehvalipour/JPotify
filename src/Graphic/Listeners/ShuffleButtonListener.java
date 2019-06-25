package Graphic.Listeners;

import Data.Music;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class ShuffleButtonListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Music.shuffle){
            Music.shuffle = false;
        } else {
            Music.shuffle = true;
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
