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
        String normalizedRequest = normalize(request.toString());
        String solution = this.cacheManager.load(normalizedRequest);

        if(solution == null) {
            solution = solver.solve(normalizedRequest);
            cacheManager.save(normalizedRequest,solution);
        }

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


        for(int i=0;i<req.length-1;i++){
            char[] reqChars = req[i].toCharArray();
            char[] solChars = sol[i].toCharArray();

                for(int j=0;j<reqChars.length;j++) {
                    Pipe left = new pipe_L('L');
                    Pipe right = new pipe_L('L');

                    if (reqChars[j] == 'L' || reqChars[j] == 'J' || reqChars[j] == 'F' || reqChars[j] == '7') {
                        left = new pipe_L(reqChars[j]);
                        right = new pipe_L(solChars[j]);
                    }
                    else if (reqChars[j] == '-' || reqChars[j] == '|') {
                    left = new pipe_I(reqChars[j]);
                    right = new pipe_I(solChars[j]);
                    }
                    res.append(i).append(",").append(j).append(",").append(left.rotate(right)+"\n");
                }
        }
        res.append("done");
        System.out.println(res);
        return res.toString();
    }
}