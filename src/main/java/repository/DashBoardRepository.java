package repository;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import model.dto.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardRepository {

    ObservableList<Item> items = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    public ObservableList<Item> getAllItem() throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM item");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            items.add(
                    new Item(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getString("size"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("qty"),
                            resultSet.getBoolean("isAvailable"),
                            1.0
                    )
            );
        }
        return items;
    }

    public ObservableList<Customer> getAllCustomers() {
        return customers;
    }
}
