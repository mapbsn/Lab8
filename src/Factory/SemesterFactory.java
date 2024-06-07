package Factory;

import Objects.Semester;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class SemesterFactory extends FactoryOfEnum<Semester> {
   public SemesterFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
   }

   public String Values() {
      return Arrays.toString(Semester.values());
   }

   public Semester ValueOf(String input) {
      try {
         return Semester.valueOf(input);
      } catch (Exception var3) {
         return null;
      }
   }

   public String toString() {
      return "SemesterFactory";
   }
}
