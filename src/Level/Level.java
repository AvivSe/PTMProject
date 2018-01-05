package Level;

import Parts.*;

import java.util.ArrayList;

public class Level <T> {
    private ArrayList<T[]> matrix;

    public Level() {
        this.matrix = new ArrayList<>();
    }

    public Level(ArrayList<T[]> data) {
        this.matrix = data;
    }

    public void setObjectAtIndex(int x, int y, T object) {
        T[] temp = matrix.get(x);
        temp[y] = object;
        this.matrix.set(x,temp);
    }

    public T getObjectAtIndex(int x,int y) {
        return this.matrix.get(x)[y];
    }

    public void addLine(T[] line) {
        this.matrix.add(line);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for(T[] row: matrix) {
            for (T col : row) {
                result.append(col);
            }
            result.append("\n");
        }

        String resultString = result.toString();
        int len = resultString.length();

        return resultString.substring(0,len-1); // remove last \n
    }

    public static void main(String[] args) {
        Level<Part> level = new Level<Part>();

        Part[] line = {new StartPoint(), new pipe_L('7'),new pipe_I('-')};
        Part[] line2 = {new pipe_I('|'), new pipe_L('7'),new GoalPoint()};

        level.addLine(line);
        level.addLine(line2);

        System.out.println(level);
    }
}