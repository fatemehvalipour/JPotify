import Data.Backup;
import Data.Library;
import Data.Music;
import Data.PlayList;
import Graphic.Graphic;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.sound.sampled.FloatControl;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidDataException, UnsupportedTagException, InterruptedException {
        Backup.load();
        Graphic graphic = new Graphic();
        Library.setGraphic(graphic);
    }
}
