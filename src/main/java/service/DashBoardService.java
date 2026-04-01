package service;

import dbConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.*;
import repository.DashBoardRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardService {

    DashBoardRepository dashBoardRepository = new DashBoardRepository();

    ObservableList<Item> orderItems = FXCollections.observableArrayList();

    //----------------- Order Cart -------------------

    public ObservableList<Item> getAllItem() {
        return orderItems;
    }

    public ObservableList<Item> addItem(Item selectedItem, int orderQty) {
        if (selectedItem.getQty() >= orderQty) {
            if (searchOrderItem(selectedItem, orderQty)) return orderItems;
            selectedItem.setQty(orderQty);
            selectedItem.setTotal(selectedItem.getPrice() * orderQty);
            orderItems.add(selectedItem);
            return orderItems;
        }
        new Alert(Alert.AlertType.INFORMATION, "Not enough stock available!").show();
        return orderItems;
    }

    public boolean searchOrderItem(Item selectedItem, int qty) {
        for (Item orderItem : orderItems) {
            if (orderItem.getId().equals(selectedItem.getId())) {
                int newQty = orderItem.getQty() + qty;
                orderItem.setQty(newQty);
                orderItem.setTotal(orderItem.getPrice() * newQty);
                return true;
            }
        }
        return false;
    }

    public void deleteOrder(Item selectedItem) {
        orderItems.removeIf(item -> item.getId().equals(selectedItem.getId()));
    }

    public void updateOrder(Item selectedItem, int qty) {
        for (Item item : orderItems) {
            if (item.getId().equals(selectedItem.getId())) {
                item.setQty(qty);
                item.setTotal(item.getPrice() * qty);
                break;
            }
        }
    }

    public ObservableList<Item> cancelOrder() {
        orderItems.clear();
        return orderItems;
    }

    //-------------------Customer -----------------------

    public ObservableList<Customer> getAllCustomer() {
        ObservableList<Customer> customers = FXCollections.observableArrayList(); // ✅ local
        try {
            customers = dashBoardRepository.getAllCustomers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return customers;
    }

    public void addCustomer(Customer customer, ObservableList<Customer> customers) {
        try {
            dashBoardRepository.addCustomer(customer);
            customers.add(customer);
            new Alert(Alert.AlertType.INFORMATION, "Customer added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteCustomer(Customer customer, ObservableList<Customer> customers) {
        try {
            dashBoardRepository.deleteCustomer(customer.getId());
            customers.removeIf(c -> c.getId().equals(customer.getId()));
            new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateCustomer(Customer customer, ObservableList<Customer> customers) {
        try {
            dashBoardRepository.updateCustomer(customer);
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId().equals(customer.getId())) {
                    customers.set(i, customer);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    // --------------Supplier-------------------------------------

    public ObservableList<Supplier> getAllSupplier() {
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(); // ✅ local
        try {
            ResultSet rs = dashBoardRepository.getAllSupplier();
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getString("supplier_id"),
                        rs.getString("name"),
                        rs.getString("company_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return suppliers;
    }

    public void addSupplier(Supplier supplier, ObservableList<Supplier> suppliers) {
        try {
            dashBoardRepository.addSupplier(supplier);
            suppliers.add(supplier);
            new Alert(Alert.AlertType.INFORMATION, "Supplier added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteSupplier(String id, ObservableList<Supplier> suppliers) {
        try {
            dashBoardRepository.deleteSupplier(id);
            suppliers.removeIf(s -> s.getId().equals(id));
            new Alert(Alert.AlertType.INFORMATION, "Supplier deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateSupplier(Supplier supplier, ObservableList<Supplier> suppliers) {
        try {
            dashBoardRepository.updateSupplier(supplier);
            for (int i = 0; i < suppliers.size(); i++) {
                if (suppliers.get(i).getId().equals(supplier.getId())) {
                    suppliers.set(i, supplier);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Supplier updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    // ---------------------------Employee---------------------

    public ObservableList<Employee> getAllEmployee() {
        ObservableList<Employee> employees = FXCollections.observableArrayList(); // ✅ local
        try {
            ResultSet rs = dashBoardRepository.getAllEmployees();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("nic"),
                        rs.getString("dob"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getString("contact_number"),
                        rs.getString("address"),
                        rs.getString("joined_date"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return employees;
    }

    public void addEmployee(Employee employee, ObservableList<Employee> employees) {
        try {
            dashBoardRepository.addEmployee(employee);
            employees.add(employee);
            new Alert(Alert.AlertType.INFORMATION, "Employee added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteEmployee(Employee employee, ObservableList<Employee> employees) {
        try {
            dashBoardRepository.deleteEmployee(employee.getId());
            employees.remove(employee);
            new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateEmployee(Employee employee, ObservableList<Employee> employees) {
        try {
            dashBoardRepository.updateEmployee(employee);
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getId().equals(employee.getId())) {
                    employees.set(i, employee);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Employee updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    // -------------------item--------------------------------------

    public ObservableList<Item> getAllNewItem() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try {
            items = dashBoardRepository.getAllItem();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return items;
    }

    public void addNewItem(Item item, ObservableList<Item> items) {
        try {
            dashBoardRepository.addNewItem(item);
            items.add(item);
            new Alert(Alert.AlertType.INFORMATION, "Item added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteItem(Item selectedItem, ObservableList<Item> items) {
        try {
            dashBoardRepository.deleteItem(selectedItem.getId());
            items.remove(selectedItem);
            new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateItem(Item item, ObservableList<Item> items) {
        try {
            dashBoardRepository.updateItem(item);
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(item.getId())) {
                    items.set(i, item);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    // ----------------orders-----------------------------------

    private String getOrderId() {
        try {
            ResultSet rs = dashBoardRepository.getLastOrderId();
            if (rs.next()) {
                String lastId = rs.getString("OrderID"); // e.g. "ORD007"
                int number = Integer.parseInt(lastId.replaceAll("[^0-9]", "")) + 1;
                return String.format("ORD%03d", number);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Could not generate Order ID: " + e.getMessage()).show();
        }
        return "ORD001"; // default for first order
    }

    public void placeOrder(ObservableList<Item> placeOrders, String custId,
                           double discount, String date) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String orderId = getOrderId();

            if (!dashBoardRepository.addOrder(orderId, custId, Date.valueOf(date))) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to create order record.").show();
                return;
            }

            if (!dashBoardRepository.addOrderDetails(placeOrders, discount, orderId)) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save order details.").show();
                return;
            }

            if (!dashBoardRepository.changeStock(placeOrders)) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to update stock.").show();
                return;
            }

            connection.commit();
            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully! ID: " + orderId).show();

        } catch (SQLException e) {
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException ex) { /* ignore */ }
            new Alert(Alert.AlertType.ERROR, "Order failed: " + e.getMessage()).show();
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "DB reset failed: " + e.getMessage()).show();
            }
        }
    }

    // -------------History-------------------------------------------

    public ObservableList<Report> getOrdersByDate(java.time.LocalDate date) {
        ObservableList<Report> reports = FXCollections.observableArrayList();
        try {
            ResultSet rs = dashBoardRepository.getOrdersByDate(date);
            while (rs.next()) {
                reports.add(new Report(
                        rs.getString("OrderID"),
                        rs.getString("CustID"),
                        rs.getString("CustID") // name resolved separately in controller
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return reports;
    }

    public ObservableList<Order> getOrderItems(String orderId) {
        ObservableList<Order> orderDetails = FXCollections.observableArrayList();
        try {
            ResultSet rs = dashBoardRepository.getAllOrderItem(orderId);
            while (rs.next()) {
                String itemId = rs.getString("ItemID");
                int qty = rs.getInt("Qty");
                double discount = rs.getDouble("Discount");

                ResultSet itemRs = dashBoardRepository.getItem(itemId);
                if (itemRs.next()) {
                    double price = itemRs.getDouble("price");
                    String name = itemRs.getString("name");
                    double discountedPrice = price - (price * discount / 100);
                    double totalPrice = discountedPrice * qty;
                    orderDetails.add(new Order(name, qty, price, discountedPrice, totalPrice));
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return orderDetails;
    }

    public boolean login(String username, String password) {
        try {
            return dashBoardRepository.login(username, password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            return false;
        }
    }
}
