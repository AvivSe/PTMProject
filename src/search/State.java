package search;

public class State<T> {
    T state;
    State<T> cameFrom;
    double cost;

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
        return this.toString()==toCompare.toString();
    }
}
