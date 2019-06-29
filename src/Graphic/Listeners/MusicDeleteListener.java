package Graphic.Listeners;

import Data.Backup;
import Data.Library;
import Data.Music;
import Data.PlayList;
import org.omg.PortableInterceptor.INACTIVE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MusicDeleteListener extends JPopupMenu {
    public MusicDeleteListener(Music music) {
        super();
//        JMenuItem delete = new JMenuItem("Delete");
//        delete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Music.getMusics().remove(music);
//                setVisible(false);
//            }
//        });

        JMenuItem removeFromPlaylist = new JMenuItem("Remove From Playlist");
        removeFromPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayList.selectedPlayList.removeMusic(music);
                setVisible(false);

            }
        });
        add(removeFromPlaylist);
        JMenuItem setOrder = new JMenuItem("Set Order");
        add(setOrder);
        setOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame miniFrame = new JFrame("write another name");
                miniFrame.setBackground(Color.black);
                JTextField textField = new JTextField();
                JButton okButton = new JButton("Ok");
                okButton.setPreferredSize(new Dimension(60, 50));
                textField.setPreferredSize(new Dimension(400,50));
                textField.setFont(textField.getFont().deriveFont(22.0f));
                miniFrame.setBounds(500, 500, 200, 200);
                miniFrame.setLayout(new FlowLayout());
                miniFrame.setSize(600, 200);
                miniFrame.add(textField);
                miniFrame.add(okButton);
                miniFrame.setVisible(true);
                textField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String place = ((JTextField)e.getSource()).getText();
                        PlayList.selectedPlayList.removeMusic(music);
                        PlayList.selectedPlayList.getPlayListMusics().add(Integer.parseInt(place)-1, music);
                    }
                });
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        miniFrame.setVisible(false);
                        Backup.save();
                        try {
                            Library.getGraphic().showLibrary(PlayList.getPlayLists(), true);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
        });
        //add(delete);
    }

    }
