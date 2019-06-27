package Graphic.Listeners;

import network.Friend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShowFriendMusicsListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        String friendName = ((JButton)e.getSource()).getText().substring(33);
        friendName = friendName.substring(0, friendName.indexOf("<"));
        for (Friend friend : Friend.getFriends()){
            if (friend.getName().equals(friendName)){
                Friend.selectedFriend = friend;
                break;
            }
        }
        JFrame musicFrame = new JFrame("Download Music");
        musicFrame.setVisible(true);
        musicFrame.setSize(500, 200);
        JPanel interfacePanel = new JPanel();
        interfacePanel.setBackground(Color.black);
        musicFrame.add(new JScrollPane(interfacePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
        interfacePanel.setLayout(new BoxLayout(interfacePanel, BoxLayout.Y_AXIS));
        for (String music : Friend.selectedFriend.getMusics()){
            JButton musicButton = new JButton(music);
            musicButton.setFocusable(false);
            musicButton.setBackground(Color.black);
            musicButton.setPreferredSize(new Dimension(500, 30));
            interfacePanel.add(musicButton);
            musicButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Friend.selectedFriend.download(((JButton)e.getSource()).getText());
                    musicFrame.setVisible(false);
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
