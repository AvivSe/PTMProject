package pipe_game_server;

import parts.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.invoke.LambdaConversionException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PgLevel pgLevel = (PgLevel) o;
        return matrix.hashCode() == ((PgLevel) o).matrix.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrix.toString());
    }

    public Part getObject(int row, int column) {
        return this.matrix.get(row).get(column);
    }

    @Override
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

    public Point getStart() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix.get(i).get(j).getClass().toString().contains("StartPoint")) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public Point getGoal() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix.get(i).get(j).getClass().toString().contains("GoalPoint")) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    interface FunciMonkey {
        void action(Object obj);
    }

    public void foreach(FunciMonkey funciMonkey, Predicate predicate) {
        for(ArrayList<Part> row: matrix) {
            for (Part part : row) {
                if (predicate.test(part)){
                    funciMonkey.action(part);
                }
            }
        }
    }

    public void foreach(FunciMonkey funciMonkey) {
        for(ArrayList<Part> row: matrix) {
            for (Part part : row) {
                    funciMonkey.action(part);
            }
        }
    }

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

    protected PgLevel copy() {
        StringBuilder stringBuilder = new StringBuilder();

        for(ArrayList<Part> line: this.getMatrix()) {
            for(Part p: line) {
                stringBuilder.append(p.toString());
            }
            stringBuilder.append("\n");
        }
        String result = stringBuilder.toString();
        return LevelBuilder.build(result.substring(0, result.length()-1));
    }

    public static void main(String[] args) {
        PgLevel level = LevelBuilder.build("s-7\n|-g");
        System.out.println(level);
        try {
            System.out.println(level.getObject(5, 5).getClass().toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(level.getStart());
        System.out.println(level.getGoal());
    }

}