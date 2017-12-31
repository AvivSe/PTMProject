package server;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CacheManager {
    public String load(String problem);
    public void save(String problem,String solution) throws IOException;
}
