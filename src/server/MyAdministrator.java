package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyAdministrator {
    private MyServer remote;
    private  MyClientHandler clientHandler;
    MyAdministrator(MyServer remote, MyClientHandler clientHandler) {
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

        cp.start.addActionListener(startAction);
        cp.stop.addActionListener(stopAction);
        cp.exit.addActionListener(exitAction);

    }
}