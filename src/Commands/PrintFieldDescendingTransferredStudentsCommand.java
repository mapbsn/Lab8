package Commands;

import DataBase.DataBase;
import Objects.StudyGroup;

import java.io.PrintStream;

public class PrintFieldDescendingTransferredStudentsCommand extends GenericCommand{
    private final DataBase dataBase;
    public PrintFieldDescendingTransferredStudentsCommand(PrintStream printStream, DataBase dataBase) {
        super(printStream);
        this.dataBase = dataBase;
    }

    @Override
    public void Execute() throws Exception {
dataBase.transfer();
    }

    @Override
    public void Execute(StudyGroup param) throws Exception {

    }

    @Override
    public String Description() {
        return " - displays the number of transferred students in reverse order";
    }

    @Override
    public boolean VerifyInputParameters(String[] args) {
        return args.length==1;
    }
}
