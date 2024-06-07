package Objects;

public class Coordinates {
   private double x;
   private int y;

   public Coordinates(double x, int y) {
      this.x = x;
      this.y = y;
   }

   public Coordinates() {
   }

   public double getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public void setX(double x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }
}
