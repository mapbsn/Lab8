package DataBase;

import java.util.HashSet;
import java.util.Set;

public class IdManager {
   public static Set<Long> ListID = new HashSet();
   static long current_id = 1L;

   public static void AddId(Long id) {
      ListID.add(id);
   }

   public static void RemoveId(Long id) {
      ListID.remove(id);
   }

   public static Long GetNewId() {
      for(current_id = 1L; ListID.contains(current_id); ++current_id) {
      }

      return current_id;
   }
}
