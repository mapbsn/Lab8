package Objects;

public class Person {
   private String name; //Поле не может быть null, Строка не может быть пустой
   private Float weight; //Поле может быть null, Значение поля должно быть больше 0
   private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 7, Поле не может быть null
   private  Location location; //Поле не может быть null
   public Person(String name, Float weight, String passportID){
      this.name=name;
      this.weight=weight;
      this.passportID=passportID;
   }
   public Person(){
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Float getWeight() {
      return this.weight;
   }

   public String getPassportID() {
      return this.passportID;
   }

   public void setPassportID(String passportID) {
      this.passportID = passportID;
   }

   public void setWeight(Float weight) {
      this.weight = weight;
   }

   public Location getLocation() {
      return this.location;
   }

   public void setLocation(Location location) {
      this.location = location;
   }
}