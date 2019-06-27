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
            fileWriter = new FileWriter("playlists.txt");
            for (Library playlist : PlayList.getPlayLists()){
                fileWriter.write(((PlayList)playlist).getPlayListName() + "\n");
            }
            fileWriter.close();
            for (Library playlist : PlayList.getPlayLists()){
                fileWriter = new FileWriter(((PlayList)playlist).getPlayListName() + ".txt");
                for (Library music : ((PlayList)playlist).getPlayListMusics()){
                    fileWriter.write(((Music)music).getTitle() + "\n");
                }
                fileWriter.close();
            }
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
            scanner = new Scanner(new File("playlists.txt"));
            while (scanner.hasNext()){
                String playlistName = scanner.nextLine();
                if (!playlistName.equals("Favourite Songs") && !playlistName.equals("Shared Songs")){
                    new PlayList(playlistName, false, false);
                }
            }
            scanner.close();
            for (Library playlist : PlayList.getPlayLists()){
                scanner = new Scanner(new File((((PlayList)playlist).getPlayListName()) + ".txt"));
                while (scanner.hasNext()){
                    String musicName = scanner.nextLine();
                    for (Library music : Music.getMusics()){
                        if ((musicName).equals(((Music)music).getTitle())){
                            if (((PlayList)playlist).getPlayListName().equals("Favourite Songs")){
                                ((Music)music).setFavorite(true);
                            }
                            ((PlayList)playlist).addMusic((Music) music);
                        }
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("There is no file with that name");
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
    }
}
