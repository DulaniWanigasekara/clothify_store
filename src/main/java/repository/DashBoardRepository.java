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

    public ObservableList<Item> getAllItem() {
        return items;
    }
}
