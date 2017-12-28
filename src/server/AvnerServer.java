package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;


public class AvnerServer implements Server{
    ServerSocket server;
    @Override
    public void start(int port) throws IOException {
        ;
        try {
            server=new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Running on port : " + port);
        while (true){
            MyClientHandler clientHendler = new MyClientHandler(server.accept());

            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(clientHendler.getSocket().getInputStream()));
            if(bufferedReader.readLine().equals("getSolution")) {
                PrintWriter printWriter = new PrintWriter(clientHendler.getSocket().getOutputStream());
                printWriter.println(clientHendler.RequestSolution(bufferedReader.readLine()));
                printWriter.flush();
            }
        }
    }
    @Override
    public void stop() throws IOException {
        server.close();
    }
}