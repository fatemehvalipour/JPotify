package Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Album extends Library {
    private ArrayList<Library> musics;
    private static ArrayList<Library> albums  = new ArrayList<>();
    private JButton imageButton;
    private JLabel albumName;

    public Album(String name) {
        imageButton = null;
        super.name = name;
        albumName.setText(name);
        imageButton.setFocusable(false);
        setPreferredSize(new Dimension(200, 240));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(imageButton);
        add(albumName);
        imageButton.setBackground(Color.BLACK);
        imageButton.setPreferredSize(new Dimension(200, 200));
        albumName.setBackground(Color.BLACK);
        albumName.setPreferredSize(new Dimension(200, 10));
        musics = new ArrayList<>();
        albums.add(this);
    }

    private void addMusic(Music music){
        musics.add(music);
    }

    public String getAlbumName() {
        return name;
    }

    private void setImage(Image image) {
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

    public static void addMusicToAlbum(Music music) throws IOException {
        for (Library album : albums){
            if (((Album)album).getAlbumName().equals(music.getAlbum())){
                ((Album)album).addMusic(music);
                return;
            }
        }
        Album album = new Album("" + music.getAlbum());
        album.addMusic(music);
        album.setImage(music.getAlbumArt());
        album.getImageButton().setIcon(new ImageIcon(album.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
    }

    public static ArrayList<Library> getAlbums() {
        return albums;
    }

    public ArrayList<Library> getMusics() {
        return musics;
    }

    public JButton getImageButton() {
        return imageButton;
    }
}
