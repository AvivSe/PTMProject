package game_server;

public interface Searcher<T> {
    Solution search(Searchable<T> searchable);
}
