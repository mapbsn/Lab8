package Application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StartWindow {
    public void create(){
        JFrame frame=new JFrame("Студенческая группа");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Изменение");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("remove_lower");
        JMenuItem m12 = new JMenuItem("add_if_max");
        JMenuItem m13=new JMenuItem("add");
        JMenuItem m14=new JMenuItem("clear");
        JMenuItem m15=new JMenuItem("remove_by_id");
        JMenuItem m16=new JMenuItem("update id");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m1.add(m15);
        m1.add(m16);
        JMenu m2 = new JMenu("Информация");
        mb.add(m2);
        JMenuItem m21 = new JMenuItem("help");
        JMenuItem m22 = new JMenuItem("info");
        JMenuItem m23 = new JMenuItem("history");
        JMenuItem m24 = new JMenuItem("save");
        JMenuItem m25 = new JMenuItem("group");
        JMenuItem m26 = new JMenuItem("transfer");
        JMenuItem m27 = new JMenuItem("print_unique");
        m2.add(m21);
        m2.add(m22);
        m2.add(m23);
        m2.add(m24);
        m2.add(m25);
        m2.add(m26);
        m2.add(m27);
        JMenu m3 = new JMenu("Пользователь");
        mb.add(m3);
        JMenuItem m31 = new JMenuItem("login");
        JMenuItem m32 = new JMenuItem("register");
        JMenuItem m33 = new JMenuItem("logout");
        m3.add(m31);
        m3.add(m32);
        m3.add(m33);
        JMenu m4 = new JMenu("Завершение работы");
        JMenuItem m41=new JMenuItem("exit");
        mb.add(m4);
        m4.add(m41);
        JMenu m5 = new JMenu("Языки");
        mb.add(m5);
        JMenuItem m51 = new JMenuItem("RUS");
        JMenuItem m52 = new JMenuItem("TR");
        JMenuItem m53 = new JMenuItem("LIT");
        JMenuItem m54 = new JMenuItem("ES-CO");
        m5.add(m51);
        m5.add(m52);
        m5.add(m53);
        m5.add(m54);
        frame.add(BorderLayout.NORTH,mb);
        m32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window=new Window();
                window.start();
            }
        });
        m41.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // Устанавливаем параметры подключения к базе данных
        String url = "jdbc:postgresql://146.185.211.205:5432/dbstud";
        String user = "itmo381957_2024";
        String password = "itmo381957";

        try {
            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Выполняем SQL-запрос для получения данных из таблицы
            ResultSet resultSet = statement.executeQuery("SELECT * FROM StudyGroup");

            // Создаем модель данных для JTable
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            DefaultTableModel model = new DefaultTableModel();

            // Добавляем имена столбцов в модель
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Добавляем строки с данными в модель
            Object[] rowData = new Object[columnCount];
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
            }

            // Создаем JTable с полученными данными и добавляем его на форму
           JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
m13.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
});
            // Закрываем ресурсы
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
}
