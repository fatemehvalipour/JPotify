package Graphic.Components;

import Graphic.Containers.BorderPanel;

import javax.swing.*;
import java.awt.*;

public class JSlide extends JSlider {
    public JSlide(BorderPanel borderPanel, int min, int max) {
        super(min, max);
        this.setPaintTrack(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMajorTickSpacing(20);
        this.setMinorTickSpacing(6);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(150,120));
        borderPanel.add(this, BorderLayout.EAST);
    }
}
