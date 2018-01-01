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
        /**
         *
         * append the solution into map
         */
        this.cache.put(problem,solution);

        /**
         *
         * Save whole updated map to db
         */
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(
                        new FileOutputStream("db.bin"));

        objectOutputStream.writeObject(this.cache);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    Map<String,String> cache;

    MyCacheManager() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = null;

        try {
             objectInputStream =
                    new ObjectInputStream(
                            new FileInputStream("db.bin"));
        } catch (IOException e) {
            e.getMessage();
            this.cache = new HashMap<>();
        }
        this.cache = (Map<String, String>) objectInputStream.readObject();

    }
}
