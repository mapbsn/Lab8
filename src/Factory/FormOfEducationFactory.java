package Factory;

import Objects.FormOfEducation;
import Objects.Semester;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class FormOfEducationFactory extends FactoryOfEnum<FormOfEducation> {
    public FormOfEducationFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
        super(ps, scanner, stopIfError);
    }

    @Override
    public String Values() {
        return Arrays.toString(FormOfEducation.values());
    }

    @Override
    public FormOfEducation ValueOf(String input) {
        try {
            return FormOfEducation.valueOf(input);
        } catch (Exception var3) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Form of Education Factory";
    }
}
