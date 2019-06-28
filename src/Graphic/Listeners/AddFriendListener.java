package Graphic.Listeners;

import network.Friend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;

public class AddFriendListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        JFrame miniFrame = new JFrame("write IP");
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
                try {
                    System.out.println(50);
                    new Friend(new Socket(((JTextField)e.getSource()).getText(), 8080));
                    System.out.println(51);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miniFrame.setVisible(false);
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
