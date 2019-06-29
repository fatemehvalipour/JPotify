package Graphic.Listeners;

import Data.Music;
import Data.PlayList;
import javazoom.jl.player.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class volumeChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        if (Music.playingMusic != null) {
            Player player = Music.playingMusic.getPlayer();
            if (!Music.isMute) {
                player.setVol((((float) ((JSlider) e.getSource()).getValue()) / 100) * 6f);
            }
            Music.volume = (((float) ((JSlider) e.getSource()).getValue()) / 100) * 6f;
        }
    }
}
