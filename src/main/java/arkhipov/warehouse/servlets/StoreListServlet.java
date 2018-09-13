package arkhipov.warehouse.servlets;

import arkhipov.warehouse.models.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/stores")
public class StoreListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // временное решение
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Store> storeList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM stores");
            while (rs.next())
            {
                Store store = new Store();
                store.setId( rs.getInt("id") );
                store.setName( rs.getString("name") );
                store.setAddress( rs.getString("address") );
                storeList.add(store);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        req.setAttribute("stores", storeList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("stores.jsp");
        requestDispatcher.forward(req, resp);
    }

    private Connection getConnection()
    {

        String dbUrl = "jdbc:postgresql://localhost:5432/warehouse";
        Properties connectionProps = new Properties();
        connectionProps.put("user", "test_user");
        connectionProps.put("password", "password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, connectionProps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
