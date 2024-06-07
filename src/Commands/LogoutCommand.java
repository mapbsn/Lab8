package Commands;

import Managers.UserStatusManager;
import Objects.StudyGroup;

import java.io.PrintStream;
import java.util.Scanner;

public class LogoutCommand extends GenericCommand {
   private final UserStatusManager userStatusManager;

   public LogoutCommand(UserStatusManager userStatusManager, PrintStream printStream) {
      super(printStream);
      this.userStatusManager = userStatusManager;
   }

   public void Execute() throws Exception {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter 'Yes' if you want to log out of your account");
      String inputCheck = scanner.nextLine();
      if (inputCheck.equals("Yes") || inputCheck.equals("yes")) {
         this.userStatusManager.setStatus(false);
         this.userStatusManager.setUser_name("");
         System.out.println("You are logged out");
      }

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - logout user.";
   }

   public boolean VerifyInputParameters(String[] tokens) {
      return tokens.length == 1;
   }
}
