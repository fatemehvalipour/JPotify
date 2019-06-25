package Data;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.util.Scanner;

public class Backup {
    private static Scanner scanner;
    private static FileWriter fileWriter;

    public static void save(){
        try {
            fileWriter = new FileWriter("save.txt");
            for (Library music : Music.getMusics()){
                fileWriter.write(((Music)music).getAddress() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("can't save the files");
        }
    }

    public static void load(){
        try {
            scanner = new Scanner(new File("save.txt"));
            while (scanner.hasNext()){
                String address = scanner.nextLine();
                Music music = new Music(address);
                Album.addMusicToAlbum(music);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("There is no file with that name");
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
    }
}
