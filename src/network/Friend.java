package network;

import Data.Library;
import Data.Music;
import Data.PlayList;
import Data.User;

import javax.swing.*;
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
        new Thread(){
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
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void upload(String musicName){

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
