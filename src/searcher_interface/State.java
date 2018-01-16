package searcher_interface;

import pipe_game_server.PgLevel;

import java.util.Iterator;
import java.util.Objects;

public class State<T>  {

    private T state;
    private State<T> cameFrom;
    private double cost;

    public State(T state, State<T> cameFrom, double cost) {
        this.state = state;
        this.cost = cost;

    }

    public State(T state) {
        this.state = state;
        this.cameFrom = null;
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean hasCameFrom() { return this.cameFrom != null; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state1 = (State<?>) o;
        return Double.compare(state1.cost, cost) == 0 &&
                Objects.equals(state, state1.state) &&
                Objects.equals(cameFrom, state1.cameFrom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(state, cameFrom, cost);
    }

    @Override
    public String toString() {
        return this.state.toString();
    }

    public static void main(String[] args) {
        State<PgLevel> state = new State<PgLevel>(PgLevel.LevelBuilder.build("s|J\n  -\n FL\n g "));
        Solution<PgLevel> sol = new Solution<>();
        sol.add(state.getState());
        sol.add(state.getState());
        System.out.println(sol);
    }


}
