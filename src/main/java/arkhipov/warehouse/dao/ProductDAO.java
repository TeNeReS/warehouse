package arkhipov.warehouse.dao;

import arkhipov.warehouse.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAO {
    public List<Product> getAllProductByStoreID(Integer storeId) {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = getConnection()) {
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
        } catch (SQLException e) {
            System.out.println("Error during execute SQL query!");
            e.printStackTrace();
        }
        return productList;
    }
}
