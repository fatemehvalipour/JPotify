package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

public class BoxPanel extends JPanel {
    public BoxPanel(int index) {
        setLayout(new BoxLayout(this, index));
        setBackground(Color.black);
    }

}
