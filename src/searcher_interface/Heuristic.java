package searcher_interface;

public interface Heuristic<T> {
    double indication();
    boolean isLeftBetter(double left, double right);
    double calcHeuristic(State<T> state);
}
