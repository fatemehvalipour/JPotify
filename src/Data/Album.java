package Data;

import java.util.ArrayList;

public class Album {
    private String name;
    private byte[] image;
    private ArrayList<Music> musics;
    private static ArrayList<Album> albums  = new ArrayList<>();

    public Album(String name) {
        this.name = name;
        musics = new ArrayList<>();
        albums.add(this);
    }

    private void addMusic(Music music){
        musics.add(music);
    }

    public String getName() {
        return name;
    }

    private void setImage(byte[] image) {
        this.image = image;
    }

    public static void addMusicToAlbum(Music music){
        for (Album album : albums){
            if (album.getName().equals(music.getAlbum())){
                album.addMusic(music);
                return;
            }
        }
        Album album = new Album("" + music.getAlbum());
        album.addMusic(music);
        album.setImage(music.getAlbumArt());
    }

    public static ArrayList<Album> getAlbums() {
        return albums;
    }
}
