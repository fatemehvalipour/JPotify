package Data;

import Graphic.Graphic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public abstract class Library implements Serializable {
    protected Image image;
    protected String name;
    protected static Graphic graphic;

    public static void setGraphic(Graphic g) {
        graphic = g;
    }

    public static Graphic getGraphic() {
        return graphic;
    }

    public abstract Image getAlbumArt() throws IOException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
