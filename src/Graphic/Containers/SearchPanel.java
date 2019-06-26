package Graphic.Containers;

import Data.User;
import Graphic.Listeners.SearchListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SearchPanel extends JPanel {
    private JTextField searchBar;
    private JTextArea username;

    public SearchPanel(String user) throws IOException {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        searchBar = new JTextField("Search");
        searchBar.addActionListener(new SearchListener());
        //searchBar.getAccessibleContext();
        //searchBar.
        username = new JTextArea(user);
        JPanel searchBox = new JPanel();
        searchBox.setBackground(Color.WHITE);
        searchBox.setLayout(new FlowLayout());
        JLabel searchIcon = new JLabel();
        searchIcon.setBackground(Color.WHITE);
        searchIcon.setPreferredSize(new Dimension(10, 10));
        searchBar.setBackground(Color.WHITE);
        searchBar.setPreferredSize(new Dimension(70, 20));
        Image image = ImageIO.read(getClass().getResource("search.png")).getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        searchIcon.setIcon(new ImageIcon(image));
        searchBox.add(searchIcon);
        searchBox.add(searchBar);
        searchBox.setPreferredSize(new Dimension(100, 30));
        username.setPreferredSize(new Dimension(150, 30));
        username.setBackground(Color.BLACK);
        username.setFont(username.getFont().deriveFont(22.0f));
        username.setForeground(Color.white);
        add(searchBox, BorderLayout.WEST);
        add(username, BorderLayout.EAST);
    }
}
