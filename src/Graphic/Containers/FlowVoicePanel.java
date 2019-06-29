package Graphic.Containers;

import Graphic.Components.VoiceJSlide;
import Graphic.Listeners.MuteListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * makes a panel with FlowLayout
 * @author Korosh Roohi & Fatemeh Valipour
 * @since 2019.06.22
 * @version 1.0
 */
public class FlowVoicePanel extends JPanel {

    public FlowVoicePanel(BorderPanel borderPanel, VoiceJSlide voiceJSlide) {
        super();
        setBackground(Color.black);
        setLayout(new FlowLayout());
        JButton voiceButton = new JButton();
        voiceButton.setPreferredSize(new Dimension(55, 55));
        try {
            voiceButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("voice.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        voiceButton.setBackground(Color.black);
        voiceButton.setBorder(null);
        voiceButton.setFocusable(false);
        voiceButton.addMouseListener(new MuteListener());
        add(voiceButton);
        add(voiceJSlide);
        borderPanel.add(this, BorderLayout.EAST);

    }
}
