package Commands;

import DataBase.DataBase;
import Objects.StudyGroup;

import java.io.PrintStream;

public class PrintUniqueSemesterEnum extends GenericCommand{
    private final DataBase dataBase;
    public PrintUniqueSemesterEnum(PrintStream printStream, DataBase dataBase) {
        super(printStream);
        this.dataBase = dataBase;
    }

    @Override
    public void Execute() throws Exception {
        dataBase.semType();
    }

    @Override
    public void Execute(StudyGroup param) throws Exception {

    }

    @Override
    public String Description() {
        return " - outputs unique semester values";
    }

    @Override
    public boolean VerifyInputParameters(String[] args) {
        return args.length==1;
    }
}
