package Factory;

import java.io.PrintStream;
import java.util.Scanner;

public class FloatFactory extends FactoryOfValue<Float>{
    public FloatFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
        super(ps, scanner, stopIfError);
    }

    @Override
    public void Parse(String input) throws Exception {
        this.value=Float.parseFloat(input);
    }

    @Override
    public String toString() {
        return "FloatFactory";
    }

    @Override
    public String ValueType() {
        return "Float";
    }
}
