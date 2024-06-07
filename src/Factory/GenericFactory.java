package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public class GenericFactory {
   final boolean stopIfError;
   final Scanner scanner;
   final PrintStream ps;

   public GenericFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      this.stopIfError = stopIfError;
      this.scanner = scanner;
      this.ps = ps;
   }

   public void Say(String message) {
      if (this.ps != null) {
         this.ps.printf("%s", message);
      }

   }

   public void SayError(String message) {
      if (this.ps != null) {
         this.ps.printf(" %s\n", message);
      }

   }
}
