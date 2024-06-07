package Factory;

import Objects.Location;
import Objects.Person;

import java.io.PrintStream;
import java.util.Scanner;

public class PersonFactory extends GenericFactory{
    private final StringFactory stringFactory;
    private final FloatFactory floatFactory;
    private final LocationFactory locationFactory;

    public PersonFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
        super(ps, scanner, stopIfError);
        this.locationFactory = new LocationFactory(ps, scanner, stopIfError);
        this.floatFactory=new FloatFactory(ps, scanner, stopIfError);
        this.stringFactory=new StringFactory(ps, scanner, stopIfError);
    }
    public Person Produce(String invitation, boolean mayBeEmpty){
        this.Say(invitation);
        Float weight=null;
        while (weight==null){
            try {
                weight=(this.floatFactory.Produce("Weight: ", false)).floatValue();
                if(weight<=0){
                    this.SayError("Вес должен быть больше нуля");
                    weight=null;
                }
            }catch (Exception e){
                this.SayError(e.getMessage());
            }
        }
        String name=null;
        while(name==null){
            try {
                name=(this.stringFactory.Produce("Name: ", false)).toString();
            }catch (Exception e){
                this.SayError(e.getMessage());
            }
        }
String passport=null;
        while (passport==null){
            try {
                passport=(this.stringFactory.Produce("passport: ", false)).toString();
                if(passport.length()<7){
                    this.SayError("Длина паспорта должна состоять хотя бы из 7 символов");
                    passport=null;
                }
            }catch (Exception e){
                this.SayError(e.getMessage());
            }
        }

return new Person(name, weight, passport);
    }
}
