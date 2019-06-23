package Graphic.Listeners;

import Data.Music;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RepeatbuttonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

         if (Music.repeat) {
              Music.repeat = false;
             System.out.println(false);
         }  else {
             Music.repeat = true;
             System.out.println(true);
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
