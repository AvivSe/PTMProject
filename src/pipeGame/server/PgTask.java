package pipeGame.server;

public class PgTask implements Runnable, Comparable<PgTask> {
    private int weight;
    private Runnable r;

    public PgTask(Runnable r, int weight) {
        this.r = r;
        this.weight = weight;
    }

    @Override
    public void run() {
        r.run();
    }

    @Override
    public int compareTo(PgTask o) {
        return Integer.compare(this.weight, o.weight);
    }

}
