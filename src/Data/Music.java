package Data;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Music extends Library implements Serializable {
    //TODO make the public key word to private
    public static volatile Music playingMusic = null;
    public static boolean isPlaying = false;
    public static float volume = 0.0f;
    private File file;
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
    private static volatile ArrayList<Library> musics = new ArrayList<>();

    public Music(String address) throws InvalidDataException, UnsupportedTagException, IOException {
        this.address = address;
        musicFile = new FileInputStream(address);
        repeat = false;
        paused = false;
        totalSongLength = 0;
        pauseLocation = 0;
        mp3File = new Mp3File(address);
        musics.add(this);
        timer = new Timer();
        file = new File(address);
        //TODO exception handling
    }

    public String getTitle() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            if (!tag.getTitle().equals("null")) {
                super.name = tag.getTitle();
            } else {
                super.name = "No Title";
            }
            return super.name;
        }
        return null;
//        byte[] namebytes = new byte[30];
//        for (int i = 3 ; i < 33 ;i++){
//            namebytes[i - 3] = getLastBytes()[i];
//        }
//        return new String(namebytes);
    }

    public byte[] getLastBytes(){
        byte[] lastBytes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytesArray = new byte[(int) file.length()];
            fileInputStream.read(bytesArray);
            lastBytes = new byte[128];
            int j = 0;
            for (int i = bytesArray.length - 128 ; j < 128 ;i++){
                lastBytes[j] = bytesArray[i];
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastBytes;
    }

    public String getArtist() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            if (!tag.getArtist().equals("null")) {
                return tag.getArtist();
            } else {
                return "No Artist";
            }
        }
        return null;
//        byte[] artistbytes = new byte[30];
//        for (int i = 33; i < 63; i++){
//            artistbytes[i - 33] = getLastBytes()[i];
//        }
//        return new String(artistbytes);
    }

    public String getAlbum() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            String str = tag.getAlbum();
            if (str != null) {
                return str;
            } else {
                return "No Album";
            }
        }
        return null;
//        byte[] albumbytes = new byte[30];
//        for (int i = 63 ;i < 93; i++){
//            albumbytes[i - 63] = getLastBytes()[i];
//        }
//        return new String(albumbytes);
    }

    @Override
    public Image getAlbumArt() throws IOException {
        if (mp3File.hasId3v2Tag()) {
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
                musics.remove(playingMusic);
                musics.add(0, playingMusic);
                for (Library album : Album.getAlbums()) {
                    if (((Album) album).getAlbumName().equals(playingMusic.getAlbum())) {
                        Album.getAlbums().remove(album);
                        Album.getAlbums().add(0, album);
                        break;
                    }
                }
                if (((PlayList) PlayList.getPlayLists().get(1)).getPlayListMusics().contains(playingMusic)) {
                    ((PlayList) PlayList.getPlayLists().get(1)).getPlayListMusics().remove(playingMusic);
                    ((PlayList) PlayList.getPlayLists().get(1)).getPlayListMusics().add(0, playingMusic);
                }
                player.play();
            } catch (JavaLayerException e) {
                System.out.println("can't play this music");
            }
        }).start();
        player.setVol(volume);
    }

    public void stop() {
        timer.cancel();
        estimatedTime = 0;
        paused = false;
        if (null != player) {
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

    public static void previous() {
        int index = 0;
        if (Music.playingMusic != null) {
            for (int i = 0; i < Music.getMusics().size(); i++) {
                if (Music.playingMusic.equals(Music.getMusics().get(i))) {
                    if (!Music.shuffle) {
                        if (i == 0) {
                            index = Music.getMusics().size() - 1;
                        } else {
                            index = i - 1;
                        }
                        break;
                    } else {
                        Random rand = new Random();
                        index = rand.nextInt(Music.getMusics().size());
                    }
                }
            }
            try {
                Music.playingMusic.stop();
                Music.playingMusic = (Music) (Music.getMusics().get(index));
                Library.getGraphic().setAlbumArt(Music.playingMusic);
                Library.getGraphic().setNameOfSong(Music.playingMusic);
                ((Music) (Music.getMusics().get(index))).play();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JavaLayerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void next() {
        int index = 0;
        if (Music.playingMusic != null) {
            for (int i = 0; i < Music.getMusics().size(); i++) {
                if (Music.playingMusic.equals(Music.getMusics().get(i))) {
                    if (!Music.shuffle) {
                        if (i == Music.getMusics().size() - 1) {
                            index = 0;
                        } else {
                            index = i + 1;
                        }
                        break;
                    } else {
                        Random rand = new Random();
                        index = rand.nextInt(Music.getMusics().size());
                        break;
                    }
                }
            }
            try {
                Music.playingMusic.stop();
                Music.playingMusic = (Music) (Music.getMusics().get(index));
                Library.getGraphic().setAlbumArt(Music.playingMusic);
                Library.getGraphic().setNameOfSong(Music.playingMusic);
                ((Music) (Music.getMusics().get(index))).play();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JavaLayerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public long getDuration() {
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

    public int getEstimatedTime() {
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

    public long getTotalSongLength() {
        return totalSongLength;
    }

    public void setPauseLocation(long pauseLocation) {
        this.pauseLocation = pauseLocation;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
