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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }
}
