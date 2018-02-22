package pipe_game_server;

import searcher_interface.Heuristic;
import searcher_interface.State;

import java.util.Random;

public class PgMyHeuristic implements Heuristic<PgLevel> {
    private Random random = new Random();
    @Override
    public double indication() {
        return 0;
    }

    @Override
    public boolean isLeftBetter(double left, double right) {
        return left > right;
    }

    private boolean isOutOfBound(int i, int j, PgLevel board) {
        return (i < 0 || i >= board.getNumOfRows() ||
                j < 0 || j >= board.getNumOfCols());
    }

    int connectedPipes(State<PgLevel> s) {
        PgLevel board = s.getState();
        char c = board.getObjectOnPosition();
        int row = board.getRow();
        int col = board.getCol();
        int connectedPipes = 0;

        if (canGoRight(board,row,col)) connectedPipes++;
        if (canGoUp(board,row,col)) connectedPipes++;
        if (canGoLeft(board,row,col)) connectedPipes++;
        if (canGoDown(board,row,col)) connectedPipes++;

        return connectedPipes;
    }

    private boolean canGoLeft(PgLevel board, int row, int col) {
        char c= board.getObjectOnPosition();
        if (!isOutOfBound(row, col + 1, board) && (c == '7' || c == 'J' || c == '-')) {
            char r = board.getObject(row , col + 1);
            return r == 'F' || r == '-' || r == 'L';
        }

        return false;
    }

    private boolean canGoDown(PgLevel board, int row, int col) {
        char c= board.getObjectOnPosition();
        if (!isOutOfBound(row+1, col , board) && (c == '|' || c == 'F' || c == '7')) {
            char r = board.getObject(row+1, col);
            return r == 'J' || r == '|' || r == 'L';
        }
        return false;
    }

    private boolean canGoRight(PgLevel board, int row, int col) {
        char c= board.getObjectOnPosition();
        if (!isOutOfBound(row, col + 1, board) && (c == 'L' || c == 'F' || c == '-')) {
            char r = board.getObject(row , col + 1);
            return r == 'J' || r == '-' || r == '7';
        }

        return false;
    }

    private boolean canGoUp(PgLevel board, int row, int col) {
        char c= board.getObjectOnPosition();
        if (!isOutOfBound(row-1, col, board) && (c == 'L' || c == '|' || c == 'J')) {
            char r = board.getObject(row -1, col);
            return r == 'F' || r == '|' || r == '7';
        }

        return false;
    }



    @Override
    public double calcHeuristic(State<PgLevel> s) {
        int positionX = s.getState().getPosition().x;
        int positionY = s.getState().getPosition().y;
        int endX = s.getState().getEnd().x;
        int endY = s.getState().getEnd().y;

        return (random.nextInt()*connectedPipes(s))/(Math.abs(positionX - endX) + Math.abs(positionY - endY) + 1);
    }
}
