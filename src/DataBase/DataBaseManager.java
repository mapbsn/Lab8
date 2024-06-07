package DataBase;

import Managers.PasswordManager;
import Objects.Coordinates;
import Objects.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DataBaseManager {
   private Connection connection;

   public DataBaseManager(String url, Properties info) {
      try {
         connection=DriverManager.getConnection(url, info);
         System.out.println("Соединение с базой данных...");
      } catch (SQLException var4) {
         System.out.println("Ошибка с подключением к базе данных");
      }

   }

   public DataBaseManager() {

   }

   public void initDataBase() {
      this.createUserTable();
      this.createStudyGroupTable();
   }

   public void createUserTable() {
      try {
         Statement statement = connection.createStatement(1000, 1000);
         String sql = "CREATE TABLE IF NOT EXISTS USERS (user_name TEXT PRIMARY KEY UNIQUE,  password TEXT);";
         statement.executeUpdate(sql);
         statement.close();
      } catch (SQLException var3) {
         System.out.println("Ошибка при отправке запроса");
      }

   }

   public void createStudyGroupTable() {
      this.createIdSeq();

      try {
         Statement statement = connection.createStatement(1000, 1000);
         String sql = "CREATE TABLE IF NOT EXISTS StudyGroup (id bigint PRIMARY KEY DEFAULT nextval('ID_SEQ'),name VARCHAR(255) NOT NULL CHECK (name <> ''),study_x DOUBLE PRECISION NOT NULL CHECK(study_x>-108),study_y INT NOT NULL,creationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,countStudents BIGINT CHECK (countStudents > 0),transfer INT CHECK (transfer > 0),forms TEXT NOT NULL,semester TEXT NOT NULL,person TEXT NOT NULL,weight FLOAT CHECK(weight>0),Passport TEXT NOT NULL UNIQUE CHECK(length(Passport) >6),Local_x FLOAT NOT NULL,Local_y BIGINT NOT NULL,Local_name text CHECK(length(Local_name) <836),user_name TEXT,FOREIGN KEY (user_name) REFERENCES users(user_name));";
         statement.executeUpdate(sql);
         statement.close();
      } catch (SQLException var3) {
         System.out.println("Ошибка при отправке запроса");
      }

   }



   public void createIdSeq() {
      try {
         Statement statement = connection.createStatement(1000, 1000);
         String sql = "CREATE SEQUENCE IF NOT EXISTS ID_SEQ START WITH 1 INCREMENT BY 1;";
         statement.executeUpdate(sql);
         statement.close();
      } catch (SQLException var3) {
         System.out.println("Ошибка при отправке запроса");
      }

   }

   public boolean checkUser(String user_name) {
      boolean exists = false;

      try {
         String sql = "SELECT COUNT(*) AS count FROM users WHERE user_name = ?";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, user_name);
         ResultSet resultSet = preparedStatement.executeQuery();
         if (resultSet.next()) {
            int count = resultSet.getInt("count");
            if (count > 0) {
               exists = true;
            }
         }

         resultSet.close();
         preparedStatement.close();
      } catch (SQLException var7) {
         System.out.println("Error with sql");
      }

      return exists;
   }

   public void registerUser(String user_name, String pswd) {
      PreparedStatement preparedStatement = null;

      try {
         String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";
         preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, user_name);
         preparedStatement.setString(2, pswd);
         preparedStatement.executeUpdate();
         System.out.println("User added successfully.");
      } catch (SQLException var13) {
         System.out.println("Error adding user registration");
      } finally {
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } catch (SQLException var12) {
            System.out.println("Error with closing statement");
         }

      }

   }

   public boolean checkPassword(String user_name, String pswd) {
      String sql = "SELECT password FROM Users WHERE user_name = ?";

      try {
         PreparedStatement prepareStatement = connection.prepareStatement(sql);
         prepareStatement.setString(1, user_name);
         ResultSet resultSet = prepareStatement.executeQuery();
         if (resultSet.next()) {
            String hashedPassword = resultSet.getString("password");
            String hashedInputPassword = PasswordManager.hashPassword(pswd);
            prepareStatement.close();
            resultSet.close();
            return hashedInputPassword.equals(hashedPassword);
         } else {
            prepareStatement.close();
            resultSet.close();
            return false;
         }
      } catch (SQLException var8) {
         throw new RuntimeException(var8);
      }
   }

   public ArrayList<String> getUsers() {
      String sql = "SELECT user_name FROM Users;";
      ArrayList users = new ArrayList();

      try {
         PreparedStatement prepareStatement = connection.prepareStatement(sql);
         ResultSet resultSet = prepareStatement.executeQuery();

         while(resultSet.next()) {
            String user = resultSet.getString("user_name");
            users.add(user);
         }

         if (!users.isEmpty()) {
            prepareStatement.close();
            resultSet.close();
            return users;
         } else {
            users.add("There are no users yet...");
            return users;
         }
      } catch (SQLException var6) {
         throw new RuntimeException(var6);
      }
   }

   public TreeSet<StudyGroup> readFromDataBase() {
      TreeSet studyGroups = new TreeSet();

      try {
         String sql = "SELECT id, name, study_x, study_y, countStudents, transfer, forms, semester, person, weight,Passport, Local_x,Local_y,Local_name,user_name FROM StudyGroup";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         ResultSet rs = preparedStatement.executeQuery();

         while(rs.next()) {
            StudyGroup studyGroup = new StudyGroup();
            Coordinates coordinates = new Coordinates();
            Person person = new Person();
            Location location=new Location();
            studyGroup.setId((long)rs.getInt("id"));
            IdManager.AddId((long)rs.getInt("id"));
            studyGroup.setName(rs.getString("name"));
            coordinates.setX(rs.getDouble("study_x"));
            coordinates.setY(rs.getInt("study_y"));
            studyGroup.setCoordinates(coordinates);
            studyGroup.setStudentsCount(rs.getLong("countStudents"));
            studyGroup.setTransferredStudents(rs.getInt("transfer"));
            studyGroup.setFormOfEducation(FormOfEducation.valueOf(rs.getString("forms")));
            studyGroup.setSemesterEnum(Semester.valueOf(rs.getString("semester")));
            person.setName(rs.getString("person"));
            person.setWeight(rs.getFloat("weight"));
            person.setPassportID(rs.getString("Passport"));
            location.setX(rs.getFloat("Local_x"));
            location.setY(rs.getLong("Local_y"));
            location.setName(rs.getString("Local_name"));
            person.setLocation(location);
            studyGroup.setGroupAdmin(person);
            studyGroup.setUser_name(rs.getString("user_name"));
            studyGroups.add(studyGroup);
         }

         rs.close();
         preparedStatement.close();
      } catch (SQLException var8) {
         var8.printStackTrace();
      }

      return studyGroups;
   }

   public void saveToDataBase(TreeSet<StudyGroup> studyGroups, String user_name) {
      StringBuilder sql = new StringBuilder("DELETE FROM StudyGroup WHERE user_name = ?;");
      Iterator var5 = studyGroups.iterator();

      while(var5.hasNext()) {
         StudyGroup studyGroup = (StudyGroup) var5.next();
         if (studyGroup.getUser_name().equals(user_name) && !studyGroup.getUser_name().isEmpty()) {
            String value = getValue(studyGroup);
            sql.append(value);
         }
      }

      try {
         PreparedStatement prepareStatement = connection.prepareStatement(sql.toString());
         prepareStatement.setString(1, user_name);
         prepareStatement.executeQuery();
         prepareStatement.close();
      } catch (SQLException var8) {
         System.out.println("save was successfully");
      }

   }

   private static String getValue(StudyGroup studyGroup) {
      String value = "INSERT INTO StudyGroup(id, name, study_x, study_y, countStudents, transfer, forms, semester, person, weight, Passport, Local_x, Local_y, Local_name, user_name) VALUES";
      value = value + "(" + studyGroup.getId() + ",'" + studyGroup.getName() + "'," + studyGroup.getCoordinates().getX() + "," + studyGroup.getCoordinates().getY() + ","  + studyGroup.getStudentsCount() + "," + studyGroup.getTransferredStudents() + ",'" + studyGroup.getFormOfEducation() + "','" + studyGroup.getSemesterEnum() + "','" + studyGroup.getGroupAdmin().getName()+ "'," + studyGroup.getGroupAdmin().getWeight() + ",'" + studyGroup.getGroupAdmin().getPassportID()+"',"+ studyGroup.getGroupAdmin().getLocation().getX()+","+studyGroup.getGroupAdmin().getLocation().getY()+",'"+studyGroup.getGroupAdmin().getLocation().getName()+"','"+studyGroup.getUser_name()+"');";
      return value;
   }
}
