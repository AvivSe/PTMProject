package pipe_game_server;

import game_server.Solution;
import game_server.State;

import java.util.ArrayList;

public class PgSolution extends ArrayList<String> implements Solution {

    public char getChar(int i, int j) {
        return this.get(i).charAt(j);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < size(); i++) {
            stringBuilder.append(get(i));
            if(i != size()-1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<String> getSolution() {
        return this;
    }

    @Override
    public void append(State item) {

    }



}
