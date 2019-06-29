package network;

import Data.*;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * classifies the friends
 *
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class Friend {
    private String name;
    private Socket socket;
    private static boolean transfering = false;
    public static Friend selectedFriend = null;
    private static ArrayList<Friend> friends = new ArrayList<>();
    private volatile ObjectOutputStream objectOutputStream;
    private volatile ObjectInputStream objectInputStream;
    private ArrayList<String> musics;
    private Thread listeningThread;

    /**
     * initializes a friend
     * @param socket
     */
    public Friend(Socket socket) {
        friends.add(this);
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream((socket.getOutputStream()));
            objectInputStream = new ObjectInputStream((socket.getInputStream()));
            objectOutputStream.writeObject(User.getUsername());
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
                    if (transfering){
                        continue;
                    }
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

    /**
     * uploads a song
     * @param musicName
     */
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

    /**
     * downloads a song
     * @param musicName
     */
    public void download(String musicName){
        int fileSize = 16*1024;
        byte[] downloadingMusic = new byte[fileSize];
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            objectOutputStream.writeObject(musicName);
            transfering = true;
            objectOutputStream.flush();
//            listeningThread.suspend();
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
        transfering = false;
//        listeningThread.resume();
    }

    /**
     * show the information of the music sent
     * @return
     */
    public ArrayList<String> setMusic(){
        ArrayList<String> sharedMusics = new ArrayList<>();
        for (Library music : ((PlayList)PlayList.getPlayLists().get(1)).getPlayListMusics()){
            if (((Music)music).getLastPlaytime() == 0){
                sharedMusics.add("0@@@@" + ((Music) music).getTitle());
            } else {
                sharedMusics.add("" + (System.currentTimeMillis() - ((Music) music).getLastPlaytime()) + "@@@@" + ((Music) music).getTitle());
            }
        }
        return sharedMusics;
    }

    /**
     * updates the information
     */
    public void refresh(){
        try {
            objectOutputStream.flush();
            objectOutputStream.writeObject(setMusic());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return name of friend
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return musics of friend
     */
    public ArrayList<String> getMusics() {
        return musics;
    }

    /**
     *
     * @return the list of friends
     */
    public static ArrayList<Friend> getFriends() {
        return friends;
    }
}
