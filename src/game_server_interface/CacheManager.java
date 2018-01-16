package game_server_interface;

import java.io.IOException;

public interface CacheManager {
    Directions load(String request) throws IOException;
    void save(String problem, String solution) throws IOException;
}
