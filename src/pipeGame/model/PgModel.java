package pipeGame.model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

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

    public ArrayList<String> solve(String request) {
//        System.out.println("Weight: " + weight(request));
//        System.out.println(request);
//
//        Socket s = new Socket(getIp(), getPort());
//        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        PrintWriter out = new PrintWriter(s.getOutputStream());
//
//        out.println(getWeight());
//        out.flush();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for(char[] line: data) {
//            for (char c : line) {
//                stringBuilder.append(c);
//            }
//            stringBuilder.append("\n");
//        }
//        stringBuilder.append("done");
//        out.println(stringBuilder.toString());
//        out.flush();
//
//        String answer = in.readLine();
//        ArrayList<String> solution = new ArrayList<>();
//
//        while(!answer.equals("done")) {
//            //System.out.println(answer);
//            solution.add(answer);
//            answer = in.readLine();
//        }
//
//        in.close();
//        out.close();
//        s.close();

        return null;
    }


}
