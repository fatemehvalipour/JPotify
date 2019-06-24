package Data;

import java.io.*;

public class Backup {
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    public static void save(){
        try {
            objectOutputStream = new ObjectOutputStream(new DataOutputStream(new FileOutputStream("save.sv")));
            objectOutputStream.writeObject(Music.getMusics());
            objectOutputStream.writeObject(Album.getAlbums());
            objectOutputStream.writeObject(PlayList.getPlayLists());
        } catch (IOException e) {
            System.out.println("can't save the files");
        }
    }

    public static void load(){

    }
}
