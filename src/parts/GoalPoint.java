package parts;

public class GoalPoint extends Part {
    public String toString() {
        return ""+charface();
    }

    @Override
    public Character charface() {
        return 'g';
    }
}
