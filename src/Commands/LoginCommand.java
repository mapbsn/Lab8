package Commands;

import DataBase.DataBaseManager;
import Managers.PasswordManager;
import Managers.UserStatusManager;
import Objects.StudyGroup;

import java.io.PrintStream;
import java.util.Scanner;

public class LoginCommand extends GenericCommand {
   DataBaseManager db;
   private final UserStatusManager userStatusManager;

   public LoginCommand(UserStatusManager userStatusManager, PrintStream printStream, DataBaseManager db) {
      super(printStream);
      this.db = db;
      this.userStatusManager = userStatusManager;
   }

   public void Execute() throws Exception {
      Scanner scanner = new Scanner(System.in);

      String inputCheck;
      do {
         do {
            System.out.print("Enter your login: ");
         } while(!scanner.hasNextLine());

         String user_name = scanner.nextLine();
         if (this.db.checkUser(user_name)) {
            do {
               System.out.print("Enter password: ");
               inputCheck = PasswordManager.enterPassword();
               if (this.db.checkPassword(user_name, inputCheck)) {
                  this.userStatusManager.setStatus(true);
                  this.userStatusManager.setUser_name(user_name);
                  System.out.println("You have successfully registered");
                  return;
               }

               System.out.println("Enter 'Yes' if you want to try again");
               inputCheck = scanner.nextLine();
            } while(inputCheck.equals("Yes") || inputCheck.equals("yes"));

            return;
         }

         System.out.println("Invalid username or password");
         System.out.println("Enter 'Yes' if that want to continue");
         inputCheck = scanner.nextLine();
      } while(inputCheck.equals("Yes") || inputCheck.equals("yes"));

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - login user.";
   }

   public boolean VerifyInputParameters(String[] tokens) {
      return tokens.length == 1;
   }
}
