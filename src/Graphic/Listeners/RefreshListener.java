package Graphic.Listeners;

import Data.Library;
import network.Friend;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * a listener for refreshing information sent
 *
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 */
public class RefreshListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        for (Friend friend : Friend.getFriends()){
            friend.refresh();
        }
        Library.getGraphic().showFriendActivity();
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
