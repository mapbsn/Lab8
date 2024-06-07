package Commands;

import DataBase.DataBase;

import Factory.StudyGroupFactory;
import Managers.UserStatusManager;

import Objects.StudyGroup;

import java.io.PrintStream;

public class UpdateCommand extends GenericCommand {
   private Long id;
   private final DataBase db;
   private final StudyGroupFactory studyGroupFactory;
   private final UserStatusManager userStatusManager;



   public UpdateCommand(PrintStream printStream, DataBase db, StudyGroupFactory studyGroupFactory, UserStatusManager userStatusManager) {
      super(printStream);
      this.db = db;
      this.studyGroupFactory = studyGroupFactory;
      this.userStatusManager = userStatusManager;
   }

   public void Execute() throws Exception {
    StudyGroup studyGroup = this.studyGroupFactory.Produce("Make a study group:\n");
    studyGroup.setUser_name(studyGroup.getUser_name());
      if (!this.userStatusManager.getStatus()) {
         System.out.println("Вы не зарегистрированы!Используйте команды login или register");
      } else {
         this.db.Update(this.id, studyGroup, this.userStatusManager.getUser_name());
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - updates a study group which \"id\" = this.id.";
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
