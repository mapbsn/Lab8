package Commands;

import DataBase.DataBase;
import Factory.StudyGroupFactory;
import Managers.UserStatusManager;
import Objects.StudyGroup;

import java.io.PrintStream;


public class RemoveLower extends GenericCommand{
    UserStatusManager userStatusManager;
    DataBase dataBase;



    public RemoveLower(PrintStream printStream, UserStatusManager userStatusManager, DataBase dataBase) {
        super(printStream);
        this.userStatusManager = userStatusManager;
        this.dataBase = dataBase;

    }

    @Override
    public void Execute() throws Exception {
        if (!this.userStatusManager.getStatus()) {
            System.out.println("Вы не зарегистрированы!Используйте команды login или register");
        } else {
            this.dataBase.RemoveLower(this.userStatusManager.getUser_name());
        }
    }

    @Override
    public void Execute(StudyGroup param) throws Exception {

    }

    @Override
    public String Description() {
        return " - remove lower elements in collection";
    }
    public boolean VerifyInputParameters(String[] tokens) {
        return tokens.length==1;
        }
    }


