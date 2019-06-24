package Graphic.Listeners;

import Data.Album;
import Data.Library;
import Data.Music;
import Data.PlayList;
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
            if (e.getSource() instanceof JButton && ((((JButton) e.getSource()).getText().equals("Music")) || (((JButton)e.getSource()).getText().equals("Albums")) || (((JButton)e.getSource()).getText().equals("PlayList")))) {
                System.out.println(1);
                if (((JButton) e.getSource()).getText().equals("Music")) {
                    graphic.showLibrary(Music.getMusics(), false);
                } else if (((JButton) e.getSource()).getText().equals("Albums")){
                    graphic.showLibrary(Album.getAlbums(), false);
                } else {
                    graphic.showLibrary(PlayList.getPlayLists(), true);
                }
            } else {
                for (Library album : Album.getAlbums()) {
                    if (((Album) album).getAlbumName().equals(((JButton) e.getSource()).getText())) {
                        graphic.showLibrary(((Album) album).getMusics(), false);
                        return;
                    }
                }
                for (Library playList : PlayList.getPlayLists()){
                    if (((PlayList) playList).getPlayListName().equals(((JButton) e.getSource()).getText())) {
                        graphic.showLibrary(((PlayList) playList).getPlayListMusics(), true);
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
