package pipe_game_server;


import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.invoke.LambdaConversionException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class PgLevel {
    private char[][] matrix;
    public char[][] getMatrix() {
        return matrix;
    }

    public PgLevel(char[][] data) {
        this.matrix = data.clone();
    }

    public PgLevel(int numRows, int numColumns) {
        this.matrix = new char[numRows][numColumns];
    }

    @Override
    public boolean equals(Object o) {
        PgLevel pgLevel2 = (PgLevel) o;
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] != pgLevel2.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public void setObject(int row, int column, char c) {
        this.matrix[row][column] = c;
    }

    public char getObject(int row, int column) {
        return this.matrix[row][column];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int lengthRows = getNumOfRows();
        int lengthCols = getNumOfCol();
        for(int i = 0; i < lengthRows; i++) {
            for(int j = 0; j < lengthCols; j++) {
                result.append(matrix[i][j]);
            }
            result.append("\n");
        }

        String resultString = result.toString();
        int len = resultString.length();
        return resultString.substring(0,len-1); // remove last \n
    }

    public int getNumOfRows() { return this.matrix.length; }

    public int getNumOfCol() { return this.matrix[0].length;}

    public Point getStart() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] == 's') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public Point getGoal() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] == 'g') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

//    interface FunciMonkey {
//        void action(Object obj);
//    }

//    public void foreach(FunciMonkey funciMonkey, Predicate predicate) {
//        for(ArrayList<Part> row: matrix) {
//            for (Part part : row) {
//                if (predicate.test(part)){
//                    funciMonkey.action(part);
//                }
//            }
//        }
//    }

//    public void foreach(FunciMonkey funciMonkey) {
//        for(ArrayList<Part> row: matrix) {
//            for (Part part : row) {
//                    funciMonkey.action(part);
//            }
//        }
//    }

    public static class LevelBuilder {

        public static PgLevel build(String problem) {
            String[] rows = problem.split("\n");
            int numRows = rows.length;
            int numColumns = rows[0].length();

            PgLevel result = new PgLevel(numRows,numColumns);

            for (int i = 0; i < result.getNumOfRows(); i++) {
                char[] chars = rows[i].toCharArray();
                for (int j = 0; j < result.getNumOfCol(); j++) {
                    result.matrix[i][j] = chars[j];
                }
            }
            return result;
        }
    }

    protected PgLevel copy() {
        StringBuilder stringBuilder = new StringBuilder();

        for(char[] line: this.getMatrix()) {
            for(char c: line) {
                stringBuilder.append(c);
            }
            stringBuilder.append("\n");
        }
        String result = stringBuilder.toString();
        return LevelBuilder.build(result.substring(0, result.length()-1));
    }

    public static void main(String[] args) {
        PgLevel level = LevelBuilder.build("s-7\n|-g");
        System.out.println(level);
        //System.out.println(level.getStart());
        //System.out.println(level.getGoal());
    }

}