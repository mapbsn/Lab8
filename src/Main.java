import Application.StartWindow;
import Application.Window;
import DataBase.DataBase;
import DataBase.DataBaseManager;
import Managers.CommandManager;
import Managers.UserStatusManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

public class Main extends JFrame {
    public static void main(String[] args) {
       StartWindow startWindow=new StartWindow();
       startWindow.create();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://146.185.211.205:5432/dbstud";
            Properties info = new Properties();
            info.load(new FileInputStream(args[0]));
            DataBaseManager dataBaseManager = new DataBaseManager(url, info);
            DataBase db = new DataBase(dataBaseManager);
            dataBaseManager.initDataBase();
            UserStatusManager userStatusManager = new UserStatusManager(false, "");
            CommandManager commandManager = new CommandManager(userStatusManager, dataBaseManager, db, new Scanner(System.in), false);
            commandManager.Run();
        } catch (Exception e) {
            System.out.println("");
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Exiting...");
        }
    }
}