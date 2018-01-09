package search;
public interface Searcher<T> {
    // TODO: return solution instead this shit

    Solution search(Searchable<T> searchable);
}
