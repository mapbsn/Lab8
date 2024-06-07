package Factory;

import Objects.*;

import java.io.PrintStream;
import java.util.Scanner;

public class StudyGroupFactory extends GenericFactory {
   private final StringFactory stringFactory;
   private final CoordinatesFactory coordinatesFactory;
   private final IntegerFactory integerFactory;
   private final LongFactory longFactory;
   private final FormOfEducationFactory formOfEducationFactory;
   private final SemesterFactory semesterFactory;
   private final DoubleFactory doubleFactory;
   private final PersonFactory personFactory;
   private final LocationFactory locationFactory;

   public StudyGroupFactory(PrintStream ps, Scanner scanner, boolean stopIfError) {
      super(ps, scanner, stopIfError);
      this.stringFactory = new StringFactory(ps, scanner, stopIfError);
      this.coordinatesFactory = new CoordinatesFactory(ps, scanner, stopIfError);
      this.integerFactory = new IntegerFactory(ps, scanner, stopIfError);
      this.longFactory = new LongFactory(ps, scanner, stopIfError);
      this.semesterFactory = new SemesterFactory(ps, scanner, stopIfError);
      this.doubleFactory = new DoubleFactory(ps, scanner, stopIfError);
      this.formOfEducationFactory=new FormOfEducationFactory(ps, scanner, stopIfError);
      this.personFactory=new PersonFactory(ps, scanner, stopIfError);
      this.locationFactory=new LocationFactory(ps, scanner, stopIfError);
   }

   public StudyGroup Produce(String invitation) throws Exception {
      this.Say(invitation);
      String name = (String)this.stringFactory.Produce("Name of Study Group: ", false);

      long studentsCount;
      for(studentsCount = Math.toIntExact(this.integerFactory.Produce("Students  count: ", false)); studentsCount <= 0; studentsCount = Math.toIntExact((Long)this.integerFactory.Produce("age -> ", false))) {
         if (this.stopIfError) {
            throw new Exception("");
         }

         this.SayError("Количество студентов должно быть больше 0");
      }

      int transferredStudents;
      for(transferredStudents = Math.toIntExact(this.longFactory.Produce("transferred students: ", false)); transferredStudents <= 0L; transferredStudents = Math.toIntExact(this.integerFactory.Produce("wingspan -> ", false))) {
         if (this.stopIfError) {
            throw new Exception("Количество студентов должно быть больше нуля");
         }

         this.SayError("");
      }

      Semester semester = (Semester) this.semesterFactory.Produce("Semester:\n", true);
      FormOfEducation form=(FormOfEducation) this.formOfEducationFactory.Produce("Form of Education:", true);
      Coordinates coordinates = this.coordinatesFactory.Produce("Coordinates:\n", false);
      Person person=this.personFactory.Produce("Person:",false);
      Location location =this.locationFactory.Produce("",false);
      return new StudyGroup(name, coordinates, studentsCount,transferredStudents,form, semester,person,location);
   }
}
