/**
 * Searcher Interface
 * In my project I use those abstract algorithms, adjacent to their pseudo code.
 * The idea is to use those alg' as a black box, we only have to create the right adapter in order to use them.
 * Aviv Segal 12/2017
 */

package searcher_interface;

public interface Searcher<T> {
    Solution<T> search(Searchable<T> searchable);

}

