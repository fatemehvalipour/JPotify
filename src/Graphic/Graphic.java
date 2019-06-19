package Graphic;

import Data.Music;
import Graphic.Containers.BorderPanel;
import Graphic.Containers.GridPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Graphic {
    private JFrame mainFrame;
    private BorderPanel mainBorderPanel;
    private BorderPanel centerBorderPanel;
    private BorderPanel westBorderPanel;
    private BorderPanel eastBorderPanel;
    private BorderPanel northBorderPanel;
    private GridPanel centerGridPanel;
    private GridPanel eastGridPanel;
    private GridPanel westGridPanel;
    private JLabel albumArt;

    public Graphic(){
        mainFrame = new JFrame("JPotify");
        mainBorderPanel = new BorderPanel(mainFrame);
        centerBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.CENTER);
        northBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.NORTH);
        eastBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.EAST);
        westBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.WEST);
        centerGridPanel = new GridPanel(centerBorderPanel, 4);
        eastGridPanel = new GridPanel(eastBorderPanel, 1);
        westGridPanel = new GridPanel(westBorderPanel, 1);
        mainFrame.setSize(950, 600);
        northBorderPanel.setPreferredSize(new Dimension(950, 35));
        eastBorderPanel.setPreferredSize(new Dimension( 120, 505));
        westBorderPanel.setPreferredSize(new Dimension(120 , 505));
        centerBorderPanel.setPreferredSize(new Dimension(710 , 505));
        albumArt = null;
        mainFrame.setVisible(true);

    }

    public void setAlbumArt(Music music) throws IOException {
        ByteArrayInputStream byteArrayImage = new ByteArrayInputStream(music.getAlbumArt());
        Image image = ImageIO.read(byteArrayImage);
        image.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        albumArt.setIcon(new ImageIcon(image));
        westBorderPanel.add(albumArt, BorderLayout.SOUTH);
    }

    public void removeAlbumArt(){
        albumArt.setIcon(null);
        westBorderPanel.add(albumArt, BorderLayout.SOUTH);
    }
}
