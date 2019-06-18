package Data;

import java.util.ArrayList;

public class playList {
    private String name;
    private ArrayList<Music> playListMusics;
    private static ArrayList<playList> playLists = new ArrayList<>();
    private boolean removable;
    private boolean nameChange;

    public playList(String name,boolean removable , boolean nameChange) {
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

    public void removePlayList(playList playList){
        if (removable){
            playLists.remove(playList);

        }
    }

    private void setName(String name) {
        this.name = name;
    }

    public void renamePlayList(playList playList, String newName){
        if (nameChange){
            playList.setName(newName);
        }
    }


}
