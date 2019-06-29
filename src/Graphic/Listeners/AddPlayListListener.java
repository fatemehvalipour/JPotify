package Graphic.Listeners;

import Data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * add a playist to playlist arraylist
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class AddPlayListListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        JFrame miniFrame = new JFrame("write playlist's name");
        JTextField textField = new JTextField();
        JButton okButton = new JButton("Ok");
        okButton.setPreferredSize(new Dimension(60, 50));
        textField.setPreferredSize(new Dimension(400,50));
        textField.setFont(textField.getFont().deriveFont(22.0f));
        miniFrame.setBounds(500, 500, 200, 200);
        miniFrame.setLayout(new FlowLayout());
        miniFrame.setBackground(Color.black);
        miniFrame.setSize(600, 200);
        miniFrame.add(textField);
        miniFrame.add(okButton);
        miniFrame.setVisible(true);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Library album : Album.getAlbums()){
                    if(((JTextField)e.getSource()).getText().equals(((Album)album).getAlbumName())
                            || ((JTextField)e.getSource()).getText().equals("Favourite Songs")
                            || ((JTextField)e.getSource()).getText().equals("Shared Songs")
                    ){
                        textField.setText("Please enter another name");
                        return;
                    }
                }

                PlayList playList = new PlayList(((JTextField)e.getSource()).getText(), true, true);
                Backup.save();
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        miniFrame.setVisible(false);
                        try {
                            Library.getGraphic().showLibrary(PlayList.getPlayLists(), true);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
        });
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
