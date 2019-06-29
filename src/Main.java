import Data.*;
import Graphic.Graphic;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import network.Friend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * the main class for running the player
 *
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8080);
                while (true){
                    new Friend(serverSocket.accept());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        JFrame miniFrame = new JFrame("log in");
        miniFrame.setIconImage(new ImageIcon("src\\user.png").getImage());
        miniFrame.setLayout(new FlowLayout());
        miniFrame.setBackground(Color.black);
        JTextField textField = new JTextField();
        JButton okButton = new JButton("Ok");
        okButton.setPreferredSize(new Dimension(60, 50));
        textField.setPreferredSize(new Dimension(400,50));
        textField.setFont(textField.getFont().deriveFont(22.0f));
        miniFrame.setBounds(500, 500, 200, 200);

        miniFrame.setSize(600, 200);
        miniFrame.add(textField);
        miniFrame.add(okButton);
        miniFrame.setVisible(true);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = ((JTextField)e.getSource()).getText();
                User.setUsername(newName);
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miniFrame.setVisible(false);
                new PlayList("Favourite Songs", false, false);
                new PlayList("Shared Songs", false, false);
                Backup.load();
                Graphic graphic = null;
                try {
                    graphic = new Graphic();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Library.setGraphic(graphic);
            }
        });

    }
}
