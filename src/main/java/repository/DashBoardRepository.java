package repository;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Supplier;

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

    public ObservableList<Customer> getAllCustomers() throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            customers.add(
                    new Customer(
                            resultSet.getString("CustID"),
                            resultSet.getString("CustTitle"),
                            resultSet.getString("CustName"),
                            resultSet.getString("CustEmail"),
                            resultSet.getString("CustAddress"),
                            resultSet.getString("City"),
                            resultSet.getString("Province"),
                            resultSet.getString("PostalCode")
                    )
            );
        }
        return customers;
    }

    public void addCustomer(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Customer Values (?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, customer.getId());
        preparedStatement.setString(2, customer.getTitle());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setString(5, customer.getCity());
        preparedStatement.setString(6, customer.getProvince());
        preparedStatement.setString(7, customer.getPostalCode());
        preparedStatement.executeUpdate();
    }

    public void deleteCustomer(String id) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE CustID = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET CustTitle=?, CustName=?, CustEmail=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, customer.getId());
        preparedStatement.setString(2, customer.getTitle());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setString(5, customer.getCity());
        preparedStatement.setString(6, customer.getProvince());
        preparedStatement.setString(7, customer.getPostalCode());
        preparedStatement.executeUpdate();
    }

    public ResultSet getAllSupplier() throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM supplier");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void addSupplier(Supplier supplier) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Supplier VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, supplier.getId());
        preparedStatement.setString(2, supplier.getName());
        preparedStatement.setString(3, supplier.getCompanyName());
        preparedStatement.setString(4, supplier.getAddress());
        preparedStatement.setString(5, supplier.getCity());
        preparedStatement.setString(6, supplier.getProvince());
        preparedStatement.setString(7, supplier.getPostalCode());
        preparedStatement.setString(8, supplier.getPhone());
        preparedStatement.setString(9, supplier.getEmail());
        preparedStatement.executeUpdate();



    }

}
