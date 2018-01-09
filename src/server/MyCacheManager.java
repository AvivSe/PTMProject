package server;

import java.io.*;
import java.util.Scanner;

public class MyCacheManager implements CacheManager {
    @Override
    public String load(String problem) throws IOException {
        try {
            Scanner in = new Scanner(new FileInputStream(path + Integer.toString(problem.hashCode())));
            StringBuilder solution = new StringBuilder();

            while(!solution.toString().contains("done")){
                solution.append(in.nextLine()).append("\n");
            }
            return solution.toString();
        } catch (FileNotFoundException e) {
            System.out.println("..Not in cash..");
        }
        return null;
    }

    @Override
    public void save(String problem, String solution) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream(path +Integer.toString(problem.hashCode()));
        fileOutputStream.write(solution.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
            }
    String path;
    public MyCacheManager() {
        path = "./db/";
        new File(path).mkdirs();
    }
    // TODO: Dont load whole solutions to memory. keep it on files and load specific solution when asked. in addition, it better save eace solution in sperated file.
    // TODO: save on HDD, but create hash map for session
}
