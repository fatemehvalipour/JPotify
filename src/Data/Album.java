package Data;


import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Album extends Library implements Serializable {
    private ArrayList<Library> musics;
    private static ArrayList<Library> albums  = new ArrayList<>();

    public Album(String name) {
        super.name = name;
        musics = new ArrayList<>();
        albums.add(this);
    }

    private void addMusic(Music music){
        musics.add(music);
    }

    public String getAlbumName() {
        return name;
    }

    private void setImage(Image image) {
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

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

    public static ArrayList<Library> getAlbums() {
        return albums;
    }

    public ArrayList<Library> getMusics() {
        return musics;
    }

    @Override
    public Image getAlbumArt() throws IOException {
        return image;
    }

    public static void setAlbums(ArrayList<Library> albums) {
        Album.albums = albums;
    }
}
