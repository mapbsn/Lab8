package Commands;

import DataBase.DataBaseManager;
import Managers.PasswordManager;
import Managers.UserStatusManager;
import Objects.StudyGroup;

import java.io.PrintStream;
import java.util.Scanner;

public class RegisterCommand extends GenericCommand {
   private final DataBaseManager db;
   public UserStatusManager userStatusManager;

   public RegisterCommand(UserStatusManager userStatusManager, PrintStream printStream, DataBaseManager db) {
      super(printStream);
      this.userStatusManager = userStatusManager;
      this.db = db;
   }

   public void Execute() throws Exception {
      Scanner scanner = new Scanner(System.in);

      String pass1;
      do {
         do {
            this.printStream.print("Enter your name: ");
         } while(!scanner.hasNextLine());

         String user_name = scanner.nextLine();
         if (!this.db.checkUser(user_name)) {
            String inputCheck;
            do {
               System.out.print("Enter your password: ");
               pass1 = PasswordManager.enterPassword();
               System.out.print("Accept your password: ");
               String pass2 = PasswordManager.enterPassword();
               if (!pass1.isEmpty() && pass1.equals(pass2)) {
                  this.db.registerUser(user_name, PasswordManager.hashPassword(pass1));
                  this.userStatusManager.setUser_name(user_name);
                  this.userStatusManager.setStatus(true);
                  System.out.println("You have successfully registered");
                  return;
               }

               System.out.println("Passwords not match");
               System.out.println("Enter 'Yes' to continue'");
               inputCheck = scanner.nextLine();
            } while(inputCheck.equals("Yes") || inputCheck.equals("yes"));

            return;
         }

         System.out.println("This name is already exists!");
         System.out.println("Enter 'Yes' to try to login with another name or use command 'login'");
         pass1 = scanner.nextLine();
      } while(pass1.equals("Yes") || pass1.equals("yes"));

   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public String Description() {
      return " - adds user.";
   }

   public boolean VerifyInputParameters(String[] args) {
      return args.length == 1;
   }
}
