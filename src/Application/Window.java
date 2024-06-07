package Application;

import DataBase.DataBaseManager;
import Managers.PasswordManager;
import Managers.UserStatusManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

DataBaseManager dataBaseManager=new DataBaseManager();
UserStatusManager userStatusManager=new UserStatusManager();
    public void start() {
      JTextField usernameField;
        JPasswordField passwordField;
        JPasswordField p;
        JFrame frame = new JFrame("Регистрация");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
frame.setSize(400,200);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Логин:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField();
        JLabel passRepeat=new JLabel("Подтвердите пароль:");
        p=new JPasswordField();

        JButton loginButton = new JButton("Войти");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String pa=new String(p.getPassword());
                // Проверка логина и пароля
                if (password.equals(pa)) {
                    dataBaseManager.registerUser(username, PasswordManager.hashPassword(password));
                    userStatusManager.setUser_name(username);
                    userStatusManager.setStatus(true);
                    JOptionPane.showMessageDialog(null, "Успешная регистрация!");
                } else {
                    JOptionPane.showMessageDialog(null, "Пароли не совпадают!");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(passRepeat);
        panel.add(p);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

}