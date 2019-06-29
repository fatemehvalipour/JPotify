package Data;


import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class for classify an Album
 * That have a Arraylist for musics
 *
 * @author Fatemeh valipour
 * @since
 * @version 1.0
 */
public class Album extends Library implements Serializable {
    private ArrayList<Library> musics;
    private static ArrayList<Library> albums  = new ArrayList<>();

    /**
     * constructor for Album class
     *
     * @param name name of album
     */
    public Album(String name) {
        super.name = name;
        musics = new ArrayList<>();
        albums.add(this);
    }

    /**
     * add music to this album
     *
     * @param music add this music to album
     */
    private void addMusic(Music music){
        musics.add(music);
    }

    /**
     * a method that returns album name
     *
     * @return a string
     */
    public String getAlbumName() {
        return name;
    }

    /**
     * set an image as AlbumArt
     *
     * @param image set this param as AlbumArt
     */
    private void setImage(Image image) {
        this.image = image;
    }

    /**
     * this method checks if an album doesn't exist create an album
     *
     * @param music check this music
     * @throws IOException for input and output problems
     */
    public static void addMusicToAlbum(Music music) throws IOException {
        for (Library album : albums){
            if (((Album)album).getAlbumName().equals(music.getAlbum())){
                ((Album)album).addMusic(music);
                return;
            }
        }
        if (music.getAlbum() == null){
            System.out.println("isNull");
        }
        Album album = new Album(music.getAlbum());
        album.addMusic(music);
        album.setImage(music.getAlbumArt());
    }

    /**
     * returns the album list
     *
     * @return all albums
     */
    public static ArrayList<Library> getAlbums() {
        return albums;
    }

    /**
     * return musics of this album
     *
     * @return an arraylist of musics
     */
    public ArrayList<Library> getMusics() {
        return musics;
    }

    /**
     * return an image of album music
     *
     * @return an image of albums music
     * @throws IOException for sometime that can't read the image from music
     */
    @Override
    public Image getAlbumArt() throws IOException {
        return image;
    }

    public static void setAlbums(ArrayList<Library> albums) {
        Album.albums = albums;
    }
}
