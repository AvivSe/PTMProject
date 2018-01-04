package Parts;

public class pipe_I extends Pipe {
    State state;
    public pipe_I(char ch) {
        switch(ch) {
            case '-':
                this.setState(new state_minus());
                break;
             default:
                 this.setState(new state_I());
                 break;
        }
    }

    private class state_I implements State {
        @Override
        public String toString() { return "|"; }
        @Override
        public void changeState(Pipe wrapper) { wrapper.setState(new state_minus());}
    }

    private class state_minus implements State {
        @Override
        public String toString() { return "-"; }
        @Override
        public void changeState(Pipe wrapper) { wrapper.setState(new state_I()); }
    }
}
