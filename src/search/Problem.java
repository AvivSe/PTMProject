package search;

public interface Problem<T> {
    Searchable<T> getMySearchable();
    Solution getBestSolution();
    /**
     * Any data we want to save about our problem in the future...
     */
}
