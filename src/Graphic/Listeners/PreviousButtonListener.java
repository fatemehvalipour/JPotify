package Graphic.Listeners;

import Data.Library;
import Data.Music;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

public class PreviousButtonListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        int index = 0;
        if (Music.playingMusic != null) {
            for (int i = 0; i < Music.getMusics().size(); i++) {
                if (Music.playingMusic.equals(Music.getMusics().get(i))) {
                    if (!Music.shuffle) {
                        if (i == 0) {
                            index = Music.getMusics().size() - 1;
                        } else {
                            index = i - 1;
                        }
                        break;
                    } else {
                        Random rand = new Random();
                        index = rand.nextInt(Music.getMusics().size());
                    }
                }
            }
            try {
                Music.playingMusic.stop();
                Music.playingMusic = (Music) (Music.getMusics().get(index));
                Library.getGraphic().setAlbumArt(Music.playingMusic);
                Library.getGraphic().setNameOfSong(Music.playingMusic);
                ((Music) (Music.getMusics().get(index))).play();
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
