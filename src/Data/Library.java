package Data;

import Graphic.Graphic;

import javax.swing.*;
import java.awt.*;

public class Library extends JPanel {
    protected Image image;
    protected String name;
    protected static Graphic graphic;

    public static void setGraphic(Graphic g) {
        graphic = g;
    }
}
