package ClientTester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Client {

    public static void main(String[] args) throws  IOException {
        Socket socket = new Socket("127.0.0.1", 4200);
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("MyLevel");
            printWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMsg = bufferedReader.readLine();
            System.out.println("Server said:" + serverMsg);



        } finally {
            socket.close();
        }
    }

}
