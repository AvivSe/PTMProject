package parts;

public class EmptyPart extends Part{
    public String toString() { return " "; }

    @Override
    public Character charface() {
        return ' ';
    }
}
