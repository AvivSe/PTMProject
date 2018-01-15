package parts;

public class pipe_L extends Pipe{
    public pipe_L(char ch) {
        switch(ch) {
            case 'F':
                this.setRotation(new state_F());
                break;
            case '7':
                this.setRotation(new state_7());
                break;
            case 'J':
                this.setRotation(new state_J());
                break;
            default:
                this.setRotation(new state_L());
                break;
        }
    }

    @Override
    public Character charface() {
        return this.getRotation().charface();
    }

    class state_L implements Rotation {
        @Override
        public Character charface() { return  'L'; }
        @Override
        public String toString() { return ""+charface(); }
        @Override
        public void changeRotation(Pipe wrapper) { wrapper.setRotation(new state_F());}
    }

    private class state_F implements Rotation {
        @Override
        public Character charface() { return  'F'; }
        @Override
        public String toString() { return ""+charface(); }
        @Override
        public void changeRotation(Pipe wrapper) { wrapper.setRotation(new state_7());}
    }

    private class state_7 implements Rotation {
        @Override
        public Character charface() { return  '7'; }
        @Override
        public String toString() { return ""+charface(); }
        @Override
        public void changeRotation(Pipe wrapper) { wrapper.setRotation(new state_J());}
    }

    private class state_J implements Rotation {
        @Override
        public Character charface() { return  'J'; }
        @Override
        public String toString() { return ""+charface(); }
        @Override
        public void changeRotation(Pipe wrapper) { wrapper.setRotation(new state_L());}
    }
}
