package adminstrator;

import server.MyClientHandler;
import server.MyServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MyAdministrator {
    private MyServer remote;
    private MyClientHandler clientHandler;
    public MyAdministrator(MyServer remote, MyClientHandler clientHandler) {
        this.remote = remote;
        this.clientHandler = clientHandler;
    }

    public void gui() {
        ControlPanel cp = new ControlPanel();

        ActionListener stopAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!remote.stop) {
                        remote.stop();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        ActionListener startAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    remote.start(clientHandler);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
}