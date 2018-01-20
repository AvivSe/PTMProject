package pipe_game_server;

import game_server_interface.CacheManager;

import java.io.*;
import java.util.Scanner;

public class PgCacheManager implements CacheManager {
    @Override
    public PgDirections load(String request) throws IOException {
        try {
            Scanner in = new Scanner(new FileInputStream(path + Integer.toString(request.hashCode())));
            PgDirections directions = new PgDirections();

            while(in.hasNextLine()){
                directions.add(in.nextLine());
            }

            return directions;
            } catch (FileNotFoundException e) {
            System.out.println("Cache said: I don't have it :(\n");
        }
        return null;
    }

    @Override
    public void save(String problem, String solution) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream(path + Integer.toString(problem.hashCode()));
        fileOutputStream.write(solution.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private String path;
    public PgCacheManager() {
        path = "./db/";
        new File(path).mkdirs();
    }
    // TODO: Dont load whole solutions to memory. keep it on files and load specific solution when asked. in addition, it better save eace solution in sperated file.
    // TODO: save on HDD, but create hash map for session
}
