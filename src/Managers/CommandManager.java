package Managers;

import Commands.*;
import DataBase.CommandLogger;
import DataBase.DataBase;
import DataBase.DataBaseManager;
import Factory.StudyGroupFactory;
import Objects.StudyGroup;


import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class CommandManager {
   private final DataBase db;
   private final DataBaseManager dataBaseManager;
   private final PrintStream printStream;
   private final Map<String, GenericCommand> commands = new HashMap();
   private final Scanner scanner;
   private final StudyGroupFactory studyGroupFactory;
   private final ExitCommand exitCommand;
   private final CommandLogger commandLogger;
   private final UserStatusManager userStatusManager;

   public CommandManager(UserStatusManager userStatusManager, DataBaseManager dataBaseManager, DataBase db, Scanner scanner, boolean stopIfError) {
      this.userStatusManager = userStatusManager;
      this.db = db;
      this.scanner = scanner;
      this.printStream = System.out;
      this.studyGroupFactory = new StudyGroupFactory(this.printStream, scanner, stopIfError);
      this.commandLogger = new CommandLogger();
      this.exitCommand = new ExitCommand(this.printStream);
      this.dataBaseManager = dataBaseManager;
      this.initializeCommands();
   }

   private void initializeCommands() {
      this.commands.put("add", new AddCommand(this.printStream, this.db, this.studyGroupFactory, this.userStatusManager));
      this.commands.put("add_if_max", new AddIfMaxCommand(this.printStream, this.db, this.studyGroupFactory, this.userStatusManager));
      this.commands.put("clear", new ClearCommand(this.printStream, this.db, this.userStatusManager));
      this.commands.put("exit", this.exitCommand);
      this.commands.put("help", new HelpCommand(this.printStream, this));
      this.commands.put("history", new HistoryCommand(this.printStream, this.commandLogger));
      this.commands.put("info", new InfoCommand(this.printStream, this.db));
      this.commands.put("remove_by_id", new RemoveByIdCommand(this.printStream, this.db, this.userStatusManager));
      this.commands.put("remove_lower", new RemoveLower(this.printStream, this.userStatusManager, this.db));
      this.commands.put("print_unique", new PrintUniqueSemesterEnum(this.printStream,this.db));
      this.commands.put("transfer", new PrintFieldDescendingTransferredStudentsCommand(this.printStream, this.db));
      this.commands.put("group", new GroupCountingByNameCommand(this.printStream, this.db));
      this.commands.put("show", new ShowCommand(this.printStream, this.db));
      this.commands.put("update", new UpdateCommand(this.printStream, this.db, this.studyGroupFactory, this.userStatusManager));
      this.commands.put("save", new SaveCommand(this.printStream, this.db, this.userStatusManager));
      this.commands.put("execute_script", new ExecuteScriptCommand(this.printStream, this));
      this.commands.put("login", new LoginCommand(this.userStatusManager, this.printStream, this.dataBaseManager));
      this.commands.put("register", new RegisterCommand(this.userStatusManager, this.printStream, this.dataBaseManager));
      this.commands.put("logout", new LogoutCommand(this.userStatusManager, this.printStream));
      this.commands.put("users", new Users(this.printStream, this.db));
   }

   public GenericCommand getCommand(String commandName) {
      return (GenericCommand)this.commands.get(commandName);
   }

   public void Run() {
      System.out.println("");

      while(!this.exitCommand.getExitCondition()) {
         this.printStream.print("");
         if (this.scanner.hasNextLine()) {
            String commandLine = this.scanner.nextLine();
            this.processCommand(commandLine);
         }
      }

   }

   public void processCommand(String commandLine) {
      if (!commandLine.isEmpty()) {
         String[] tokens = commandLine.trim().split("\\s+");
         if (tokens.length != 0) {
            String commandName = tokens[0];
            GenericCommand command = this.getCommand(commandName);
            if (command != null) {
               try {
                  command.setTokens(tokens);
                  if (command.VerifyInputParameters(tokens)) {
                     command.Execute();
                     this.commandLogger.Add(commandName);
                  }
               } catch (Exception var6) {
                  this.printStream.println("");
               }
            } else {
               this.printStream.println("This command not found");
            }

         }
      }
   }

   public String Help() {
      StringBuilder help = new StringBuilder("List of commands:\n");
      Iterator var2 = this.commands.entrySet().iterator();

      while(var2.hasNext()) {
         Entry<String, GenericCommand> entry = (Entry)var2.next();
         help.append((String)entry.getKey()).append(": ").append(((GenericCommand)entry.getValue()).Description()).append("\n");
      }

      return help.toString();
   }
}
