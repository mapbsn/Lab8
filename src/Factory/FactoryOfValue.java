package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class FactoryOfValue<T> extends GenericFactory {
   T value;

   public abstract void Parse(String var1) throws Exception;

   public abstract String ValueType();

   public FactoryOfValue(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
   }

   public T Produce(String invitation, boolean mayBeEmpty) throws Exception {
      while(true) {
         this.Say(invitation);
         String input = "";
         if (this.scanner.hasNextLine()) {
            input = this.scanner.nextLine();
         }

         if (input.isBlank()) {
            if (mayBeEmpty) {
               return null;
            }

            if (this.stopIfError) {
               throw new Exception(this + ": The entered can't be an empty!");
            }

            this.SayError("The entered can't be an empty!");
         } else {
            try {
               this.Parse(input);
               return this.value;
            } catch (NumberFormatException var5) {
               if (this.stopIfError) {
                  throw new Exception(this + String.format("", this.ValueType()));
               }

               this.SayError(String.format("", this.ValueType()));
            }
         }
      }
   }
}
