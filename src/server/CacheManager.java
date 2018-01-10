package server;

import search.Solution;

import java.io.IOException;

public interface CacheManager {
    Solution load(int hashName) throws IOException;
    void save(String problem, String solution) throws IOException;
}
