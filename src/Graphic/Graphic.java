package Graphic;

import Data.*;
import Graphic.Components.ListButton;
import Graphic.Components.PlayMusicJSlide;
import Graphic.Components.VoiceJSlide;
import Graphic.Containers.*;
import Graphic.Listeners.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
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
    private GridBagPanel centerGridBagPanel;
    private GridPanel eastGridPanel;
    private GridPanel westGridPanel;
    private PlayBoxPanel playBoxPanel;
    private BoxPanel southCenterBoxPanel;
    private VoiceJSlide voiceSlider;
    private ListButton musicButton;
    private ListButton albumButton;
    private ListButton playListButton;
    private PlayMusicJSlide playMusicJSlider;
    private JLabel albumArt;
    private JLabel nameOfMusic;
    private JLabel libraries;


    public Graphic() throws IOException {
        mainFrame = new JFrame("JPotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainBorderPanel = new BorderPanel(mainFrame);
        centerBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.CENTER);
        northBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.NORTH);
        eastBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.EAST);
        westBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.WEST);
        southBorderPanel = new BorderPanel(mainBorderPanel, BorderLayout.SOUTH);
        centerGridBagPanel = new GridBagPanel(centerBorderPanel);
        eastGridPanel = new GridPanel(eastBorderPanel, 1, BorderLayout.NORTH);
        westGridPanel = new GridPanel(westBorderPanel, 1, BorderLayout.NORTH);
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
        voiceSlider = new VoiceJSlide(southBorderPanel, -1000, 0);
        westBorderPanel.add(albumArt, BorderLayout.SOUTH);
        southBorderPanel.add(nameOfMusic, BorderLayout.WEST);
        libraries = new JLabel("Libraries");
        westGridPanel.add(libraries);
        libraries.setFont(libraries.getFont().deriveFont(22.0f));
        libraries.setForeground(Color.WHITE);
        libraries.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        musicButton = new ListButton(westGridPanel, "Music");
        musicButton.addMouseListener(new ShowListener(this));
        albumButton = new ListButton(westGridPanel, "Albums");
        albumButton.addMouseListener(new ShowListener(this));
        playListButton = new ListButton(westGridPanel, "PlayList");
        playListButton.addMouseListener(new ShowListener(this));
        westGridPanel.add(Box.createGlue());
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

    public void removeNameOfSong(){
        nameOfMusic.setText("");
    }

    public void showLibrary(ArrayList<Library> libraries, boolean isPlayList) throws IOException {
        centerGridBagPanel.removeAll();
        centerGridBagPanel.revalidate();
        JButton addButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("plus.png")).getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        addButton.setBackground(Color.BLACK);
        addButton.setBorder(null);
        addButton.setPreferredSize(new Dimension(200, 200));
        boolean add = false;
        if (libraries.size() == 0){
            if (isPlayList){
                centerGridBagPanel.getGbc().gridy = 0;
                centerGridBagPanel.getGbc().gridx = 0;
                centerGridBagPanel.add(addButton, centerGridBagPanel.getGbc());
                addButton.addMouseListener(new AddMusicToPlayListListener());
            } else {
                centerGridBagPanel.getGbc().gridy = 0;
                centerGridBagPanel.getGbc().gridx = 0;
                centerGridBagPanel.add(addButton, centerGridBagPanel.getGbc());
                addButton.addMouseListener(new AddMusicListener());
            }
            centerGridBagPanel.repaint();
            return;
        }
        if (libraries.get(0) instanceof PlayList){
            centerGridBagPanel.getGbc().gridy = 0;
            centerGridBagPanel.getGbc().gridx = 0;
            centerGridBagPanel.add(addButton, centerGridBagPanel.getGbc());
            add = true;
            System.out.println(123);
            addButton.addMouseListener(new AddPlayListListener());
        } else if (libraries.get(0) instanceof Music && isPlayList){
            centerGridBagPanel.getGbc().gridy = 0;
            centerGridBagPanel.getGbc().gridx = 0;
            centerGridBagPanel.add(addButton, centerGridBagPanel.getGbc());
            add = true;
            addButton.addMouseListener(new AddMusicToPlayListListener());
        } else if (libraries.get(0) instanceof Music && !isPlayList){
            centerGridBagPanel.getGbc().gridy = 0;
            centerGridBagPanel.getGbc().gridx = 0;
            centerGridBagPanel.add(addButton, centerGridBagPanel.getGbc());
            addButton.addMouseListener(new AddMusicListener());
            add = true;
        }
        int count = 0;
        centerGridBagPanel.getGbc().insets = new Insets(20, 20 , 20 , 20);
        for (int i = 0; i < (int)((libraries.size()/4)+1); i++){
            for (int j = 0; j < 4; j++){
                if (count == libraries.size()){
                    centerGridBagPanel.repaint();
                    return;
                }
                JPanel musicPanel = new JPanel();
                musicPanel.setLayout(new BoxLayout(musicPanel, BoxLayout.Y_AXIS));
                musicPanel.setBackground(Color.black);
                JLabel imageLabel = new JLabel(new ImageIcon(libraries.get(count).getAlbumArt().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
                imageLabel.setPreferredSize(new Dimension(200, 200));
                musicPanel.add(imageLabel);
                JButton nameBtn;
                if(libraries.get(count) instanceof Music){
                    nameBtn = new JButton("<html>" + ((Music) libraries.get(count)).getTitle() +
                            "<br>" + ((Music) libraries.get(count)).getArtist() + "</html>");
                    nameBtn.addMouseListener(new PlayMusicListener());
                } else if (libraries.get(count) instanceof Album){
                    nameBtn = new JButton("" + ((Album)libraries.get(count)).getAlbumName());
                    nameBtn.addMouseListener(new ShowListener(this));
                } else {
                    nameBtn = new JButton("" + ((PlayList)libraries.get(count)).getPlayListName());
                    nameBtn.addMouseListener(new ShowListener(this));
                }
                nameBtn.setBackground(Color.black);
                nameBtn.setForeground(Color.white);
                nameBtn.setPreferredSize(new Dimension(200, 40));
                musicPanel.add(nameBtn);
                if (add && i == 0){
                    add = false;
                    continue;
                }
                centerGridBagPanel.getGbc().gridx = j;
                centerGridBagPanel.getGbc().gridy = i;
                centerGridBagPanel.add(musicPanel, centerGridBagPanel.getGbc());
                count++;
            }
        }
    }

    public void showPlayList(ArrayList<PlayList> playLists) {

    }

    public void showFriendActivity(ArrayList<IP> IPList) {

    }
}
