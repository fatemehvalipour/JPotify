package Graphic.Containers;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * make a panel with BorderLayout
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
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
