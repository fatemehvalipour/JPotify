package Graphic.Listeners;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * a listener for playing videos
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class PlayVideoListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String[] run = new String[]{"C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", chooser.getSelectedFile().getAbsolutePath()};
            try {
                Runtime.getRuntime().exec(run);
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
