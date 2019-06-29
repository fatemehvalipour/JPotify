package Graphic.Components;

import Graphic.Containers.BorderPanel;
import Graphic.Listeners.volumeChangeListener;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.*;

/**
 * JSlider for changing voice
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class VoiceJSlide extends JSlider {
    /**
     * constructor of changing JSlider
     * @param min
     * @param max
     */
    public VoiceJSlide(int min, int max) {
        super(min, max);
        setPaintTrack(true);
        setPaintTicks(false);
        setPaintLabels(false);
        setMajorTickSpacing(200);
        setBackground(Color.pink);
        setMinorTickSpacing(5);
        setBackground(Color.black);
        setValue(max);
        addChangeListener(new volumeChangeListener());
        setPreferredSize(new Dimension(150,120));
    }

}
