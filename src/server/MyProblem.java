package server;

import level.MyLevel;
import search.Problem;
import search.Solution;

public class MyProblem implements Problem <MyLevel> {
    private MySearchable mySearchable;


    public MyProblem(MySearchable mySearchable) {
        this.mySearchable = mySearchable;
    }

    @Override
    public MySearchable getMySearchable() {
        return this.mySearchable;
    }

    @Override
    public Solution getBestSolution() {
        return null;
    }
}
