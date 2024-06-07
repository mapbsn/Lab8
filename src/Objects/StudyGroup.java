package Objects;

import java.sql.Date;

public class StudyGroup implements Comparable<StudyGroup> {
   private Long id;
   private String name;
   private Coordinates coordinates;
   private java.util.Date creationDate;
   private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
   private int transferredStudents; //Значение поля должно быть больше 0
   private FormOfEducation formOfEducation; //Поле не может быть null
   private Semester semesterEnum; //Поле не может быть null
   private Person groupAdmin; //Поле может быть null
   private String user_name;//Поле не может быть null


   public StudyGroup(String name, Coordinates coordinates, Long studentsCount, int transferredStudents, FormOfEducation formOfEducation, Semester semester, Person groupAdmin, Location location) {
      this.name = name;
      this.coordinates = coordinates;
      this.creationDate = new java.util.Date();
      this.studentsCount=studentsCount;
      this.transferredStudents = transferredStudents;
      this.formOfEducation = formOfEducation;
      this.semesterEnum = semester;
      this.groupAdmin = groupAdmin;
this.groupAdmin.setLocation(location);
   }

   public StudyGroup() {
   }

   public int compareTo(StudyGroup other) {
      return Long.compare(this.id, other.id);
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Coordinates getCoordinates() {
      return this.coordinates;
   }

   public void setCoordinates(Coordinates coordinates) {
      this.coordinates = coordinates;
   }

   public java.util.Date getCreationDate() {
      return this.creationDate;
   }

   public void setCreationDate(Date creationDate) {
      this.creationDate = creationDate;
   }

   public Long getStudentsCount() {
      return this.studentsCount;
   }

   public void setStudentsCount(long studentsCount) {
      this.studentsCount = studentsCount;
   }

   public String getUser_name() {
      return this.user_name;
   }

   public void setUser_name(String user_name) {
      this.user_name = user_name;
   }

   public int getTransferredStudents() {
      return this.transferredStudents;
   }

   public void setTransferredStudents(int transferredStudents) {
      this.transferredStudents = transferredStudents;
   }


   public Person getGroupAdmin() {
      return this.groupAdmin;
   }

   public void setGroupAdmin(Person groupAdmin) {
      this.groupAdmin = groupAdmin;
   }

   public Semester getSemesterEnum() {
      return this.semesterEnum;
   }

   public void setSemesterEnum(Semester semester) {
      this.semesterEnum = semester;
   }

   public FormOfEducation getFormOfEducation() {
      return this.formOfEducation;
   }

   public void setFormOfEducation(FormOfEducation formOfEducation) {
      this.formOfEducation = formOfEducation;
   }



   public String toString() {
      return String.format("USER = %s\nStudy Group id = %d\nName = %s\n Stud_X = %f\n Stud_Y = %d\n Count of students = %d\n transferred students = %d\nSemester = %s\n Form of Education = %s\nName of Group Admin = %s\n Weight = %f\nPassport_id = %s\n L_X = %f\nL_Y = %d\nName Location = %s\nCreation date = %s", this.user_name, this.id, this.name, this.coordinates.getX(), this.coordinates.getY(), this.studentsCount, this.transferredStudents, this.semesterEnum, this.formOfEducation, this.groupAdmin.getName(), this.groupAdmin.getWeight(), this.groupAdmin.getPassportID(), this.groupAdmin.getLocation().getX(), this.groupAdmin.getLocation().getY(),this.groupAdmin.getLocation().getName(), this.creationDate);
   }
}
