package Graphic.Listeners;

import Data.Album;
import Data.Backup;
import Data.Music;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLOutput;

public class AddMusicListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            try {
                Music music = new Music(chooser.getSelectedFile().getAbsolutePath());
                Album.addMusicToAlbum(music);
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
