package network;

import Data.*;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class Friend {
    private String name;
    private Socket socket;
    public static Friend selectedFriend = null;
    private static ArrayList<Friend> friends = new ArrayList<>();
    private volatile ObjectOutputStream objectOutputStream;
    private volatile ObjectInputStream objectInputStream;
    private ArrayList<String> musics;
    private Thread listeningThread;

    public Friend(Socket socket) {
        friends.add(this);
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream((socket.getOutputStream()));
            objectInputStream = new ObjectInputStream((socket.getInputStream()));
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
                    try {
                        objectOutputStream.flush();
                    } catch (IOException e) {
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
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        sendingMusic = new byte[(int)musicFile.length()];
        System.out.println("Sending...");
        try {
           dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
           dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.flush();
            FileInputStream fis = new FileInputStream(musicFile);
            int counter;
            int sum = 0;
            while ((counter = fis.read(sendingMusic)) > 0) {
                System.out.println(counter);
                sum += counter;
                dataOutputStream.write(sendingMusic, 0, counter);
                dataOutputStream.flush();
            }
            System.out.println("bad as write");
            System.out.println(sum);
            dataOutputStream.flush();
            System.out.println("Sent");
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void download(String musicName){
        listeningThread.stop();
        int fileSize = 16*1024;
        byte[] downloadingMusic = new byte[fileSize];
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            objectOutputStream.writeObject(musicName);
            objectOutputStream.flush();
            System.out.println("receiving... ");
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            FileOutputStream savedMusic = new FileOutputStream(".\\Downloads\\" + musicName + ".mp3");
            int tempCountByte;
            int size = 0;
            int count = 0;
            while(true){
                Thread.sleep(10);
                if (dataInputStream.available() == 0 && count > 200){
                    break;
                }
                tempCountByte = dataInputStream.read(downloadingMusic);
                savedMusic.write(downloadingMusic, 0, tempCountByte);
                savedMusic.flush();
                count++;
            }
            System.out.println("received");
            savedMusic.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            File savedFile = new File(".\\Downloads\\" + musicName + ".mp3");
            Music music = new Music(savedFile.getAbsolutePath());
            music.setAlbum();
            Album.addMusicToAlbum(music);
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
            sharedMusics.add("" + (System.currentTimeMillis() - ((Music)music).getLastPlaytime())+ "@@@@" + ((Music)music).getTitle());
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
