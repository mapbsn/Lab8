package Commands;

import DataBase.CommandLogger;
import Objects.StudyGroup;

import java.io.PrintStream;

public class HistoryCommand extends GenericCommand {
   CommandLogger commandLogger;

   public HistoryCommand(PrintStream printStream, CommandLogger commandLogger) {
      super(printStream);
      this.commandLogger = commandLogger;
   }

   public void Execute() throws Exception {
      if (this.printStream != null) {
         this.printStream.println(this.commandLogger);
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - shows last 12 commands.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return args.length == 1;
   }
}
