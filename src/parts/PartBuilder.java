package parts;

public class PartBuilder {
    public static Part build(char ch){
        switch(ch) {
            case 'L':
            case 'J':
            case 'F':
            case '7':
                return new pipe_L(ch);
            case '|':
            case '-':
                return new pipe_I(ch);
            case 's':
                return new StartPoint();
            case 'g':
                return new GoalPoint();
            case ' ':
                return new EmptyPart();
            default:
                System.out.println("Unknown kind of Part. we support: {s,g,L,F,7,J,|,-, }");
                return null;

        }
    }
}
