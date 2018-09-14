package arkhipov.warehouse.dao;

import arkhipov.warehouse.models.Store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO extends DAO {
    public List<Store> getAllStores() {
        List<Store> storeList = new ArrayList<>();

        try (Connection connection = getConnection()){
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
        } catch (SQLException e) {
            System.out.println("Error during execute SQL query!");
            e.printStackTrace();
        }
        return storeList;
    }
}
