package network;

import Data.Library;
import Data.Music;
import Data.PlayList;
import Data.User;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Friend {
    private String name;
    public static Friend selectedFriend = null;
    private static ArrayList<Friend> friends = new ArrayList<>();
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ArrayList<String> musics;
    private Thread listeningThread;

    public Friend(Socket socket) {
        friends.add(this);
        try {
            objectOutputStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
            objectInputStream = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
            objectOutputStream.writeObject(User.getUserName());
            name = (String) objectInputStream.readObject();
            objectOutputStream.writeObject(setMusic());
            musics = (ArrayList<String>) objectInputStream.readObject();
            Library.getGraphic().showFriendActivity();
        } catch (IOException e) {
            System.out.println("Can't create these streams");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        listeningThread = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Object object = objectInputStream.readObject();
                        if (object instanceof String){
                            upload((String) object);
                        } else {
                            musics = (ArrayList<String>)object;
                            Library.getGraphic().showFriendActivity();
                        }
                    } catch (IOException e) {
                        System.out.println("Can't receive any file at this moment");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        listeningThread.start();
    }

    public void upload(String musicName) {
        File musicFile = null;
        byte[] sendingMusic = null;
        for (Library music : Music.getMusics()){
            if (musicName.equals(((Music)music).getTitle())){
                musicFile = new File(((Music)music).getAddress());
                break;
            }
        }
        sendingMusic = new byte[(int)musicFile.length()];
        try {
            FileInputStream fis = new FileInputStream(musicFile);
            fis.read(sendingMusic);
            System.out.println("Sending file...");
            objectOutputStream.writeInt(sendingMusic.length);
            objectOutputStream.writeObject(sendingMusic);
            System.out.println("sent");
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void download(String musicName){
        listeningThread.stop();
        byte[] downloadingMusic = null;
        try {
            objectOutputStream.writeObject(musicName);
            System.out.println("receiving... ");
//            System.out.println("Press any key when downloaded...");
//            Scanner scanner = new Scanner(System.in);
//            scanner.next();
            int size = objectInputStream.readInt();
            downloadingMusic = new byte[size];
            System.out.println(size);
            objectInputStream.read(downloadingMusic, 0, size);
            System.out.println(downloadingMusic.length);
            System.out.println("received");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream savedMusic = new FileOutputStream(".\\Downloads\\" + musicName + ".mp3");
            savedMusic.write(downloadingMusic);
            savedMusic.close();
            File savedFile = new File(".\\Downloads\\" + musicName + ".mp3");
            new Music(savedFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
        listeningThread.resume();
    }

    public ArrayList<String> setMusic(){
        ArrayList<String> sharedMusics = new ArrayList<>();
        for (Library music : ((PlayList)PlayList.getPlayLists().get(1)).getPlayListMusics()){
            sharedMusics.add(((Music)music).getTitle());
        }
        return sharedMusics;
    }

    public void refresh(){
        try {
            objectOutputStream.flush();
            objectOutputStream.writeObject(setMusic());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMusics() {
        return musics;
    }

    public static ArrayList<Friend> getFriends() {
        return friends;
    }
}
