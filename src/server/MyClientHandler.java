package server;

import java.io.*;

import level.Level;
import parts.*;

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

        StringBuilder req = new StringBuilder(in.readLine() + "\n");
        while (!req.toString().contains("done")) {
            req.append(in.readLine()).append("\n");
        }

        String tmp = req.toString();
        tmp = req.toString().substring(0,tmp.length()-5);
        Level request = Level.LevelBuilder.build(tmp);
        System.out.println("Client ask for directions to level: \n"  + tmp);
        Level normalizedRequest = normalize(request);

        String sol = this.cacheManager.load(normalizedRequest.toString());


        if(sol == null) {
            sol = solver.solve(normalizedRequest);
            cacheManager.save(normalizedRequest.toString(),sol + "\ndone");
        }

        Level solution = Level.LevelBuilder.build(sol.substring(0,sol.length()-5));

        System.out.println("Server - Soltuion is:\n "+ solution);
        out.write(directions(request ,solution));

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
    private Level normalize(Level original) {

        StringBuilder result = new StringBuilder();
        for(char item: original.toString().toCharArray()) {
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

        return Level.LevelBuilder.build(result.toString());
    }
    private String directions(Level request,Level solution) {
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < request.getNumOfRows(); i++) {
            for(int j = 0; j < request.getNumOfCol(); j++) {
                Part left = request.getObject(i,j);
                Part right =  solution.getObject(i,j);
                /**
                 * Write vector if and only both chars are not equals.
                 * Either way, both will work.
                 */
                if (left.toString() != right.toString()) {
                    res.append(i).append(",").append(j).append(",").append(((Pipe)left).rotate((Pipe)right)).append("\n");
                }

            }
        }
        res.append("done");
        System.out.println("Directions are:\n" + res.toString());

        return res.toString();
    }
}