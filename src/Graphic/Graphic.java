package Graphic;

import Data.*;
import Graphic.Components.ListButton;
import Graphic.Components.PlayMusicJSlide;
import Graphic.Components.VoiceJSlide;
import Graphic.Containers.*;
import Graphic.Listeners.AddMusicListener;
import Graphic.Listeners.ShowListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;

//TODO check all the sizes plz
public class Graphic {
    private JFrame mainFrame;
    private BorderPanel mainBorderPanel;
    private BorderPanel centerBorderPanel;
    private BorderPanel westBorderPanel;
    private BorderPanel eastBorderPanel;
    private BorderPanel northBorderPanel;
    private BorderPanel southBorderPanel;
    private SearchPanel searchPanel;
    private GridPanel centerGridPanel;
    private GridPanel eastGridPanel;
    private GridPanel westGridPanel;
    private PlayBoxPanel playBoxPanel;
    private BoxPanel southCenterBoxPanel;
    private VoiceJSlide voiceSlider;
    private ListButton musicButton;
    private ListButton albumButton;
    private ListButton playListButton;
    private PlayMusicJSlide playMusicJSlider;
    private ListButton add;
    private JLabel albumArt;
    private JLabel nameOfMusic;
    private JLabel libraries;
    private Music playingMusic;


    public Graphic() throws IOException {
        mainFrame = new JFrame("JPotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainBorderPanel = new BorderPanel(mainFrame);
        centerBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.CENTER);
        northBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.NORTH);
        eastBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.EAST);
        westBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.WEST);
        southBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.SOUTH);
        centerGridPanel = new GridPanel(centerBorderPanel, 4, BorderLayout.NORTH);
        eastGridPanel = new GridPanel(eastBorderPanel, 1, BorderLayout.NORTH);
        westGridPanel = new GridPanel(westBorderPanel, 1, BorderLayout.NORTH);
        centerGridPanel = new GridPanel(centerBorderPanel, 4, BorderLayout.NORTH);
        southCenterBoxPanel = new BoxPanel(BoxLayout.Y_AXIS);
        playBoxPanel = new PlayBoxPanel();
        southCenterBoxPanel.add(playBoxPanel);
        southBorderPanel.add(southCenterBoxPanel, BorderLayout.CENTER);
        mainFrame.setSize(950, 600);
        northBorderPanel.setPreferredSize(new Dimension(950, 35));
        eastBorderPanel.setPreferredSize(new Dimension(120, 445));
        westBorderPanel.setPreferredSize(new Dimension(120, 445));
        centerBorderPanel.setPreferredSize(new Dimension(710, 445));
        southBorderPanel.setPreferredSize(new Dimension(950, 120));
        User user = new User("Water Bottle", "1234");
        searchPanel = new SearchPanel(user);
        mainBorderPanel.add(searchPanel, BorderLayout.PAGE_START);
        albumArt = new JLabel();
        nameOfMusic = new JLabel();
        voiceSlider = new VoiceJSlide(southBorderPanel, 0, 100);
        westBorderPanel.add(albumArt, BorderLayout.SOUTH);
        southBorderPanel.add(nameOfMusic, BorderLayout.WEST);
        libraries = new JLabel("Libraries");
        westGridPanel.add(libraries);
        libraries.setFont(libraries.getFont().deriveFont(22.0f));
        libraries.setForeground(Color.white);
        add = new ListButton(westGridPanel, "Add");
        add.addMouseListener(new AddMusicListener());
        musicButton = new ListButton(westGridPanel, "Music");
        musicButton.addMouseListener(new ShowListener(this));
        albumButton = new ListButton(westGridPanel, "Albums");
        albumButton.addMouseListener(new ShowListener(this));
        playListButton = new ListButton(westGridPanel, "PlayList");
        playMusicJSlider = new PlayMusicJSlide();
        southCenterBoxPanel.add(playMusicJSlider);
        mainFrame.setVisible(true);

    }

    public void setAlbumArt(Music music) throws IOException {
        Image image = music.getAlbumArt();
        image = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        albumArt.setPreferredSize(new Dimension(120, 120));
        albumArt.setIcon(new ImageIcon(image));
    }

    public void setNameOfSong(Music music) {
        nameOfMusic.setText("<html>" + music.getTitle() + "<br>" + music.getArtist() + "</html>");
        nameOfMusic.setFont(nameOfMusic.getFont().deriveFont(22.0f));
        nameOfMusic.setForeground(Color.white);
        nameOfMusic.setPreferredSize(new Dimension(150, 120));
    }

    public void removeAlbumArt() {
        albumArt.setIcon(null);
    }

    public void showLibrary(ArrayList<Library> libraries) {
        centerGridPanel.removeAll();
        centerGridPanel.revalidate();
        for (Library library : libraries) {
            centerGridPanel.add(library);
        }
        centerGridPanel.repaint();
    }

    public void showPlayList(ArrayList<PlayList> playLists) {

    }

    public void showFriendActivity(ArrayList<IP> IPList) {

    }
}
