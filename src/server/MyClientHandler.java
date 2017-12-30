package server;

public class MyClientHandler implements ClientHandler{
    @Override
    public String handle(String request, Response response) {
        return response.manipulate(request);
    }
}
