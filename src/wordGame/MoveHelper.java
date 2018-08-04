package wordGame;

import java.util.Objects;

public class MoveHelper {
    int i;
    int j;
    int distance;
    String wordState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveHelper that = (MoveHelper) o;
        return Objects.equals(wordState, that.wordState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordState);
    }

    public MoveHelper(String wordState) {
        this.i = 0;
        this.j = 0;
        distance = 0;
        this.wordState = wordState;
    }

    public MoveHelper(String move, String wordState) {
        String[] parse = move.split(",");
        this.i = Integer.valueOf(parse[0]);
        this.j = Integer.valueOf(parse[1]);
        this.wordState = switchChars(wordState);
        distance = Math.abs(i-j);
    }

    private String switchChars(String wordState) {
        char[] work = wordState.toCharArray();
        char t = work[this.i];
        work[this.i] = work[this.j];
        work[this.j] = t;
        return new String(work);
    }

    String getMove() {
        return i+","+j;
    }

}
