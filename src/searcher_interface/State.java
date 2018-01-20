package searcher_interface;

import pipe_game_server.PgLevel;
import pipe_game_server.PgSearchable;

import java.util.ArrayList;
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
        return this.getState().hashCode() == ((State<T>)o).getState().hashCode();
    }

    @Override
    public int hashCode() {

        return Objects.hash(state.hashCode());
    }

    @Override
    public String toString() {
        return this.state.toString();
    }

    public static void main(String[] args) {
        State<PgLevel> state = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L \n" +
                " -LL\n" +
                "-F |\n" +
                "FF-J\n" +
                " g -"));
        System.out.println("You ask: ");
        System.out.println(state.getState());
        System.out.println("his childs: ");
        PgSearchable searchable = new PgSearchable(state.getState());
        System.out.println(searchable.getPossibleStates(state));


    }


}
