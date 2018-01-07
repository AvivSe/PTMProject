package parts;

public abstract class Pipe extends Part implements Rotateable {
    private Rotation rotation;

    Rotation getRotation() {
        return this.rotation;
    }
    void setRotation(Rotation rotation){
        this.rotation = rotation;
    }
    public String toString() {
        return this.rotation.toString();
    }

    @Override
    public int rotate(Pipe target) {
        if(this.getClass()!=target.getClass()) {
            System.out.println("/rotate/Class is not the same.");
            return -1;
        }
        if(this.getRotation().toString().contains(target.getRotation().toString())){
            return 0;
        }
        this.getRotation().changeRotation(this);
        return 1+rotate(target);
    }
}