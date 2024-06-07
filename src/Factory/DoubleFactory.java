package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public class DoubleFactory extends FactoryOfValue<Double> {
   public DoubleFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
   }

   public void Parse(String input) {
      this.value = Double.parseDouble(input);
   }

   public String toString() {
      return "DoubleFactory";
   }

   public String ValueType() {
      return "Double";
   }
}
