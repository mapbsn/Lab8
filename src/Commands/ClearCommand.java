package Commands;

import DataBase.DataBase;
import Managers.UserStatusManager;
import Objects.StudyGroup;

import java.io.PrintStream;

public class ClearCommand extends GenericCommand {
   private final DataBase db;
   private final UserStatusManager userStatusManager;

   public ClearCommand(PrintStream printStream, DataBase db, UserStatusManager userStatusManager) {
      super(printStream);
      this.db = db;
      this.userStatusManager = userStatusManager;
   }

   public void Execute() throws Exception {
      if (!this.userStatusManager.getStatus()) {
         System.out.println("Вы не зарегистрированы!Используйте команды login или register");
      } else {
         this.db.Clear(this.userStatusManager.getUser_name());
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - clear collection.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return args.length == 1;
   }
}
