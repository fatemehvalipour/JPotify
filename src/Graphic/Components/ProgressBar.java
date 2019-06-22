package Graphic.Components;

import Graphic.Containers.BorderPanel;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JProgressBar {
    public ProgressBar(BorderPanel borderPanel) {
        super();
        this.setValue(0);
        this.setStringPainted(true);
        this.setBackground(Color.black);
        borderPanel.add(this, BorderLayout.CENTER);
        //fill();
    }

    public void fill(){
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                this.setValue(i + 10);

                // delay the thread
                //Thread.sleep(1000);
                i += 20;
            }
        }
        catch (Exception e) {
        }
    }
}

