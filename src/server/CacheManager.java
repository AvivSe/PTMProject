package server;

import java.io.IOException;

public interface CacheManager {
    String load(String problem);
    void save(String problem, String solution) throws IOException;
}
