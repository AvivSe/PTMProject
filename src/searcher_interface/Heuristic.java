package searcher_interface;

public interface Heuristic<T> {
    double calcHeuristic(State<T> state);
}
