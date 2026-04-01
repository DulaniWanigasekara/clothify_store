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
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
    ObservableList<Employee> employees = FXCollections.observableArrayList();
    ObservableList<Item> items = FXCollections.observableArrayList();
    ObservableList<Report> reports = FXCollections.observableArrayList();
    ObservableList<Order> orders = FXCollections.observableArrayList();

    public ObservableList<Item> getAllItem() {
        return orderItems;
    }

    public ObservableList<Item> addItem(Item selectedItem, int orderQty) {
        if (selectedItem.getQty() >= orderQty) {
            if (searchOrderItem(selectedItem, orderQty)) {
                return orderItems;
            }
            selectedItem.setQty(orderQty);
            selectedItem.setTotal((selectedItem.getPrice()) * orderQty);
            orderItems.add(selectedItem);
            return orderItems;
        }
        new Alert(Alert.AlertType.INFORMATION, "Not Available stock!").show();
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
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getId().equals(selectedItem.getId())) {
                orderItems.remove(i);
                break;
            }
        }
    }

    public void updateOrder(Item selectedItem, int qty) {
        for (Item item : orderItems) {
            if ((item.getId()).equals((selectedItem.getId()))) {
                item.setQty(qty);
                item.setTotal((item.getPrice()) * qty);
            }
        }
    }

    public ObservableList<Customer> getAllCustomer() {
        try {
            customers = dashBoardRepository.getAllCustomers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return customers;
    }


    public void addCustomer(Customer customer) {
        try {
            dashBoardRepository.addCustomer(customer);
            customers.add(customer);
            new Alert(Alert.AlertType.INFORMATION, "Customer Added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteCustomer(Customer customer) {
        try {
            dashBoardRepository.deleteCustomer(customer.getId());
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId().equals(customer.getId())) {
                    customers.remove(i);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            dashBoardRepository.updateCustomer(customer);
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId().equals(customer.getId())) {
                    customers.get(i).setTitle(customer.getTitle());
                    customers.get(i).setName(customer.getName());
                    customers.get(i).setEmail(customer.getEmail());
                    customers.get(i).setAddress(customer.getAddress());
                    customers.get(i).setCity(customer.getCity());
                    customers.get(i).setProvince(customer.getProvince());
                    customers.get(i).setPostalCode(customer.getPostalCode());
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public ObservableList<Supplier> getAllSupplier() {
        try {
            ResultSet allSupplier = dashBoardRepository.getAllSupplier();
            while (allSupplier.next()) {
                suppliers.add(new Supplier(
                        allSupplier.getString("supplier_id"),
                        allSupplier.getString("name"),
                        allSupplier.getString("company_name"),
                        allSupplier.getString("address"),
                        allSupplier.getString("city"),
                        allSupplier.getString("province"),
                        allSupplier.getString("postal_code"),
                        allSupplier.getString("phone"),
                        allSupplier.getString("email")
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return suppliers;
    }

    public void addSupplier(Supplier supplier) {
        try {
            dashBoardRepository.addSupplier(supplier);
            suppliers.add(supplier);
            new Alert(Alert.AlertType.INFORMATION, "Supplier Added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteSupplier(String id) {
        try {
            dashBoardRepository.deleteSupplier(id);
            for (int i = 0; i < suppliers.size(); i++) {
                if (suppliers.get(i).getId().equals(id)) {
                    suppliers.remove(i);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateSupplier(Supplier supplier) {
        try {
            dashBoardRepository.updateSupplier(supplier);
            for (int i = 0; i < suppliers.size(); i++) {
                if (suppliers.get(i).getId().equals(supplier.getId())) {
                    suppliers.set(i, supplier);
                    break;
                }
            }
            new Alert(Alert.AlertType.INFORMATION, "Supplier Update successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public ObservableList<Employee> getAllEmployee() {
        try {
            ResultSet resultSet = dashBoardRepository.getAllEmployees();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("dob"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("address"),
                        resultSet.getString("joined_date"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        try {
            dashBoardRepository.addEmployee(employee);
            employees.add(employee);
            new Alert(Alert.AlertType.INFORMATION, "employee Added successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteEmployee(Employee selectedItem) {
        try {
            dashBoardRepository.deleteEmployee(selectedItem.getId());
            employees.remove(selectedItem);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            dashBoardRepository.updateEmployee(employee);
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getId().equals(employee.getId())) {
                    employees.set(i, employee);
                    break;
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void addNewItem(Item item) {
        try {
            dashBoardRepository.addNewItem(item);
            new Alert(Alert.AlertType.INFORMATION, "Item Added successfully!").show();
            items.add(item);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void deleteItem(Item selectedItem) {
        try {
            dashBoardRepository.deleteItem(selectedItem.getId());
            items.remove(selectedItem);
            new Alert(Alert.AlertType.INFORMATION, "Item Deleted successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    public void updateItem(Item item) {
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

    public ObservableList<Item> getAllNewItem() {
        try {
            items = dashBoardRepository.getAllItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public ObservableList<Item> cancelOrder() {
        orderItems.clear();
        return orderItems;
    }

    public void placeOrder(ObservableList<Item> placeOrders, String id, double discount, String date) {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String orderId = getOrderId();

            if (!dashBoardRepository.addOrder(orderId, id, Date.valueOf(date))) {
                connection.rollback();
                return;
            }

            if (!dashBoardRepository.addOrderDetails(placeOrders, discount, orderId)) {
                connection.rollback();
                return;
            }

            if (!dashBoardRepository.changeStock(placeOrders)) {
                connection.rollback();
                return;
            }

            connection.commit();
            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to place order!").show();
            }
        }
    }

}
