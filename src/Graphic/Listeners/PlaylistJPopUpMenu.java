package Graphic.Listeners;

import Data.Backup;
import Data.Library;
import Data.Music;
import Data.PlayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlaylistJPopUpMenu extends JPopupMenu {

    public PlaylistJPopUpMenu(PlayList playList) {
        super();
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem rename = new JMenuItem("Rename");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playList.isRemovable()) {
                    PlayList.getPlayLists().remove(playList);
                    Backup.save();
                    try {
                        Library.getGraphic().showLibrary(PlayList.getPlayLists(), true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                setVisible(false);
            }
        });
        rename.addActionListener(new ActionListener() {
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
                        String newName = ((JTextField)e.getSource()).getText();
                        System.out.println(playList.isNameChange());
                        if (playList.isNameChange()){
                            playList.renamePlayList(playList, newName);
                        }
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
        add(delete);
        add(rename);
    }
}
