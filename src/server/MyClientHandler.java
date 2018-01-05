package server;

import java.io.*;
import Parts.*;

/**
 *
 * The purpose of this kind of ClientHandler is to solve level in our Pipe-Game Project,
 * We adapt some algorithms for this goal, using our levels solver,
 * old requests (and their solutions) are stored in the cache manager.
 */
public class MyClientHandler implements ClientHandler{
    @Override
    public void handler(InputStream input, OutputStream output) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        PrintWriter out = new PrintWriter(output);

        StringBuilder request = new StringBuilder(in.readLine() + "\n");
        while (!request.toString().contains("done")) {
            request.append(in.readLine()).append("\n");
        }
        System.out.println("Client ask for directions to level: \n"  + request.toString());

        String normalizedRequest = normalize(request.toString());
        String solution = this.cacheManager.load(normalizedRequest);

        if(solution == null) {
            solution = solver.solve(normalizedRequest);
            cacheManager.save(normalizedRequest,solution);
        }
        System.out.println("Server - Soltuion is:\n "+ solution);
        out.write(directions(request.toString(),solution));

        System.out.print("Client got answer. ");

        out.flush();
        out.close();
    }

    private Solver solver;
    private CacheManager cacheManager;

    MyClientHandler(Solver solver, CacheManager cacheManager) {
        this.solver = solver;
        this.cacheManager = cacheManager;
    }
    private String normalize(String original) {
        StringBuilder result = new StringBuilder();
        for(char item: original.toCharArray()) {
            switch(item) {
                case 'F':
                case '7':
                case 'J':
                    result.append('L');
                    break;
                case '-':
                    result.append('|');
                    break;
                default:
                    result.append(item);
                    break;
            }
        }
        return result.toString();
    }
    private String directions(String request,String solution) {
        String[] req = request.split("\n");
        String[] sol = solution.split("\n");
        StringBuilder res=new StringBuilder();

        for(int i = 0; i < req.length-1; i++) { // (-1) 'cause last line is "done".
            char[] reqCharsLine = req[i].toCharArray();
            char[] solCharsLien = sol[i].toCharArray();

            for(int j = 0; j < reqCharsLine.length; j++) {
                Pipe left = null;
                Pipe right = null;

                switch(reqCharsLine[j]) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        left = new pipe_L(reqCharsLine[j]);
                        right = new pipe_L(solCharsLien[j]);
                        break;
                    case '|':
                    case '-':
                        left = new pipe_I(reqCharsLine[j]);
                        right = new pipe_I(solCharsLien[j]);
                        break;
                    case 's':
                    case 'g':
                        break;

                        default:
                            System.out.println("Unknown kind of Part. we support: {s,g,L,F,7,J,|,-}");

                }
                /**
                 * Write vector if and only both chars are not equals.
                 * Either way, both will work.
                 * (iluz gay)
                 */
                if (reqCharsLine[j] != solCharsLien[j]) {
                    res.append(i).append(",").append(j).append(",").append(left.rotate(right)).append("\n");
                }

            }
        }

        res.append("done");
        System.out.println("Directions are:\n" + res);
        return res.toString();
    }
}