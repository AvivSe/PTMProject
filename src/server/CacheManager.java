package server;

import level.Level;

import java.io.IOException;

public interface CacheManager {
    String load(String problem) throws IOException;
    void save(String problem, String solution) throws IOException;
}
