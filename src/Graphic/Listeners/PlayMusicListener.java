package Graphic.Listeners;

import Data.Music;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class PlayMusicListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof Music){
            try {
                ((Music) e.getSource()).play();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JavaLayerException ex) {
                ex.printStackTrace();
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
