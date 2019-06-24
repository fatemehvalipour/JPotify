package Data;

import java.io.*;
import java.util.ArrayList;

public class Backup {
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    public static void save(){

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
