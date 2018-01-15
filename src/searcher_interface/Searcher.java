package searcher_interface;

public interface Searcher<T> {
    Solution search(Searchable<T> searchable);
}
