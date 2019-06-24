package Data;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PlayList extends Library{
    private ArrayList<Music> playListMusics;
    private static ArrayList<PlayList> playLists = new ArrayList<>();
    private boolean removable;
    private boolean nameChange;

    public PlayList(String name, boolean removable , boolean nameChange) {
        this.name = name;
        this.removable = removable;
        this.nameChange = nameChange;
        playListMusics = new ArrayList<>();
        playLists.add(this);
    }

    public void addMusic(Music music) throws IOException {
        playListMusics.add(music);
        if (playListMusics.indexOf(music) == 0){
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

    public static ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    @Override
    public Image getAlbumArt() throws IOException {
        return image;
    }
}
