package Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayList extends Library implements Serializable {
    private ArrayList<Library> playListMusics;
    private static ArrayList<Library> playLists = new ArrayList<>();
    public static PlayList selectedPlayList = null;
    private boolean removable;
    private boolean nameChange;

    public PlayList(String name, boolean removable , boolean nameChange) {
        this.name = name;
        try {
            if (name.equals("Favourite Songs")) {
                image = ImageIO.read(getClass().getResource("favouritePlaylistIcon.png"));
            } else if (name.equals("Shared Songs")) {
                image = ImageIO.read(getClass().getResource("sharedPlaylistIcon.png"));
            } else {
                image = ImageIO.read(getClass().getResource("defualtPlaylistIcon.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.removable = removable;
        this.nameChange = nameChange;
        playListMusics = new ArrayList<>();
        playLists.add(this);
    }

    public void addMusic(Music music) throws IOException {
        playListMusics.add(music);
        if (playListMusics.indexOf(music) == 0 && !this.getPlayListName().equals("Favourite Songs") && !this.getPlayListName().equals("Shared Songs")){
            image = music.getAlbumArt();
        }
    }

    public void removeMusic(Music music){
        playListMusics.remove(music);
    }

    public void removePlayList(PlayList playList){
        if (removable){
            playLists.remove(playList);

        }
    }

    private void setPlayListName(String name) {
        this.name = name;
    }

    public String getPlayListName(){
        return name;
    }

    public void renamePlayList(PlayList playList, String newName){
        if (nameChange){
            playList.setPlayListName(newName);
        }
    }

    public static ArrayList<Library> getPlayLists() {
        return playLists;
    }

    public ArrayList<Library> getPlayListMusics() {
        return playListMusics;
    }

    @Override
    public Image getAlbumArt() throws IOException {
        return image;
    }

    public static void setPlayLists(ArrayList<Library> playLists) {
        PlayList.playLists = playLists;
    }
}
