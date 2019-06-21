package Graphic;

import Data.IP;
import Data.Library;
import Data.Music;
import Data.PlayList;
import Graphic.Components.JSlide;
import Graphic.Components.ProgressBar;
import Graphic.Containers.BorderPanel;
import Graphic.Containers.GridPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class Graphic {
    private JFrame mainFrame;
    private BorderPanel mainBorderPanel;
    private BorderPanel centerBorderPanel;
    private BorderPanel westBorderPanel;
    private BorderPanel eastBorderPanel;
    private BorderPanel northBorderPanel;
    private BorderPanel southBorderPanel;
    private GridPanel centerGridPanel;
    private GridPanel eastGridPanel;
    private GridPanel westGridPanel;
    private JSlide voiceSlider;
    private ProgressBar musicProgressBar;
    private JLabel albumArt;
    private JLabel nameOfMusic;

    public Graphic(){
        mainFrame = new JFrame("JPotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainBorderPanel = new BorderPanel(mainFrame);
        centerBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.CENTER);
        northBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.NORTH);
        eastBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.EAST);
        westBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.WEST);
        southBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.SOUTH);
        centerGridPanel = new GridPanel(centerBorderPanel, 4);
        eastGridPanel = new GridPanel(eastBorderPanel, 1);
        westGridPanel = new GridPanel(westBorderPanel, 1);
        mainFrame.setSize(950, 600);
        northBorderPanel.setPreferredSize(new Dimension(950, 35));
        eastBorderPanel.setPreferredSize(new Dimension( 120, 445));
        westBorderPanel.setPreferredSize(new Dimension(120 , 445));
        centerBorderPanel.setPreferredSize(new Dimension(710 , 445));
        southBorderPanel.setPreferredSize(new Dimension(950, 120));
        albumArt = new JLabel();
        nameOfMusic = new JLabel();
        voiceSlider = new JSlide(southBorderPanel, 0, 100);
        westBorderPanel.add(albumArt, BorderLayout.SOUTH);
        southBorderPanel.add(nameOfMusic, BorderLayout.WEST);
        musicProgressBar = new ProgressBar(southBorderPanel);
        //test
        JLabel jl = new JLabel("fatemeh");
        JLabel jl2 = new JLabel("ali");
        southBorderPanel.add(jl, BorderLayout.NORTH);
        southBorderPanel.add(jl2, BorderLayout.SOUTH);
        mainFrame.setVisible(true);

    }

    public void setAlbumArt(Music music) throws IOException {
        ByteArrayInputStream byteArrayImage = new ByteArrayInputStream(music.getAlbumArt());
        Image image = ImageIO.read(byteArrayImage);
        image = image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        albumArt.setPreferredSize(new Dimension(120, 120));
        albumArt.setIcon(new ImageIcon(image));
    }

    public void setNameOfSong(Music music){
        nameOfMusic.setText("<html>" + music.getTitle() + "<br>" + music.getArtist() + "</html>");
        nameOfMusic.setFont(nameOfMusic.getFont().deriveFont(22.0f));
        nameOfMusic.setForeground(Color.white);
        nameOfMusic.setPreferredSize(new Dimension(150,120));
    }

    public void removeAlbumArt(){
        albumArt.setIcon(null);
    }

    public void showLibrary(ArrayList<Library> libraries){

    }

    public void showPlayList(ArrayList<PlayList> playLists){

    }

    public void showFriendActivity(ArrayList<IP> IPList){

    }
}
