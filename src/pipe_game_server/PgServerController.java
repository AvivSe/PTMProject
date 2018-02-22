package pipe_game_server;


import pipe_game_server.PgClientHandler;
import pipe_game_server.PgServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PgServerController {
    private PgServer remote;
    private PgClientHandler clientHandler;
    public PgServerController(PgServer remote, PgClientHandler clientHandler) {
        this.remote = remote;
        this.clientHandler = clientHandler;
    }

    public void gui() {
        ControlPanel cp = new ControlPanel();

        ActionListener stopAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!remote.stop) {
                    remote.stop();
                }
            }
        };

        ActionListener startAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remote.start(clientHandler);
            }
        };

        ActionListener exitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                System.out.println("exit.");
            }
        };

        ActionListener cleanAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File directory = new File("./db/");
                // Get all files in directory
                File[] files = directory.listFiles();
                int counter = 0;
                for (File file : files) {
                    // Delete each file
                    if (!file.delete()) {
                        // Failed to delete file
                        System.out.println("Failed to delete "+file);
                    } else {
                        counter++;
                    }
                }
                System.out.println("Clean is empty now :) " + counter + " files is deleted");
            }
        };

        cp.clean.addActionListener(cleanAction);
        cp.start.addActionListener(startAction);
        cp.stop.addActionListener(stopAction);
        cp.exit.addActionListener(exitAction);

    }

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
}