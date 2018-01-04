package Parts;

public class Part {
    private State state;

    State getState() {
        return this.state;
    }
    void setState(State state){
        this.state=state;
    }
}
