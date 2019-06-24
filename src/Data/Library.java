package Data;

import Graphic.Graphic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class Library extends JPanel{
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
}
