package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class FactoryOfEnum<T> extends GenericFactory {
   private final StringFactory stringFactory;

   public abstract String Values();

   public abstract T ValueOf(String var1);

   public FactoryOfEnum(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
      this.stringFactory = new StringFactory(ps, scanner, stopIfError);
   }

   public T Produce(String invitation, boolean mayBeEmpty) throws Exception {
      this.Say(invitation);
      String listOfEnums = this.Values();

      Object value;
      do {
         String input = (String)this.stringFactory.Produce(listOfEnums + " -> ", mayBeEmpty);
         if (input == null || input.isBlank()) {
            return null;
         }

         value = this.ValueOf(input);
         if (value == null) {
            if (this.stopIfError) {
               throw new Exception(this + ": The entered value is not correct!");
            }

            this.SayError("The entered value is not correct!");
         }
      } while(value == null);

      return (T) value;
   }
}
