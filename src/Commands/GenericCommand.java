package Commands;

import Objects.StudyGroup;

import java.io.PrintStream;

public abstract class GenericCommand {
   protected PrintStream printStream;
   protected String[] tokens;

   public GenericCommand(PrintStream printStream) {
      this.printStream = printStream;
   }

   public abstract void Execute() throws Exception;

   public abstract void Execute(StudyGroup param) throws Exception;

   public abstract String Description();

   public void setTokens(String[] tokens) {
      this.tokens = tokens;
   }

   public boolean VerifyInputParameters(String[] tokens) {
      return true;
   }
}
