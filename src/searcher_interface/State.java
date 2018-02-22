package searcher_interface;


import java.util.Objects;

public class State<T> {
    private T state;
    private State<T> cameFrom;
    private double cost;


    public State(T state, State<T> cameFrom, double cost) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public State(T state) {
        this.state = state;
        this.cost = 0;
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

    public boolean hasCameFrom() {
        return this.cameFrom != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state1 = (State<?>) o;
        return Objects.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}