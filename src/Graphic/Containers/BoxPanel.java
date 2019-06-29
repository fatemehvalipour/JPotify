package Graphic.Containers;

import javax.swing.*;
import java.awt.*;

/**
 * makes a panel with Box
 * @author Fatemeh Valipour & Korosh Roohi
 * @since 2019.06.22
 * @version 1.0
 */
public class BoxPanel extends JPanel {
    public BoxPanel(int index) {
        setLayout(new BoxLayout(this, index));
        setBackground(Color.black);
    }

}
