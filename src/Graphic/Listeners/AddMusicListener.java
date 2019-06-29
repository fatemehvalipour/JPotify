package Graphic.Listeners;

import Data.Album;
import Data.Backup;
import Data.Library;
import Data.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLOutput;

/**
 * adds music to Music arraylist
 *
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class AddMusicListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                Music music = new Music(chooser.getSelectedFile().getAbsolutePath());
                Album.addMusicToAlbum(music);
                Library.getGraphic().showLibrary(Music.getMusics(), false);
                Backup.save();
            } catch (InvalidDataException e1) {
                System.out.println("Can't open this file");
            } catch (IOException e1) {
                System.out.println("Can't open this file");
            } catch (UnsupportedTagException e1) {
                System.out.println("Can't open this file");
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
