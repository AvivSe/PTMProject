package Parts;

public class Pipe extends Part implements Rotateable {
    private State state;

    State getState() {
        return this.state;
    }
    void setState(State state){
        this.state=state;
    }
    public String toString() {
        return this.state.toString();
    }

    @Override
    public int rotate(Pipe target) {
        if(this.getClass()!=target.getClass()) {
            System.out.println("/rotate/Class is not the same.");
            return -1;
        }
        if(this.getState().toString().contains(target.getState().toString())){
            return 0;
        }
        this.getState().changeState(this);
        return 1+rotate(target);
    }
}