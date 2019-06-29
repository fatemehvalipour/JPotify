package Graphic.Listeners;

import Data.Backup;
import Data.Music;
import Data.PlayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * a listener for handling like and dislike musics
 *
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class LikeButtonListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Music.playingMusic.isFavorite()) {
            try {
                ((PlayList)PlayList.getPlayLists().get(0)).addMusic(Music.playingMusic);
                Backup.save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Music.playingMusic.setFavorite(true);
            JButton like = (JButton) e.getSource();
            try {
                like.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("heart.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            ((PlayList)PlayList.getPlayLists().get(0)).removeMusic(Music.playingMusic);
            Backup.save();
            Music.playingMusic.setFavorite(false);
            JButton Like = (JButton) e.getSource();
            try {
                Like.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("..\\Containers\\heartBlue.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));

            } catch (IOException ex) {
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
