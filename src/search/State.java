package search;

import java.util.Arrays;
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

}
