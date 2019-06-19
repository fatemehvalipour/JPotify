package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

public class BorderPanel extends JPanel {
    public BorderPanel(Container con) {
        super();
        setBackground(Color.black);
        setLayout(new BorderLayout());
        con.add(this);
    }

    public BorderPanel(Container con, Object index){
        super();
        setBackground(Color.black);
        setLayout(new BorderLayout());
        con.add(this, index);
    }
}
