package Graphic.Containers;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BorderPanel extends JPanel {
    public BorderPanel(Container con) {
        super();
        setBackground(Color.black);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        con.add(this);
    }

    public BorderPanel(Container con, Object index){
        super();
        setBackground(Color.black);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        con.add(this, index);
    }
}
