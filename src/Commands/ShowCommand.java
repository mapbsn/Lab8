package Commands;

import DataBase.DataBase;
import Objects.StudyGroup;

import java.io.PrintStream;

public class ShowCommand extends GenericCommand {
   private final DataBase db;

   public ShowCommand(PrintStream printStream, DataBase db) {
      super(printStream);
      this.db = db;
   }

   public void Execute() throws Exception {
      if (this.printStream != null) {
         this.printStream.println(this.db);
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - prints out the entities from the database.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return args.length == 1;
   }
}
