package repository;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import model.dto.Employee;
import model.dto.Item;
import model.dto.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO supplier VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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

    public void deleteSupplier(String id) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM supplier WHERE supplier_id = ?");
        preparedStatement.setString(1 ,id);
        preparedStatement.executeUpdate();
    }

    public void updateSupplier(Supplier supplier) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE supplier SET name=?, company_name=?, address=?, city=?, province=?, postal_code=?, phone=?, email=? WHERE supplier_id=?");
        preparedStatement.setString(9, supplier.getId());
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getCompanyName());
        preparedStatement.setString(3, supplier.getAddress());
        preparedStatement.setString(4, supplier.getCity());
        preparedStatement.setString(5, supplier.getProvince());
        preparedStatement.setString(6, supplier.getPostalCode());
        preparedStatement.setString(7, supplier.getPhone());
        preparedStatement.setString(8, supplier.getEmail());
        preparedStatement.executeUpdate();
    }

    public ResultSet getAllEmployees() throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM employee");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO employee (id, name, nic, dob, position, salary, contact_number, address, joined_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, employee.getId());
        preparedStatement.setString(2, employee.getName());
        preparedStatement.setString(3, employee.getNic());
        preparedStatement.setString(4, employee.getDob());
        preparedStatement.setString(5, employee.getPosition());
        preparedStatement.setDouble(6, employee.getSalary());
        preparedStatement.setString(7, employee.getContactNumber());
        preparedStatement.setString(8, employee.getAddress());
        preparedStatement.setString(9, employee.getJoinedDate());
        preparedStatement.setString(10, employee.getStatus());
        preparedStatement.executeUpdate();
    }

    public void deleteEmployee(String id) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM employee WHERE id = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    public void  updateEmployee(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE employee SET name=?, nic=?, dob=?, position=?, salary=?, contact_number=?, address=?, joined_date=?, status=? WHERE id=? ");
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getNic());
        preparedStatement.setString(3, employee.getDob());
        preparedStatement.setString(4, employee.getPosition());
        preparedStatement.setDouble(5, employee.getSalary());
        preparedStatement.setString(6, employee.getContactNumber());
        preparedStatement.setString(7, employee.getAddress());
        preparedStatement.setString(8, employee.getJoinedDate());
        preparedStatement.setString(9, employee.getStatus());
        preparedStatement.setString(10, employee.getId());
        preparedStatement.executeUpdate();
    }

    public void addNewItem(Item item) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO item VALUES (?,?,?,?,?,?,?)");
        preparedStatement.setString(1, item.getId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getCategory());
        preparedStatement.setString(4, item.getSize());
        preparedStatement.setDouble(5, item.getPrice());
        preparedStatement.setInt(6, item.getQty());
        preparedStatement.setBoolean(7, item.getAvailable());
        preparedStatement.executeUpdate();
    }

    public void deleteItem(String id) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM item WHERE id = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    public void updateItem(Item item) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE item SET name=?, category=?, size=?, price=?, qty=?, isAvailable=? WHERE id=?");
        preparedStatement.setString(1,item.getName());
        preparedStatement.setString(2,item.getCategory());
        preparedStatement.setString(3,item.getSize());
        preparedStatement.setDouble(4,item.getPrice());
        preparedStatement.setInt(5,item.getQty());
        preparedStatement.setBoolean(6,item.getAvailable());
        preparedStatement.setString(7, item.getId());
        preparedStatement.executeUpdate();
    }

    public boolean addOrder(String orderId, String id, java.sql.Date date) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO orders (OrderID, CustID, OrderDate) VALUES (?, ?, ?)");
        preparedStatement.setString(1,orderId);
        preparedStatement.setString(2,id);
        preparedStatement.setDate(3,date);
        return preparedStatement.executeUpdate()>0;
    }

    public boolean addOrderDetails(ObservableList<Item> placeOrders, double discount, String orderId) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO orderdetails (OrderID, ItemID, Qty, Discount) VALUES (?, ?, ?, ?)");
        for (Item item : placeOrders){
            preparedStatement.setString(1,orderId);
            preparedStatement.setString(2, item.getId());
            preparedStatement.setInt(3, item.getQty());
            preparedStatement.setDouble(4,discount);
            preparedStatement.addBatch();
        }
        int[] results = preparedStatement.executeBatch();
        for (int res : results) {
            if (res <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean changeStock(ObservableList<Item> placeOrders) throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE item SET qty = qty - ? WHERE id = ?");
        for (Item item : placeOrders){
            preparedStatement.setInt(1,item.getQty());
            preparedStatement.setString(2,item.getId());
            preparedStatement.addBatch();
        }
        int[] results = preparedStatement.executeBatch();
        for (int res : results) {
            if (res <= 0) {
                return false;
            }
        }
        return true;
    }

    public ResultSet getAllOrder() throws SQLException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM orders");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public Boolean login(String userName, String password) throws SQLException {
        PreparedStatement pst = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Login WHERE username = ? AND password = ?");
        pst.setString(1, userName);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public ResultSet getOrdersByDate(LocalDate value) throws SQLException {
        PreparedStatement pst = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM orders WHERE OrderDate = ?");
        pst.setString(1, String.valueOf(value));
        return pst.executeQuery();
    }
}
