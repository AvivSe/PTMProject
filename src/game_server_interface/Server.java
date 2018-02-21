package game_server_interface;



public interface Server {
    /**
     *
     * Wait for interaction with client, here we accepting clients.
     * this process take care one client at time.
     * @param clientHandler Various implements solving different kinds of problems or requests.
     */

    void start(ClientHandler clientHandler);
    void stop() ;
}