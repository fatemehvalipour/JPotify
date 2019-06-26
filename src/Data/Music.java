package Data;

import Graphic.Listeners.PlayMusicListener;
import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Music extends Library implements Serializable {
    //TODO make the public key word to private
    public static volatile Music playingMusic = null;
    public static boolean isPlaying = false;
    private boolean isFavorite;
    private String address;
    private Mp3File mp3File;
    private Player player;
    private FileInputStream musicFile;
    public static boolean repeat = false;
    public static boolean shuffle = false;
    //TODO change the icon if it is repeatable
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private int estimatedTime;
    private Timer timer;
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
        timer = new Timer();
        //TODO exception handling
    }

    public String getTitle() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            if (tag.getTitle() != null) {
                super.name = tag.getTitle();
            } else {
                super.name = "No Title";
            }
            return super.name;
        }
        return null;
    }

    public String getArtist() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            if (tag.getArtist() != null) {
                return tag.getArtist();
            } else {
                return "No Artist";
            }
        }
        return null;
    }

    public String getAlbum() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            if (tag.getAlbum() != null) {
                return tag.getAlbum();
            } else {
                return "No Album";
            }
        }
        return null;
    }

    @Override
    public Image getAlbumArt() throws IOException {
        if (mp3File.hasId3v2Tag()){
            ID3v2 tag = mp3File.getId3v2Tag();
            if (tag.getAlbumImage() != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tag.getAlbumImage());
                image = ImageIO.read(byteArrayInputStream);
            } else {
                image = ImageIO.read(getClass().getResource("default_song.jpg"));
            }
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
        timer = new Timer();
        timer.scheduleAtFixedRate(new estimatedTime(), 0, 1000);//TODO chera inja pak nakonim aks o ina ro??
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
        timer.cancel();
        estimatedTime = 0;
        paused = false;
        if( null != player) {
            player.close();
            totalSongLength = 0;
            pauseLocation = 0;
        }
    }

    public void resume() throws IOException, JavaLayerException {
        paused = false;
        timer = new Timer();
        timer.scheduleAtFixedRate(new estimatedTime(), 0, 1000);
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
        timer.cancel();
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

    public int getEstimatedTime(){
        return estimatedTime;
    }

    @Override
    public String toString() {
        return "Title: " + this.getTitle() + "\nArtist: " + this.getArtist() + "\nAlbum: " + this.getAlbum();
    }

    class estimatedTime extends TimerTask {
        @Override
        public void run() {
            estimatedTime++;
        }
    }

    public Player getPlayer() {
        return player;
    }
}
