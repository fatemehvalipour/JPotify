package Data;

import java.util.ArrayList;

public class PlayList {
    private String name;
    private ArrayList<Music> playListMusics;
    private static ArrayList<PlayList> playLists = new ArrayList<>();
    private boolean removable;
    private boolean nameChange;

    public PlayList(String name, boolean removable , boolean nameChange) {
        this.name = name;
        this.removable = removable;
        this.nameChange = nameChange;
        playListMusics = new ArrayList<>();
    }

    public void addMusic(Music music){
        playListMusics.add(music);
    }

    public void removeMusic(Music music){
        playListMusics.remove(music);
    }

    public void removePlayList(PlayList playList){
        if (removable){
            playLists.remove(playList);

        }
    }

    private void setName(String name) {
        this.name = name;
    }

    public void renamePlayList(PlayList playList, String newName){
        if (nameChange){
            playList.setName(newName);
        }
    }


}
