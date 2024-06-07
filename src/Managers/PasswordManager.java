package Managers;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
   public static String enterPassword() {
      Console console = System.console();
      char[] passwordChars = console.readPassword();
      return new String(passwordChars);
   }

   public static String hashPassword(String pswd) {
      MessageDigest digest;
      try {
         digest = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
      }

      byte[] inputBytes = pswd.getBytes();
      byte[] hashedBytes = digest.digest(inputBytes);
      StringBuilder stringBuilder = new StringBuilder();
      byte[] var5 = hashedBytes;
      int var6 = hashedBytes.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         byte b = var5[var7];
         stringBuilder.append(String.format("%02x", b));
      }

      return stringBuilder.toString();
   }
}
