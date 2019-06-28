package Graphic.Components;

import Graphic.Containers.BorderPanel;
import Graphic.Listeners.volumeChangeListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.*;

public class VoiceJSlide extends JSlider {
    public VoiceJSlide(int min, int max) {
        super(min, max);
        setPaintTrack(true);
        setPaintTicks(false);
        setPaintLabels(false);
        setMajorTickSpacing(200);
        setMinorTickSpacing(5);
        setBackground(Color.black);
        setValue(max);
        addChangeListener(new volumeChangeListener());
        setPreferredSize(new Dimension(150,120));
    }

}
