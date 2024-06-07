package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public class IntegerFactory extends FactoryOfValue<Long> {
   public IntegerFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
   }

   public void Parse(String input) {
      this.value = (long)Integer.parseInt(input);
   }

   public String toString() {
      return "IntegerFactory";
   }

   public String ValueType() {
      return "Integer";
   }
}
