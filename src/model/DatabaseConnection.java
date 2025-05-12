package src.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseConnection {

    public static Connection getConnection() {
        try (InputStream input = new FileInputStream("resources/app.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Always return a new connection
            return DriverManager.getConnection(url, user, password);

        } catch (IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
