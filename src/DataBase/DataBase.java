package DataBase;

import Objects.*;

import java.util.*;
import java.util.stream.Collectors;

public class DataBase {
   private TreeSet<StudyGroup> studyGroups;
   private final DataBaseManager dataBaseManager;

   public DataBase(DataBaseManager dataBaseManager) throws Exception {
      this.dataBaseManager = dataBaseManager;
      this.studyGroups = new TreeSet();
      this.loadFromFile();
   }

   private void loadFromFile() {
      try {
         this.studyGroups = this.dataBaseManager.readFromDataBase();
         this.updateIds();
      } catch (Exception var2) {
         System.out.println("Error loading from file: " + var2.getMessage());
      }

   }


   public void save(String user_name) {
      this.dataBaseManager.saveToDataBase(this.studyGroups, user_name);
   }

   private void updateIds() {
      long id = 1L;
      IdManager.ListID.clear();

      for (Iterator var3 = this.studyGroups.iterator(); var3.hasNext(); ++id) {
         StudyGroup studyGroup = (StudyGroup) var3.next();
         studyGroup.setId(id);
         IdManager.AddId(id);
      }

   }

   public void Add(StudyGroup studyGroup) {
      studyGroup.setId(IdManager.GetNewId());
      IdManager.AddId(studyGroup.getId());
      this.studyGroups.add(studyGroup);
   }

   public void Update(long id, StudyGroup updatedStudyGroup, String username) {
      Iterator iterator = this.studyGroups.iterator();

      while(iterator.hasNext()) {
         StudyGroup studyGroup = (StudyGroup) iterator.next();
         if (studyGroup.getId() == id) {
            if (studyGroup.getUser_name().equals(username)) {
               iterator.remove();
               updatedStudyGroup.setId(id);
               updatedStudyGroup.setUser_name(studyGroup.getUser_name());
               this.studyGroups.add(updatedStudyGroup);
               return;
            }

            System.out.println("You don't have access to this ID");
         }
      }

      System.out.println("Study Group with id " + id + " not found.");
   }

   public void RemoveById(long id, String username) {
      Iterator<StudyGroup> iterator = this.studyGroups.iterator();
      boolean removeCheck = false;
      while (iterator.hasNext()) {
         StudyGroup studyGroup = (StudyGroup) iterator.next();
         if (studyGroup.getId() == id) {
            if (studyGroup.getUser_name().equals(username)) {
               IdManager.RemoveId(studyGroup.getId());
               iterator.remove();
               this.updateIds();
            } else {
               System.out.println("You don't have access to this ID");
            }

            removeCheck = true;
            break;
         }
      }

      if (!removeCheck) {
         System.out.println("No study group with ID = " + id);
      }

   }

   public void RemoveLower(String username) {
      Iterator iterator = this.studyGroups.iterator();

      while (iterator.hasNext()) {
         StudyGroup studyGroup = (StudyGroup) iterator.next();
         if (studyGroup.getStudentsCount() < studyGroups.stream().mapToLong(StudyGroup::getStudentsCount).max().orElse(studyGroup.getStudentsCount())) {
            if (studyGroup.getUser_name().equals(username)) {
               IdManager.RemoveId(studyGroup.getId());
               iterator.remove();
               this.updateIds();
            }
         }
      }
   }


   public void semType() {
      HashSet<Semester> Sem = new HashSet<>() {
      };
      for (StudyGroup studyGroup : studyGroups) {
         Sem.add(studyGroup.getSemesterEnum());
      }
      System.out.println("Unique Values:" + Sem);
   }

   public void transfer() {
      ArrayList<Integer> transfStusents = new ArrayList<>();
      for (StudyGroup studyGroup : studyGroups) {
         transfStusents.add(studyGroup.getTransferredStudents());
         Collections.sort(transfStusents, Collections.reverseOrder());
      }
      System.out.println("transferred students:" + transfStusents);
   }

   public List<StudyGroup> getSortedCollection() {
      List<StudyGroup> sortedList = studyGroups.stream()
              .sorted(Comparator.comparing(StudyGroup::getName))
              .collect(Collectors.toList());
      for (int i = 0; i < sortedList.size(); i++) {
         sortedList.get(i).setId((long) (i + 1));
      }
      return sortedList;
   }

   public void Clear(String userName) {
      Iterator iterator = this.studyGroups.iterator();

      while (iterator.hasNext()) {
         StudyGroup studyGroup = (StudyGroup) iterator.next();
         if (studyGroup.getUser_name().equals(userName)) {
            IdManager.RemoveId(studyGroup.getId());
            iterator.remove();
         }
      }

      this.updateIds();
   }

   public String Info() {
      return String.format("Collection type: %s\nNumber of elements: %d", this.studyGroups.getClass().getName(), this.studyGroups.size());
   }

   public ArrayList<String> getUsers() {
      return this.dataBaseManager.getUsers();
   }

   public String toString() {
      StringBuilder show = new StringBuilder(String.format("There are %d Study Group in the collection.\n", this.studyGroups.size()));
      this.studyGroups.forEach((dragon) -> {
         show.append(dragon).append("\n\n");
      });
      return show.toString();
   }


   public void AddIfMax(StudyGroup studyGroup) throws IllegalArgumentException {

      if (this.studyGroups.isEmpty()) {
         studyGroup.setId(IdManager.GetNewId());
         IdManager.AddId(studyGroup.getId());
         this.studyGroups.add(studyGroup);
      } else {
         StudyGroup maxSG = (StudyGroup) Collections.max(this.studyGroups, new StudentsCountComparator());
         if ((new StudentsCountComparator()).compare(studyGroup, maxSG) > 0) {
            studyGroup.setId(IdManager.GetNewId());
            IdManager.AddId(studyGroup.getId());
            this.studyGroups.add(studyGroup);
         } else {
            throw new IllegalArgumentException("Error: This Study Group is not maximum!");
         }
      }
   }
   }



