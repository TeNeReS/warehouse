package arkhipov.warehouse.dao;

import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DAO {
    Connection getConnection() {

        InitialContext context;
        DataSource dataSource = null;

        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup( "java:/comp/env/jdbc/postgres" );
        } catch (NamingException e) {
            System.out.println("Error during data source find!");
            e.printStackTrace();
        }

        Future<Connection> future;
        Connection connection = null;

        if (dataSource != null) {
            try {
                future = dataSource.getConnectionAsync();
                connection = future.get();
                while (!future.isDone()) {
                    System.out.println("Connection is not yet available. Do some background work");
                }
            } catch (SQLException | InterruptedException | ExecutionException e) {
                System.out.println("Error during asynchronous connection!");
                e.printStackTrace();
            }
        }

        return connection;
    }
}
