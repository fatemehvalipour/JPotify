package Data;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Music extends Library {
    //TODO make the public key word to private
    public static Music playingMusic = null;
    public static boolean isPlaying = false;
    private boolean isFavorite;
    private String address;
    private Mp3File mp3File;
    private Player player;
    private FileInputStream musicFile;
    public static boolean repeat = false;
    //TODO change the icon if it is repeatable
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private static ArrayList<Library> musics = new ArrayList<>();

    public Music(String address) throws InvalidDataException, UnsupportedTagException, IOException {
        this.address = address;
        musicFile = null;
        repeat = false;
        paused = false;
        totalSongLength = 0;
        pauseLocation = 0;
        mp3File = new Mp3File(address);
        musics.add(this);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(200, 240));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton imageButton = new JButton(new ImageIcon(this.getAlbumArt().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imageButton.setFocusable(false);
        JLabel name = new JLabel("<html>" + this.getTitle() + "<br>" + this.getArtist() + "</html>",  SwingConstants.CENTER);
        imageButton.setBackground(Color.BLACK);
        imageButton.setPreferredSize(new Dimension(200, 200));
        name.setBackground(Color.BLACK);
        name.setPreferredSize(new Dimension(200, 10));
        add(imageButton);
        name.setVerticalAlignment(SwingConstants.CENTER);
        add(name);
        //TODO exception handling
    }

    public String getTitle() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            super.name = tag.getTitle();
            return tag.getTitle();
        }
        return null;
    }

    public String getArtist() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getArtist();
        }
        return null;
    }

    public String getAlbum() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getAlbum();
        }
        return null;
    }

    public Image getAlbumArt() throws IOException {
        if (mp3File.hasId3v2Tag()){
            ID3v2 tag = mp3File.getId3v2Tag();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tag.getAlbumImage());
            image = ImageIO.read(byteArrayInputStream);
            return image;
        }
        return null;
    }

    public static ArrayList<Library> getMusics() {
        return musics;
    }

    public String getAddress() {
        return address;
    }

    public void play() throws IOException, JavaLayerException {
        musicFile = new FileInputStream(address);
        totalSongLength = musicFile.available();
        player = new Player(musicFile);
        new Thread(() -> {
            try {
                player.play();
                if (player.isComplete() && repeat) {
                    play();
                }
            } catch (JavaLayerException | IOException e) {
                System.out.println("can't play this music");
            }
        }).start();
    }

    public void stop(){
        paused = false;
        if( null != player) {
            player.close();
            totalSongLength = 0;
            pauseLocation = 0;
        }
    }

    public void resume() throws IOException, JavaLayerException {
        paused = false;
        musicFile = new FileInputStream(address);
        musicFile.skip(totalSongLength - pauseLocation);
        player = new Player(musicFile);
        new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                System.out.println("can't resume this music");
            }
        }).start();
    }

    public void pause() {
        paused = true;
        if (player != null) {
            try {
                pauseLocation = musicFile.available();
                player.close();
            } catch (IOException e) {
                System.out.println("can't pause this music");
            }
        }
    }

    public long getDuration(){
        return mp3File.getLengthInSeconds();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    @Override
    public String toString() {
        return "Title: " + this.getTitle() + "\nArtist: " + this.getArtist() + "\nAlbum: " + this.getAlbum();
    }
}
