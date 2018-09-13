package arkhipov.warehouse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAO {
    Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver not found!");
        }

        String dbUrl = "jdbc:postgresql://localhost:5432/warehouse";
        Properties connectionProps = new Properties();
        connectionProps.put("user", "test_user");
        connectionProps.put("password", "password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, connectionProps);
        } catch (SQLException e) {
            System.out.println("Error during DB connection!");
        }

        return conn;
    }
}
