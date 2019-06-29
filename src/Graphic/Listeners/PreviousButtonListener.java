package Graphic.Listeners;

import Data.Library;
import Data.Music;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

/**
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 */
public class PreviousButtonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Music.previous();
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
