package Commands;

import DataBase.DataBase;
import Objects.StudyGroup;

import java.io.PrintStream;

public class InfoCommand extends GenericCommand {
   DataBase db;

   public InfoCommand(PrintStream printStream, DataBase db) {
      super(printStream);
      this.db = db;
   }

   public void Execute() throws Exception {
      if (this.printStream != null) {
         this.printStream.println(this.db.Info());
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - show information about database.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return args.length == 1;
   }
}
