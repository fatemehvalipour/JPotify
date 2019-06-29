package Graphic.Listeners;

import Data.Library;
import Data.Lyric;
import Data.Music;
import Data.PlayList;
import Graphic.Graphic;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class PlayMusicListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(PlayList.selectedPlayList);
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (Music.playingMusic != null) {
                Music.playingMusic.stop();
                Library.getGraphic().removeAlbumArt();
                Library.getGraphic().removeNameOfSong();
            }
            try {
                for (Library music : Music.getMusics()) {
                    if (((JButton) e.getSource()).getText().equals("<html>" + ((Music) music).getTitle() + "<br>" + ((Music) music).getArtist() + "</html>")) {
                        try {
                            Music.isPlaying = true;
                            Music.playingMusic = (Music) music;
                            Library.getGraphic().setNameOfSong(Music.playingMusic);
                            Library.getGraphic().setAlbumArt(Music.playingMusic);
                            Music.playingMusic.play();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (JavaLayerException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } catch (ConcurrentModificationException ex) {
                System.out.println("Please Wait...");
            }
        } else if (e.getButton() == MouseEvent.BUTTON3 && PlayList.selectedPlayList != null){
            for (Library music : Music.getMusics()) {
                if (((JButton) e.getSource()).getText().equals("<html>" + ((Music) music).getTitle() + "<br>" + ((Music) music).getArtist() + "</html>")) {
                    MusicDeleteListener musicDeleteListener = new MusicDeleteListener((Music) music);
                    musicDeleteListener.show(e.getComponent(), e.getX(), e.getY());
                    break;
                }
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
