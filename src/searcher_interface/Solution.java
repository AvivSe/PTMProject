package searcher_interface;

import java.util.ArrayList;

public class Solution<T> extends ArrayList<T> {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T state : this) {
            stringBuilder.append(state.toString());
        }
        return stringBuilder.toString();
    }
}