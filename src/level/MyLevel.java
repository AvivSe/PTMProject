package level;

import parts.*;
import java.util.ArrayList;

public class MyLevel {
    private ArrayList<ArrayList<Part>> matrix;

    public MyLevel() {
        this.matrix = new ArrayList<>();
    }
    public MyLevel(ArrayList<ArrayList<Part>> data) {
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

        public static MyLevel build(String problem) {
            MyLevel result = new MyLevel();
            String[] rows = problem.split("\n");
            for (int i = 0; i < rows.length; i++) {
                result.matrix.add(new ArrayList<Part>());
                char[] chars = rows[i].toCharArray();
                for (char aChar : chars) {
                    result.matrix.get(i).add(PartBuilder.build(aChar));
                }
            }
            return result;
        }
    }

}