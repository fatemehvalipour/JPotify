package network;

import Data.Library;
import Data.Music;
import Data.PlayList;
import Data.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Friend {
    private String name;
    private static ArrayList<Friend> friends = new ArrayList<>();
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ArrayList<String> musics;

    public Friend(Socket socket) {
        friends.add(this);
        try {
            System.out.println("avvale object ha");
            objectOutputStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
            System.out.println("vasate");
            objectInputStream = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
            System.out.println("akhare");
            objectOutputStream.writeObject(User.getUserName());
            name = (String) objectInputStream.readObject();
            objectOutputStream.writeObject(setMusic());
            musics = (ArrayList<String>) objectInputStream.readObject();
            objectOutputStream.writeObject(setMusic());
        } catch (IOException e) {
            System.out.println("Can't create these streams");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            musics = (ArrayList<String>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
