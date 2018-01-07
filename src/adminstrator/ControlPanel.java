package adminstrator;

import javax.swing.*;
import java.awt.*;

public class ControlPanel  {
    JFrame frame;
    JPanel panel;
    JButton stop;
    JButton start;
    JButton exit;
    JButton clean;


    public ControlPanel() {
        frame = new JFrame("Control Panel");
        frame.setVisible(true);
        frame.setSize(300,300);
        frame.setLocation(250,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        stop = new JButton("Stop");
        start = new JButton("Start");
        exit = new JButton("Exit");
        clean = new JButton("Clean Cache");

        panel.add(start);
        panel.add(stop);
        panel.add(clean);
        panel.add(exit);

        frame.add(panel);

    }
}