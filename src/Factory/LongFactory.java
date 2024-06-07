package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public class LongFactory extends FactoryOfValue<Long> {
   public LongFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
   }

   public void Parse(String input) {
      this.value = Long.parseLong(input);
   }

   public String toString() {
      return "LongFactory";
   }

   public String ValueType() {
      return "Long";
   }
}
