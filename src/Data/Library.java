package Data;

import Graphic.Graphic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * abstract class and a super class for Music and Album and Playlist
 *
 *   @author  Fatemeh Valipour & Korosh Roohi
 *   @since 2019.06.22
 *   @version 1.0
 */
public abstract class Library implements Serializable {
    protected Image image;
    protected String name;
    protected static Graphic graphic;

    /**
     *
     * @param g the main Graphic
     */
    public static void setGraphic(Graphic g) {
        graphic = g;
    }

    /**
     *
     * @return Graphic
     */
    public static Graphic getGraphic() {
        return graphic;
    }

    /**
     *
     * @return Image that is the AlbumArt of the Music
     * @throws IOException
     */
    public abstract Image getAlbumArt() throws IOException;

    /**
     *
     * @return string name of music
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
