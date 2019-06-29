package Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * handles making Playlist or add or delete music of playlist
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class PlayList extends Library implements Serializable {
    private ArrayList<Library> playListMusics;
    private static ArrayList<Library> playLists = new ArrayList<>();
    public static PlayList selectedPlayList = null;
    private boolean removable;
    private boolean nameChange;

    /**
     *
     * @param name
     * @param removable
     * @param nameChange
     */
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

    /**
     * adds music to playlist
     * @param music
     * @throws IOException
     */
    public void addMusic(Music music) throws IOException {
        playListMusics.add(music);
        if (playListMusics.indexOf(music) == 0 && !this.getPlayListName().equals("Favourite Songs") && !this.getPlayListName().equals("Shared Songs")){
            image = music.getAlbumArt();
        }
    }

    /**
     * removes a music in playlist
     * @param music
     */
    public void removeMusic(Music music){
        playListMusics.remove(music);
    }

    /**
     * removes a playlist
     * @param playList
     */
    public void removePlayList(PlayList playList){
        if (removable){
            playLists.remove(playList);

        }
    }

    /**
     * sets a name for playlist if it is nameChangeable
     * @param name
     */
    private void setPlayListName(String name) {
        this.name = name;
    }

    /**
     *
     * @return name of playlist
     */
    public String getPlayListName(){
        return name;
    }

    /**
     * renames the playlist if possible
     * @param playList
     * @param newName
     */
    public void renamePlayList(PlayList playList, String newName){
        if (nameChange){
            playList.setPlayListName(newName);
        }
    }

    /**
     *
     * @return Arraylist of musics
     */
    public static ArrayList<Library> getPlayLists() {
        return playLists;
    }

    /**
     *
     * @return Arrarylist of musics in the playlist
     */
    public ArrayList<Library> getPlayListMusics() {
        return playListMusics;
    }

    /**
     *
     * @return image that is the AlbumArt
     * @throws IOException
     */
    @Override
    public Image getAlbumArt() throws IOException {
        return image;
    }

    /**
     *
     * @return boolean to show that it is removable or not
     */
    public boolean isRemovable() {
        return removable;
    }

    /**
     *
     * @return boolean to show if it is possible to change or not
     */
    public boolean isNameChange() {
        return nameChange;
    }
}
