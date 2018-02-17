/**
 * With this kind of generic, we can describe any kind of steps to goal.
 * the abstract algorithms returns this kind of solution, after that any client or server can interpret it -
 * in my case, I using class called "PgDirections" which take this solution and converts it to vector.
 *
 * Aviv Segal
 */
package searcher_interface;

import java.util.ArrayList;
public class Solution<T> extends ArrayList<T>  {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(T state: this) {
            stringBuilder.append(state.toString());
        }
        return stringBuilder.toString();
    }
}
