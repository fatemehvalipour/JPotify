package Graphic.Components;

import Graphic.Containers.BorderPanel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
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

//    public void setGain(float ctrl)
//    {
//        try {
//            Mixer.Info[] infos = AudioSystem.getMixerInfo();
//            for (Mixer.Info info: infos)
//            {
//                Mixer mixer = AudioSystem.getMixer(info);
//                if (mixer.isLineSupported(Port.Info.SPEAKER))
//                {
//                    Port port = (Port)mixer.getLine(Port.Info.SPEAKER);
//                    port.open();
//                    if (port.isControlSupported(FloatControl.Type.VOLUME))
//                    {
//                        FloatControl volume =  (FloatControl)port.getControl(FloatControl.Type.VOLUME);
//                        System.out.println("max" + volume.getMaximum());
//                        System.out.println("min" + volume.getMinimum());
//                        volume.setValue((float) (Math.log(ctrl)/ Math.log(10.0) * 20.0));
//                    }
//                    port.close();
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Erro\n"+e);
//        }
//    }
}
