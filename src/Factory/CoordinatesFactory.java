package Factory;

import Objects.Coordinates;
import java.io.PrintStream;
import java.util.Scanner;

public class CoordinatesFactory extends GenericFactory {
   private final IntegerFactory integerFactory;
   private final DoubleFactory doubleFactory;

   public CoordinatesFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
      this.integerFactory = new IntegerFactory(ps, scanner, stopIfError);
      this.doubleFactory=new DoubleFactory(ps, scanner, stopIfError);
   }

   public Coordinates Produce(String invitation, boolean mayBeEmpty) {
      this.Say(invitation);
      Double x = null;

      while(x == null) {
         try {
            x = (this.doubleFactory.Produce("x: ", mayBeEmpty)).doubleValue();
            if (x <=-108) {
               this.SayError("Координата должна быть больше -108");
               x = null;
            }
         } catch (Exception var7) {
            this.SayError(var7.getMessage() + "");
         }
      }

      Integer y = null;

      while(y == null) {
         try {
            y = ((Long)this.integerFactory.Produce("y: ", true)).intValue();
            if (y <-1000) {
               this.SayError("y должен быть больше -1000");
               y = null;
            }
         } catch (Exception var6) {
            this.SayError(var6.getMessage() + "");
         }
      }

      return new Coordinates(x, y);
   }
}
