package server_interface;

import java.io.IOException;

public interface CacheManager {
    Instructions load(String request) throws IOException;
    void save(String problem, String solution) throws IOException;
}
