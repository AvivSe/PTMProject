package test.oldTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.Scanner;

public class ClientConnectTest {

    public static void main(String[] args) throws IOException {
        Socket s= new Socket("127.0.0.1",6400);
        Scanner scanner = new Scanner(System.in);
        PrintWriter out=new PrintWriter(s.getOutputStream());
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));

        System.out.println("What is your weight?");
        out.println(scanner.nextInt());
        out.flush();

        System.out.println("Press 1 to submit a level: ");
        if(scanner.hasNext()) {
            out.println("s|g");
            out.println("done");
            out.flush();
        }

        in.close();
        out.close();
        s.close();
    }
}
