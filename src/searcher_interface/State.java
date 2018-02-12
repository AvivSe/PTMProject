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
        this.cost = Double.POSITIVE_INFINITY;
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


        /* Possibble states test: */
//        State<PgLevel> state = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L \n" +
//                " -LL\n" +
//                "-F |\n" +
//                "FF-J\n" +
//                " g -"));
//        System.out.println("You ask: ");
//        System.out.println(state.getState());
//        System.out.println("his childs: ");
//        PgSearchable searchable = new PgSearchable(state.getState());
//        System.out.println(searchable.getPossibleStates(state));

        /* Equals test: */
        State<PgLevel> state = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L"));
        State<PgLevel> state2 = new State<PgLevel>(PgLevel.LevelBuilder.build("s|L"));
        State<PgLevel> state3 = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L"));
        State<PgLevel> state4 = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L"));
        state4.setCost(5);
        State<PgLevel> state5 = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L"));
        state5.setCameFrom(state4);
        State<PgLevel> state6 = new State<PgLevel>(PgLevel.LevelBuilder.build("s-L"));
        state6.setCameFrom(state4);

        System.out.println(state4.getCost() + "/" + state.getCost());
        System.out.println("1. state equals state2? all (false): " + (state.equals(state2)));
        System.out.println("2. state equals state3? chars (true): "+ (state.equals(state3)));
        System.out.println("3. state equals state4? cost (false): "+ (state.equals(state4)));
        System.out.println("4. state equals state5? camefrom (false): "+ (state.equals(state5)));
        System.out.println("5. state5 equals state6? camefrom (true): "+ (state6.equals(state5)));

        ArrayList<State> stateList = new ArrayList<>();
        stateList.add(state);
        System.out.println("6. statelist contains? (true): "+stateList.contains(state3));


    }


}