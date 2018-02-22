package game_server_interface;

public interface Solver<T> {
    public Instructions solve(T problem);
}