package parts;

public class pipe_I extends Pipe {
    Rotation rotation;
    public pipe_I(char ch) {
        switch(ch) {
            case '-':
                this.setRotation(new state_minus());
                break;
             default:
                 this.setRotation(new state_I());
                 break;
        }
    }

    private class state_I implements Rotation {
        @Override
        public String toString() { return "|"; }
        @Override
        public void changeRotation(Pipe wrapper) { wrapper.setRotation(new state_minus());}
    }

    private class state_minus implements Rotation {
        @Override
        public String toString() { return "-"; }
        @Override
        public void changeRotation(Pipe wrapper) { wrapper.setRotation(new state_I()); }
    }
}
