package Graphic.Containers;

import Data.Lyric;
import Data.Music;
import Data.User;
import Graphic.Listeners.SearchListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class SearchPanel extends JPanel {
    private JTextField searchBar;
    private JTextArea username;

    public SearchPanel(String user) throws IOException {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        searchBar = new JTextField();
        searchBar.addActionListener(new SearchListener());
        //searchBar.
        //searchBar.getAccessibleContext();
        username = new JTextArea(user);
        JPanel searchBox = new JPanel();
        searchBox.setBackground(Color.WHITE);
        searchBox.setLayout(new FlowLayout());
        JLabel searchIcon = new JLabel();
        searchIcon.setBackground(Color.WHITE);
        searchIcon.setPreferredSize(new Dimension(10, 10));
        searchBar.setBackground(Color.WHITE);
        searchBar.setPreferredSize(new Dimension(80, 20));
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
        JButton lyric = new JButton("Lyric");
        lyric.setBackground(Color.black);
        lyric.setFocusable(false);
        lyric.setBorder(null);
        lyric.setForeground(Color.white);
        add(lyric, BorderLayout.CENTER);
        lyric.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame lyricFrame = new JFrame("lyric");
                lyricFrame.setSize(500, 500);
                lyricFrame.setVisible(true);
                JPanel interfacePanel = new JPanel();
                interfacePanel.setLayout(new BoxLayout(interfacePanel, BoxLayout.Y_AXIS));
                interfacePanel.add(Box.createGlue());
                interfacePanel.setPreferredSize(new Dimension(450, 450));
                lyricFrame.add(new JScrollPane(interfacePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
                String lyric = "<html>";
                try {
                    for (String line : Lyric.getSongLyrics(Music.playingMusic.getArtist(), Music.playingMusic.getTitle())){
                        lyric = lyric.concat(line + "<br>");
                    }
                    lyric = lyric.concat("</html>");
                    JLabel text = new JLabel(lyric);
                    text.setPreferredSize(new Dimension(500, 500));
                    interfacePanel.add(text);
                } catch (IOException e1) {
                    System.out.println("There is no lyric in net");
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
}
