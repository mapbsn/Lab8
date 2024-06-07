package Factory;

import Objects.Location;

import java.io.PrintStream;
import java.util.Scanner;

public class LocationFactory extends GenericFactory {
    private final FloatFactory floatFactory;
    private final LongFactory longFactory;
    private final StringFactory stringFactory;

    public LocationFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
        super(ps, scanner, stopIfError);
        this.floatFactory = new FloatFactory(ps, scanner, stopIfError);
        this.longFactory = new LongFactory(ps, scanner, stopIfError);
        this.stringFactory = new StringFactory(ps, scanner, stopIfError);
    }

    public Location Produce(String invitation, boolean mayBeEmpty) {
        this.Say(invitation);
        Float x = null;
        while (x == null) {
            try {
                x = (this.floatFactory.Produce("Location x: ", mayBeEmpty)).floatValue();
                if (x < -1000) {
                    this.SayError("Координата должна быть больше -1000");
                    x = null;
                }
            } catch (Exception e) {
                this.SayError(e.getMessage());
            }
        }
        Long y=null;
        while (y==null){
            try {
                y=(this.longFactory.Produce("Location y: ",mayBeEmpty)).longValue();
                if(x<-1000){
                    this.SayError("Координата не может быть меньше -1000");
                    y=null;
                }
            }catch (Exception e){
                this.SayError(e.getMessage());
            }
        }
        String name=null;
        while(name==null){
            try {
                name=(this.stringFactory.Produce("Name of Location: ",false)).toString();
                if(name.length()>836){
                    this.SayError("Длина имени не должна превышать 836 символов");
                    name=null;
                }
            }catch (Exception e){
                this.SayError(e.getMessage());
            }
        }
        return new Location(x,y, name);
    }
}
