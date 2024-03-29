package Data;

import com.mpatric.mp3agic.*;
import com.sun.org.apache.bcel.internal.generic.LASTORE;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class hold a music attributes and some method for working with music
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class Music extends Library implements Serializable {
    //TODO make the public key word to private
    public static volatile Music playingMusic = null;
    public static boolean isPlaying = false;
    public static float volume = 0.0f;
    public static boolean isMute = false;
    private File file;
    private boolean isFavorite;
    private long lastPlaytime;
    private String address;
    private Mp3File mp3File;
    private Player player;
    private FileInputStream musicFile;
    public static boolean repeat = false;
    public static boolean shuffle = false;
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private int estimatedTime;
    private Timer timer;
    private static volatile ArrayList<Library> musics = new ArrayList<>();

    /**
     * a constructor for music class
     *
     * @param address the path for music file
     * @throws InvalidDataException for invalid data
     * @throws UnsupportedTagException this for problem in music metadata
     * @throws IOException for problem with music file
     */
    public Music(String address) throws InvalidDataException, UnsupportedTagException, IOException {
        this.address = address;
        musicFile = new FileInputStream(address);
        repeat = false;
        paused = false;
        totalSongLength = 0;
        pauseLocation = 0;
        lastPlaytime = 0;
        mp3File = new Mp3File(address);
        musics.add(this);
        timer = new Timer();
        file = new File(address);
    }

    /**
     * returns the music title
     *
     * @return a string of music title
     */
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
        return "No Title";
//        byte[] namebytes = new byte[30];
//        for (int i = 3 ; i < 33 ;i++){
//            namebytes[i - 3] = getLastBytes()[i];
//        }
//        return new String(namebytes);
    }

    /**
     * returns the last 128 byte of music file
     *
     * @return a byte array
     */
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

    /**
     * returns the name of the artist of the music from the last 128 byte or from the tag
     * @return a String that is the name of artist
     */
    public String getArtist() {
        if (mp3File.hasId3v1Tag()) {
            ID3v1 tag = mp3File.getId3v1Tag();
            if (!tag.getArtist().equals("null")) {
                return tag.getArtist();
            } else {
                return "No Artist";
            }
        }
        return "No Artist";
//        byte[] artistbytes = new byte[30];
//        for (int i = 33; i < 63; i++){
//            artistbytes[i - 33] = getLastBytes()[i];
//        }
//        return new String(artistbytes);
    }

    /**
     * returns the name of the album of the music from the last 128 byte or from the tag
     * @return String for the name of song
     */
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
        return "No Album";
//        byte[] albumbytes = new byte[30];
//        for (int i = 63 ;i < 93; i++){
//            albumbytes[i - 63] = getLastBytes()[i];
//        }
//        return new String(albumbytes);
    }

    /**
     * sets an album for Downloaded musics
     */
    public void setAlbum(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            tag.setAlbum("Downloaded Music");
            try {
                mp3File.save(this.getTitle());
            } catch (NotSupportedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * returns AlbumArt of music
     * @return image that is the AlbumArt
     * @throws IOException
     */
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
        return image = ImageIO.read(getClass().getResource("default_song.jpg"));
    }

    /**
     *
     * @return ArrayList of musics
     */
    public static ArrayList<Library> getMusics() {
        return musics;
    }

    /**
     *
     * @return String that is the address of music
     */
    public String getAddress() {
        return address;
    }

    /**
     * has a thread that plays the music
     * PlayButtonListener
     * @throws IOException
     * @throws JavaLayerException
     */
    public void play() throws IOException, JavaLayerException {
        musicFile = new FileInputStream(address);
        totalSongLength = musicFile.available();
        player = new Player(musicFile);
        timer = new Timer();
        lastPlaytime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new estimatedTime(), 0, 1000);
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
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isMute) {
            Music.playingMusic.getPlayer().setVol(-60.0f);
        } else {
            player.setVol(volume);
        }
    }

    /**
     * stops the music
     * StopButtonListener
     */
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

    /**
     * resume a paused music
     * playButtonListener
     * @throws IOException
     * @throws JavaLayerException
     */
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

    /**
     * pauses a playing music
     * PlayMusicListener
     */
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

    /**
     * plays the previous music in the list
     * previousButtonListener
     */
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

    /**
     * plays the next music in the list
     * nextButtonListener
     */
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

    /**
     *
     * @return long that is the time of the music
     */
    public long getDuration() {
        return mp3File.getLengthInSeconds();
    }

    /**
     *
     * @return boolean to show if the music is paused or not
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     *
     * @param paused a boolean to show if the music is paused or not
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     *
     * @return a boolean to show if the music is favourite or not
     */
    public boolean isFavorite() {
        return isFavorite;
    }

    /**
     *
     * @param favorite to show if the music is favourite or not
     */
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    /**
     *
     * @return int that is the estimated time of music
     */
    public int getEstimatedTime() {
        return estimatedTime;
    }

    /**
     *
     * @return string of object
     */
    @Override
    public String toString() {
        return "Title: " + this.getTitle() + "\nArtist: " + this.getArtist() + "\nAlbum: " + this.getAlbum();
    }

    /**
     * inner class to increase the estimated time
     */
    class estimatedTime extends TimerTask {
        @Override
        public void run() {
            estimatedTime++;
        }
    }

    /**
     *
     * @return Player of music
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return long that is the total length of music
     */
    public long getTotalSongLength() {
        return totalSongLength;
    }

    /**
     *
     * @param pauseLocation where the music is paused
     */
    public void setPauseLocation(long pauseLocation) {
        this.pauseLocation = pauseLocation;
    }

    /**
     *
     * @param estimatedTime sets the estimated time
     */
    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    /**
     *
     * @return long that is the estimated time
     */
    public long getLastPlaytime() {
        return lastPlaytime;
    }
}
