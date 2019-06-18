package Data;

import java.util.ArrayList;

public class Album {
    private ArrayList<Music> musics;
    private static ArrayList<Album> albums  = new ArrayList<>();

    public Album() {
        musics = new ArrayList<>();
        albums.add(this);
    }

}
