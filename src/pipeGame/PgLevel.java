/**
 * This class describes my Pipe Game problem.
 * in this game we have water flow from 's' to 'g', its a n*m grid and the water have to flow trow the pipes.
 * so in this case, Position symbols the location in the grid of the water, to describe various of 'states' we -
 * can both rotate the pipe and change the position.
 * found it's better to hold both board and position same object, consider the board is not static in Pipe Game.
 * Aviv Segal
 */
package pipeGame;


import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class PgLevel {
    private char[][] matrix;
    private Point position;

    Point start;
    Point end;

    public char[][] getMatrix() {
        return matrix;
    }

    public PgLevel(PgLevel level) {
        this.matrix = new char[level.getNumOfRows()][level.getNumOfCols()];

        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCols(); j++) {
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

    public void setRow(int row) {
        this.position.x = row;
    }

    public void setCol(int col) {
        this.position.y = col;
    }

    public int getRow() {
        return this.position.x;
    }

    public int getCol() {
        return this.position.y;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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
        int lengthCols = getNumOfCols();
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

    public int getNumOfCols() { return this.matrix[0].length;}

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

        for(int i =0; i < getNumOfRows(); i++) {
            for(int j = 0; j < getNumOfCols(); j++) {
                if(matrix[i][j] != pgLevel.matrix[i][j])
                    return false;
            }
        }

        return Objects.equals(position, pgLevel.position);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(position);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    public static void main(String[] args) {
        PgLevel level = PgLevelBuilder.build("s-7\n|-g");
//        System.out.println(level);
    }

}