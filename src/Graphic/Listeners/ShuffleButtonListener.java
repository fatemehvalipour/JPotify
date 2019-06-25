package Graphic.Listeners;

import Data.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class ShuffleButtonListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Music.shuffle){
            Music.shuffle = false;
            ((JButton)e.getSource()).setBackground(Color.BLACK);
        } else {
            Music.shuffle = true;
            ((JButton)e.getSource()).setBackground(Color.DARK_GRAY);
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
