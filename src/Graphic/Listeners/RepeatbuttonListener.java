package Graphic.Listeners;

import Data.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RepeatbuttonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

         if (Music.repeat) {
              Music.repeat = false;
             ((JButton)e.getSource()).setBackground(Color.BLACK);
         }  else {
             Music.repeat = true;
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
