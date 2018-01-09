package search;

public class State<T> {
    private T state;
    private State<T> cameFrom;
    private double cost;

    public State(T state) {
        this.state = state;
    }

    public State(T state, State<T> cameFrom, double cost) {
        this.state = state;
       6
        this.cost = cost;
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

    public boolean equals(State<T> toCompare){
        return this.toString().equals(toCompare.toString());
    }
}
