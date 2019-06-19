package Data;
import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Music extends Library {
    private String address;
    private Mp3File mp3File;
    private Player player;
    private FileInputStream musicFile;
    private boolean repeat;
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private static ArrayList<Music> musics = new ArrayList<>();

    public Music(String address) throws InvalidDataException, UnsupportedTagException, IOException, JavaLayerException {
        this.address = address;
        musicFile = null;
        repeat = false;
        paused = false;
        totalSongLength = 0;
        pauseLocation = 0;
        mp3File = new Mp3File(address);
        musics.add(this);
        //TODO exception handling
    }

    public String getTitle(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            super.name = tag.getTitle();
            return tag.getTitle();
        }
        return null;
    }

    public String getArtist(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getArtist();
        }
        return null;
    }

    public String getAlbum(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getAlbum();
        }
        return null;
    }

    public byte[] getAlbumArt(){
        if (mp3File.hasId3v2Tag()){
            ID3v2 tag = mp3File.getId3v2Tag();
            image = tag.getAlbumImage();
            return tag.getAlbumImage();
        }
        return null;
    }

    public static ArrayList<Music> getMusics() {
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
                if (player.isComplete() && repeat){
                    play();
                }
            } catch (JavaLayerException | IOException e) {
                System.out.println("can't play this music");
            }
        }).start();
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

    public void pause(){
        paused = true;
        if (player != null){
            try {
                pauseLocation = musicFile.available();
                player.close();
            } catch (IOException e) {
                System.out.println("can't pause this music");
            }
        }
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public String toString() {
        return "Title: " + this.getTitle() + "\nArtist: " + this.getArtist() + "\nAlbum: " + this.getAlbum();
    }
}
