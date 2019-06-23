import Data.Album;
import Data.Library;
import Data.Music;
import Graphic.Graphic;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {
        Music music = new Music("C:\\Users\\Korosh\\Downloads\\Music\\Roozbeh Bemani - Yani Tamoom.mp3");
        Graphic graphic = new Graphic(music);
        Library.setGraphic(graphic);
        graphic.setAlbumArt(music);
    }
}
