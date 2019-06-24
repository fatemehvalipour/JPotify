package Graphic.Listeners;

import Data.Album;
import Data.Library;
import Data.Music;
import Graphic.Graphic;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class ShowListener implements MouseListener {
    private Graphic graphic;

    public ShowListener(Graphic graphic) {
        this.graphic = graphic;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (e.getSource() instanceof JButton && (((JButton) e.getSource()).getIcon() == null)) {
                if (((JButton) e.getSource()).getText().equals("Music")) {
                    graphic.showLibrary(Music.getMusics());
                } else {
                    graphic.showLibrary(Album.getAlbums());
                }
            } else {
                for (Library album : Album.getAlbums()) {
                    if (((Album) album).getAlbumName().equals(((JButton) e.getSource()).getText())) {
                        graphic.showLibrary(((Album) album).getMusics());
                        return;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
