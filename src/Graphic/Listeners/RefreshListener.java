package Graphic.Listeners;

import Data.Library;
import network.Friend;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RefreshListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        for (Friend friend : Friend.getFriends()){
            friend.refresh();
        }
        Library.getGraphic().showFriendActivity(Friend.getFriends());
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
