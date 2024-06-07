package Commands;

import Managers.CommandManager;
import Objects.StudyGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScriptCommand extends GenericCommand {
   private final CommandManager commandManager;
   private static final Set<String> scriptPaths = new HashSet();

   public ExecuteScriptCommand(PrintStream printStream, CommandManager commandManager) {
      super(printStream);
      this.commandManager = commandManager;
   }

   public void Execute() throws Exception {
      String scriptPath = this.tokens[1];
      File scriptFile = new File(scriptPath);
      if (scriptPaths.contains(scriptPath)) {
         this.printStream.println("Error: Recursion detected. Script won't be executed again to prevent infinite recursion.");
      } else {
         try {
            Scanner scanner = new Scanner(scriptFile);

            try {
               scriptPaths.add(scriptPath);

               while(scanner.hasNextLine()) {
                  String line = scanner.nextLine().trim();
                  String[] tokens = line.split("\\s+");
                  if (tokens.length != 0 && !tokens[0].isEmpty()) {
                     GenericCommand command = this.commandManager.getCommand(tokens[0]);
                     if (command != null) {
                        command.setTokens(tokens);
                        if (command.VerifyInputParameters(tokens)) {
                           command.Execute();
                        } else {
                           this.printStream.println("Error in script file at command: " + line);
                        }
                     } else {
                        this.printStream.println("Unknown command in script: " + tokens[0]);
                     }
                  }
               }
            } catch (Throwable var13) {
               try {
                  scanner.close();
               } catch (Throwable var12) {
                  var13.addSuppressed(var12);
               }

               throw var13;
            }

            scanner.close();
         } catch (FileNotFoundException var14) {
            this.printStream.println("Script file not found: " + scriptPath);
         } finally {
            scriptPaths.remove(scriptPath);
         }

      }
   }

   @Override
   public void Execute(StudyGroup param) throws Exception {

   }

   public boolean VerifyInputParameters(String[] tokens) {
      if (tokens.length < 2) {
         this.printStream.println("Error: wrong command arguments!");
         return false;
      } else {
         return true;
      }
   }

   public String Description() {
      return " -  executes commands from a file.";
   }
}
