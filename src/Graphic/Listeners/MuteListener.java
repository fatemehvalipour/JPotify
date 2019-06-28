package Graphic.Listeners;

import Data.Music;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MuteListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Music.isMute){
            try {
                ((JButton)e.getSource()).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("mute.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                Music.playingMusic.getPlayer().setVol(-60.0f);
                Music.isMute = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                ((JButton)e.getSource()).setIcon(new ImageIcon(ImageIO.read(getClass().getResource("..\\Containers\\voice.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                Music.playingMusic.getPlayer().setVol(Music.volume);
                Music.isMute = false;
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
