package Data;

import java.io.*;
import java.util.ArrayList;
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
        try {
            objectInputStream = new ObjectInputStream(new DataInputStream(new FileInputStream("save.sv")));
            Music.setMusics((ArrayList<Library>) objectInputStream.readObject());
            Album.setAlbums((ArrayList<Library>) objectInputStream.readObject());
            PlayList.setPlayLists((ArrayList<Library>) objectInputStream.readObject());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
