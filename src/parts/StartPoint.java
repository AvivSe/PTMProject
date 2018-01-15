package parts;

public class StartPoint extends Part {
    public String toString() {
        return ""+charface();
    }

    @Override
    public Character charface() {
        return 's';
    }
}
