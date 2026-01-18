package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import model.dto.Item;

public class DashBoardRepository {

    ObservableList<Item> items = FXCollections.observableArrayList();
    ObservableList<Customer> customers = FXCollections.observableArrayList();
}
