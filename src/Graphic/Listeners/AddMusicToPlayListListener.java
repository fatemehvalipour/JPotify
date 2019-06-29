package Graphic.Listeners;

import Data.Backup;
import Data.Library;
import Data.Music;
import Data.PlayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * add music to specific playlist
 *
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class AddMusicToPlayListListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        JFrame musicsFrame = new JFrame("Add Music");
        musicsFrame.setVisible(true);
        musicsFrame.setSize(500, 200);
        JPanel interfacePanel = new JPanel();
        interfacePanel.setBackground(Color.black);
        interfacePanel.setPreferredSize(new Dimension(500, 200));
        musicsFrame.add(new JScrollPane(interfacePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
        interfacePanel.setLayout(new BoxLayout(interfacePanel, BoxLayout.Y_AXIS));
        for (Library music : Music.getMusics()){
            JButton musicButton = new JButton(((Music)music).getTitle());
            musicButton.setBackground(Color.black);
            musicButton.setForeground(Color.white);
            musicButton.setFocusable(false);
            interfacePanel.add(musicButton);
            musicButton.setPreferredSize(new Dimension(500, 30));
            musicButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        PlayList.selectedPlayList.addMusic((Music)music);
                        if (PlayList.selectedPlayList.getPlayListName().equals("Favourite Songs")){
                            ((Music) music).setFavorite(true);
                        }
                        Backup.save();
                        musicsFrame.setVisible(false);
                        Library.getGraphic().showLibrary(PlayList.selectedPlayList.getPlayListMusics(), true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
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
            });
        }
        interfacePanel.add(Box.createGlue());
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
