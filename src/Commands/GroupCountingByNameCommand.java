package Commands;

import DataBase.DataBase;
import Objects.StudyGroup;

import java.io.PrintStream;

public class GroupCountingByNameCommand extends GenericCommand{
    private final DataBase dataBase;
    public GroupCountingByNameCommand(PrintStream printStream, DataBase dataBase) {
        super(printStream);
        this.dataBase = dataBase;
    }

    @Override
    public void Execute() throws Exception {
        dataBase.getSortedCollection();
    }

    @Override
    public void Execute(StudyGroup param) throws Exception {

    }

    @Override
    public String Description() {
        return " - sorts the collection by name";
    }

    @Override
    public boolean VerifyInputParameters(String[] args) {
        return args.length==1;
    }
}
