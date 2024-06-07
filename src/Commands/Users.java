package Commands;

import DataBase.DataBase;
import Objects.StudyGroup;

import java.io.PrintStream;
import java.util.Iterator;

public class Users extends GenericCommand {
   DataBase db;

   public Users(PrintStream printStream, DataBase db) {
      super(printStream);
      this.db = db;
   }

   public void Execute() throws Exception {
      if (this.printStream != null) {
         System.out.println("USERS: ");
         Iterator var1 = this.db.getUsers().iterator();

         while(var1.hasNext()) {
            String users = (String)var1.next();
            System.out.println(users);
         }
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - show users.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return args.length == 1;
   }
}
