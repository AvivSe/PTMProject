package server;

import javax.swing.*;
import java.awt.*;

public class ControlPanel  {
    JFrame frame;
    JPanel panel;
    JButton stop;
    JButton start;

    public ControlPanel() {
        frame = new JFrame("Control Panel");
        frame.setVisible(true);
        frame.setSize(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        stop = new JButton("Stop");
        start = new JButton("Start");

        panel.add(start);
        panel.add(stop);

        frame.add(panel);

    }
}
