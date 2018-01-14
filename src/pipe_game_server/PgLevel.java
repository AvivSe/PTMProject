package pipe_game_server;

import parts.*;

import java.util.ArrayList;

public class PgLevel {
    private ArrayList<ArrayList<Part>> matrix;

    public ArrayList<ArrayList<Part>> getMatrix() {
        return matrix;
    }

    public PgLevel() {
        this.matrix = new ArrayList<>();
    }
    public PgLevel(ArrayList<ArrayList<Part>> data) {
        this.matrix = data;
    }
    public void setObject(int row, int column, Part part) {
        this.matrix.get(row).set(column,part);
    }
    public Part getObject(int row, int column) {
        return this.matrix.get(row).get(column);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for(ArrayList<Part> row: matrix) {
            for (Part col : row) {
                result.append(col);
            }
            result.append("\n");
        }
        String resultString = result.toString();
        int len = resultString.length();

        return resultString.substring(0,len-1); // remove last \n
    }

    public int getNumOfRows() { return this.matrix.size(); }
    public int getNumOfCol() { return this.matrix.get(0).size();}

    public static class LevelBuilder {
        public static PgLevel build(String problem) {
            PgLevel result = new PgLevel();
            String[] rows = problem.split("\n");
            for (int i = 0; i < rows.length; i++) {
                result.matrix.add(new ArrayList<>());
                char[] chars = rows[i].toCharArray();
                for (char aChar : chars) {
                    result.matrix.get(i).add(PartBuilder.build(aChar));
                }
            }
            return result;
        }
    }

}