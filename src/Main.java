import Data.Library;
import Data.Music;
import Data.PlayList;
import Graphic.Graphic;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.sound.sampled.FloatControl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        PlayList favoritePlaylist = new PlayList("Favorite", false, false);
        PlayList sharedPlaylist = new PlayList("Shared", false, false);
        Music.playingMusic = new Music("C:\\Users\\Korosh\\Downloads\\Music\\Roozbeh Bemani - Yani Tamoom.mp3");
        Graphic graphic = new Graphic();
        Library.setGraphic(graphic);
        graphic.setAlbumArt(Music.playingMusic);
        graphic.setNameOfSong(Music.playingMusic);
    }
}
