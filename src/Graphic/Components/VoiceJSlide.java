package Graphic.Components;

import Graphic.Containers.BorderPanel;

import javax.swing.*;
import java.awt.*;

public class VoiceJSlide extends JSlider {
    public VoiceJSlide(BorderPanel borderPanel, int min, int max) {
        super(min, max);
        setPaintTrack(true);
        setPaintTicks(true);
        setPaintLabels(true);
        setMajorTickSpacing(20);
        setMinorTickSpacing(6);
        setBackground(Color.black);
        setPreferredSize(new Dimension(150,120));
        borderPanel.add(this, BorderLayout.EAST);
    }
}
