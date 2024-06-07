package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public class StringFactory extends FactoryOfValue<String> {
   public StringFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
   }

   public void Parse(String input) {
      this.value = input;
   }

   public String toString() {
      return "StringFactory";
   }

   public String ValueType() {
      return "String";
   }
}
