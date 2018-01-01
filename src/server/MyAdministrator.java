package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyAdministrator {
    private MyServer remote;
    MyAdministrator(MyServer remote) {
        this.remote = remote;
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
                    remote.start(new MyClientHandler(new MySolver(),new MyCacheManager()));
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        };

        cp.start.addActionListener(startAction);
        cp.stop.addActionListener(stopAction);

    }
}