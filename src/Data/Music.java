package Data;
import com.mpatric.mp3agic.*;

import java.io.IOException;
import java.util.ArrayList;

public class Music {
    private static ArrayList<Music> musics = new ArrayList<>();
    private String address;
    private Mp3File mp3File;

    public Music(String address) throws InvalidDataException, UnsupportedTagException, IOException {
        mp3File = new Mp3File(address);
        musics.add(this);
    }

    public String getTitle(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getTitle();
        }
        return null;
    }

    public String getArtist(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getArtist();
        }
        return null;
    }

    public String getAlbum(){
        if (mp3File.hasId3v1Tag()){
            ID3v1 tag = mp3File.getId3v1Tag();
            return tag.getAlbum();
        }
        return null;
    }

    public byte[] getAlbumArt(){
        if (mp3File.hasId3v2Tag()){
            ID3v2 tag = mp3File.getId3v2Tag();
            return tag.getAlbumImage();
        }
        return null;
    }

    public static ArrayList<Music> getMusics() {
        return musics;
    }
}
