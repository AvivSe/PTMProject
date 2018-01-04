package Parts;

public class pipe_L extends Pipe{
    public pipe_L(char ch) {
        switch(ch) {
            case 'F':
                this.setState(new state_F());
                break;
            case '7':
                this.setState(new state_7());
                break;
            case 'J':
                this.setState(new state_J());
                break;
            default:
                this.setState(new state_L());
                break;
        }
    }
    class state_L implements State {
        @Override
        public String toString() { return "L"; }
        @Override
        public void changeState(Pipe wrapper) { wrapper.setState(new state_F());}
    }

    private class state_F implements State {
        @Override
        public String toString() { return "F"; }
        @Override
        public void changeState(Pipe wrapper) { wrapper.setState(new state_7());}
    }

    private class state_7 implements State {
        @Override
        public String toString() { return "7"; }
        @Override
        public void changeState(Pipe wrapper) { wrapper.setState(new state_J());}
    }

    private class state_J implements State {
        @Override
        public String toString() { return "J"; }
        @Override
        public void changeState(Pipe wrapper) { wrapper.setState(new state_L());}
    }
}
