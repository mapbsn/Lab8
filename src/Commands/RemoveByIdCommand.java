package Commands;

import DataBase.DataBase;
import Managers.UserStatusManager;
import Objects.StudyGroup;

import java.io.PrintStream;

public class RemoveByIdCommand extends GenericCommand {
   private final DataBase db;
   private final UserStatusManager userStatusManager;
   private Long id;

   public RemoveByIdCommand(PrintStream printStream, DataBase db, UserStatusManager userStatusManager) {
      super(printStream);
      this.db = db;
      this.userStatusManager = userStatusManager;
   }

   public void Execute() throws Exception {
      if (!this.userStatusManager.getStatus()) {
         System.out.println("Вы не зарегистрированы!Используйте команды login или register");
      } else {
         this.db.RemoveById(this.id, this.userStatusManager.getUser_name());
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " -  removes the element with entered id.";
   }

   public boolean VerifyInputParameters(String[] tokens) {
      if (tokens.length != 2) {
         return false;
      } else {
         try {
            this.id = Long.parseLong(tokens[1]);
            return true;
         } catch (Exception var3) {
            return false;
         }
      }
   }
}
