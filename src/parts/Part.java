package parts;

import java.util.Objects;

public abstract class Part {
    public abstract Character charface();

    public static void main(String[] args) {
        Part p = new pipe_L('L');
        System.out.println(p.getClass().getSuperclass().getInterfaces()[0].toString().contains("Rotateable"));
    }
}