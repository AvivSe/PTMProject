package game_server;

import java.util.ArrayList;

public  interface Solution<T> {
    ArrayList<State<T>> getSolution();

    void append(State<T> item);

}
