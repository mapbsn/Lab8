package Commands;

import DataBase.DataBase;

import Factory.StudyGroupFactory;
import Managers.UserStatusManager;
import Objects.*;
import Objects.StudyGroup;

import java.io.PrintStream;

public class AddIfMaxCommand extends GenericCommand {
   DataBase db;
   StudyGroupFactory studyGroupFactory;
   UserStatusManager userStatusManager;

   public AddIfMaxCommand(PrintStream printStream, DataBase db, StudyGroupFactory studyGroupFactory, UserStatusManager userStatusManager) {
      super(printStream);
      this.db = db;
      this.studyGroupFactory = studyGroupFactory;
      this.userStatusManager = userStatusManager;
   }

   public void Execute() throws Exception {
      if (!this.userStatusManager.getStatus()) {
         System.out.println("Вы не зарегистрированы!Используйте команды login или register");
      } else {
         StudyGroup studyGroup = this.studyGroupFactory.Produce("Make a study group:\n");
         studyGroup.setUser_name(this.userStatusManager.getUser_name());
         this.db.AddIfMax(studyGroup);
      }
   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " -  add a study group in collection it is maximum.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return true;
   }
}
