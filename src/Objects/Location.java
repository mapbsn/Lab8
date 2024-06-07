package Objects;

public class Location {
    private float x;
    private long y;
    private String name; //Длина строки не должна быть больше 836, Поле не может быть null
    public Location(float x, long y, String name){
        this.x=x;
        this.y=y;
        this.name=name;
    }
    public Location(){

    }

    public float getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }
}
