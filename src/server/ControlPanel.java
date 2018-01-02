package server;

import javax.swing.*;
import java.awt.*;

public class ControlPanel  {
    JFrame frame;
    JPanel panel;
    JButton stop;
    JButton start;
    JButton exit;

    public ControlPanel() {
        frame = new JFrame("Control Panel");
        frame.setVisible(true);
        frame.setSize(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        stop = new JButton("Stop");
        start = new JButton("Start");
        exit = new JButton("Exit");

        panel.add(start);
        panel.add(stop);
        panel.add(exit);

        frame.add(panel);

    }
}