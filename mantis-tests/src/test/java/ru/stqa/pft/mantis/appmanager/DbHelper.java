package ru.stqa.pft.mantis.appmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
    }

    public List<String> getUser() {
        List<String> usersData = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                    "user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT USERNAME, EMAIL " + "FROM mantis_user_table " + "WHERE USERNAME != 'administrator'" + "LIMIT 1");
            resultSet.next();
            usersData.add(resultSet.getString("USERNAME"));
            usersData.add(resultSet.getString("EMAIL"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
        }
        return usersData;
    }
}
