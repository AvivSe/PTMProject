package pipeGame.model;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class PgModel extends Observable implements Model {

    public int weight(String request) {
        int result = 0;
        for(int i = 0; i < request.length(); i++) {
            char c = request.charAt(i);
            if (c == 'L' || c == 'F' || c == '7' || c == 'J')
                result += 2;
            if (c == '|' || c == '-')
                result++;
        }
        return result;
    }

    public String getIp() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("pgGame.conf"));
        return scanner.nextLine().split(":")[0];
    }

    public int getPort() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("pgGame.conf"));
        return Integer.valueOf(scanner.nextLine().split(":")[1]);
    }

    public ArrayList<String> solve(String request) throws IOException {
        Socket s = new Socket(getIp(), getPort());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());

        out.println(weight(request));
        out.flush();

        out.println(request += "done");
        out.flush();

        String answer = in.readLine();
        ArrayList<String> solution = new ArrayList<>();

        while(!answer.equals("done")) {
            //System.out.println(answer);
            solution.add(answer);
            answer = in.readLine();
        }

        in.close();
        out.close();
        s.close();

        return solution;
    }


}
