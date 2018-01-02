package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyCacheManager implements CacheManager {
    @Override
    public String load(String problem) {
       if (this.cache.containsKey(problem))
           return this.cache.get(problem);
       return null;
    }

    @Override
    public void save(String problem, String solution) throws IOException {
        // Append the solution into map
        this.cache.put(problem,solution);

        // Save whole updated map to db
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(
                        new FileOutputStream("db.bin"));

        objectOutputStream.writeObject(this.cache);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    private Map<String,String> cache;

    // TODO: Dont load whole solutions to memory. keep it on files and load specific solution when asked. in addition, it better save eace solution in sperated file.
    // TODO: save on HDD, but create hash map for session

    MyCacheManager() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream;

        try {
             objectInputStream =
                    new ObjectInputStream(
                            new FileInputStream("db.bin"));
            this.cache = (Map<String, String>) objectInputStream.readObject();
        } catch (IOException e) {
            e.getMessage();
            this.cache = new HashMap<>();
        }

    }
}
