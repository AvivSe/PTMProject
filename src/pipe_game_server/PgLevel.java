/**
 * This class describes my Pipe Game problem.
 * in this game we have water flow from 's' to 'g', its a n*m grid and the water have to flow trow the pipes.
 * so in this case, Position symbols the location in the grid of the water, to describe various of 'states' we -
 * can both rotate the pipe and change the position.
 * Aviv Segal
 */
package pipe_game_server;


import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class PgLevel {
    private char[][] matrix;
    Point position;

    Point start;
    Point end;


    public char[][] getMatrix() {
        return matrix;
    }


    public PgLevel(PgLevel level) {
        this.matrix = new char[level.getNumOfRows()][level.getNumOfCol()];

        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                this.matrix[i][j] = level.matrix[i][j];
            }
        }

        this.setStart(level.getStart());
        this.setEnd(level.getEnd());
        this.position = new Point(level.position.x,level.position.y);
    }

    public PgLevel(int numRows, int numColumns) {
        this.matrix = new char[numRows][numColumns];
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void setObject(int row, int column, char c) {
        this.matrix[row][column] = c;
    }

    public void setX(int x) {
        this.position.x = x;
    }

    public void setY(int y) {
        this.position.y = y;
    }

    public char getObject(int row, int column) {
            return this.matrix[row][column];
    }

    public char getObjectOnPosition() {
        return this.matrix[position.x][position.y];
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
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PgLevel pgLevel = (PgLevel) o;
        return this.position.equals(pgLevel.position) && Arrays.equals(matrix, pgLevel.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(position.x, position.y);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }


    public static void main(String[] args) {
        PgLevel level = LevelBuilder.build("s-7\n|-g");
//        System.out.println(level);
    }

}