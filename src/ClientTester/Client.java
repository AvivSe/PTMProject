package ClientTester;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws  IOException {
        Socket socket = new Socket("127.0.0.1", 4200);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner in = new Scanner(System.in);

        System.out.print("cmd: ");
        printWriter.println(in.next());

        System.out.println("Server said:" + bufferedReader.readLine());


    }

}
