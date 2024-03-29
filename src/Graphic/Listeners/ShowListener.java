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

/**
 * show the libraries in center of window
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class ShowListener implements MouseListener {
    private Graphic graphic;

    public ShowListener(Graphic graphic) {
        this.graphic = graphic;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (e.getSource() instanceof JButton && ((((JButton) e.getSource()).getText().equals("Music")) || (((JButton)e.getSource()).getText().equals("Albums")) || (((JButton)e.getSource()).getText().equals("PlayList")))) {
                if (((JButton) e.getSource()).getText().equals("Music")) {
                    graphic.showLibrary(Music.getMusics(), false);
                    PlayList.selectedPlayList = null;
                } else if (((JButton) e.getSource()).getText().equals("Albums")){
                    graphic.showLibrary(Album.getAlbums(), false);
                    PlayList.selectedPlayList = null;
                } else {
                    graphic.showLibrary(PlayList.getPlayLists(), true);
                    PlayList.selectedPlayList = null;
                }
            } else {
                for (Library album : Album.getAlbums()) {
                    if (((Album) album).getAlbumName().equals(((JButton) e.getSource()).getText())) {
                        graphic.showLibrary(((Album) album).getMusics(), false);
                        PlayList.selectedPlayList = null;
                        return;
                    }
                }
                for (Library playList : PlayList.getPlayLists()){
                    if (((PlayList) playList).getPlayListName().equals(((JButton) e.getSource()).getText())) {
                        if (e.getButton() == MouseEvent.BUTTON3){
                            for (Library Playlist : PlayList.getPlayLists()){
                                if (((JButton)e.getSource()).getText().equals(((PlayList)Playlist).getName())){
                                    PlaylistJPopUpMenu playlistJPopUpMenu = new PlaylistJPopUpMenu((PlayList) Playlist);
                                    playlistJPopUpMenu.show(e.getComponent(), e.getX(), e.getY());
                                    break;
                                }
                            }
                        } else {
                            graphic.showLibrary(((PlayList) playList).getPlayListMusics(), true);
                            PlayList.selectedPlayList = (PlayList) playList;
                            return;
                        }
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
