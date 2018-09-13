package arkhipov.warehouse.servlets;

import arkhipov.warehouse.models.Product;
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

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // временное решение
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Integer storeId = Integer.valueOf(req.getParameter("storeId"));
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products WHERE store_id = ?");
            stmt.setInt(1, storeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Product product = new Product();
                product.setId( rs.getInt("id") );
                product.setName( rs.getString("name") );
                product.setProducer( rs.getString("producer") );
                productList.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        req.setAttribute("products", productList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("products.jsp");
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
