package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.dto.*;
import service.DashBoardService;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    // ------------------ NAVIGATION BUTTONS ----------
    public Button btnHome;
    public Button btnCustomer;
    public Button btnOrder;
    public Button btnSupplier;
    public Button btnHistory;
    public Button btnItem;
    public Button btnSetting;
    public Button btnReport;
    public Button btnEmployee;

    // ------------------ PANES -----------------
    public AnchorPane loginPane;
    public AnchorPane homePane;
    public AnchorPane customerPane;
    public AnchorPane supplierPane;
    public AnchorPane orderPane;
    public AnchorPane orderCustomerPane;
    public AnchorPane employeePane;
    public AnchorPane itemPane;
    public AnchorPane historyPane;
    public AnchorPane reportPane;
    public AnchorPane settingPane;

    // --------------------LOGIN --------------------
    public TextField txtUserName;
    public PasswordField txtPassword;

    // ----------------- CUSTOMER -------------
    public TableView<Customer> tblCustomer;
    public TableColumn<Customer, String> colCustomerId;
    public TableColumn<Customer, String> colCustomerName;
    public TableColumn<Customer, String> colCustomerDOB;
    public TableColumn<Customer, String> colCustomerTitle;
    public TableColumn<Customer, String> colCustomerAddress;
    public TableColumn<Customer, String> colCustomerCity;
    public TableColumn<Customer, String> colCustomerProvince;
    public TableColumn<Customer, String> colCustomerPostalCode;

    public TextField txtCustomerId;
    public ChoiceBox<String> txtCustomerTitle;
    public TextField txtCustomerName;
    public DatePicker txtCustomerDate;
    public TextField txtCustomerAddress;
    public TextField txtCustomerCity;
    public TextField txtCustomerProvince;
    public TextField txtCustomerPostalCode;

    // -------------------------- SUPPLIER ---------------------
    public TableView<Supplier> tblSupplier;
    public TableColumn<Supplier, String> colSupplierId;
    public TableColumn<Supplier, String> colSupplierName;
    public TableColumn<Supplier, String> colSupplierCompanyName;
    public TableColumn<Supplier, String> colSupplierAddress;
    public TableColumn<Supplier, String> colSupplierCity;
    public TableColumn<Supplier, String> colSupplierProvince;
    public TableColumn<Supplier, String> colSupplierPostalCode;
    public TableColumn<Supplier, String> colSupplierPhone;
    public TableColumn<Supplier, String> colSupplierEmail;

    public TextField txtSupplierId;
    public TextField txtSupplierName;
    public TextField txtSupplierCompanyName;
    public TextField txtSupplierAddress;
    public TextField txtSupplierCity;
    public TextField txtSupplierProvince;
    public TextField txtSupplierPostalCode;
    public TextField txtSupplierPhone;
    public TextField txtSupplierEmail;

    // --------------------- ORDER ----------------
    public TableView<Item>  tblOrder;       // item catalogue (top)
    public TableColumn<Item, String> colOrderId;
    public TableColumn<Item, String> colOrderName;
    public TableColumn<Item, String> colOrderSize;
    public TableColumn<Item, Integer> colOrderQty;
    public TableColumn<Item, Double>  colOrderPrice;

    public TableView<Item>  tblOrder1;      // cart (bottom)
    public TableColumn<Item, String> colOrderName1;
    public TableColumn<Item, String> colOrderSize1;
    public TableColumn<Item, Integer> colOrderQty1;
    public TableColumn<Item, Double>  colOrderPrice1;
    public TableColumn<Item, Double>  colOrderTotalPrice1;

    public TextField txtOrderSearch;
    public TextField txtOrderName;
    public TextField txtOrderPrice;
    public TextField txtOrderQty;
    public TextField txtDiscountField;

    public Label lblDate;
    public Label lblTime;
    public Label lblDateName;
    public Label lblOrderItem;
    public Label lblOrderSubTotal;
    public Label lblOrderTotal;
    public Label lblOrderCustomerId;
    public Label lblOrderCustomerName;

    // ------------------- ORDER-CUSTOMER SELECTOR ----------------
    public TableView<Customer> tblOrderCustomer;
    public TableColumn<Customer, String> colOrderCustomerId;
    public TableColumn<Customer, String> colOrderCustomerName;
    public TableColumn<Customer, String> colOrderCustomerDOB;
    public TableColumn<Customer, String> colOrderCustomerTitle;
    public TableColumn<Customer, String> colOrderCustomerAddress;
    public TableColumn<Customer, String> colOrderCustomerCity;
    public TableColumn<Customer, String> colOrderCustomerProvince;
    public TableColumn<Customer, String> colOrderCustomerPostalCode;

    // -------------------- EMPLOYEE -----------------------
    public TableView<Employee> tblEmployee;
    public TableColumn<Employee, String> colEmployeeId;
    public TableColumn<Employee, String> colEmployeeName;
    public TableColumn<Employee, String> colEmployeeDOB;
    public TableColumn<Employee, String> colEmployeeNIC;
    public TableColumn<Employee, Double> colEmployeeSalary;
    public TableColumn<Employee, String> colEmployeeAddress;
    public TableColumn<Employee, String> colEmployeePosition;
    public TableColumn<Employee, String> colEmployeePhone;
    public TableColumn<Employee, String> colEmployeeJoinedDate;
    public TableColumn<Employee, String> colEmployeeStatus;

    public TextField txtEmployeeId;
    public TextField txtEmployeeName;
    public DatePicker txtEmployeeDOB;
    public TextField txtEmployeeNic;
    public TextField txtEmployeeSalary;
    public TextField txtEmployeeAddress;
    public TextField txtEmployeePhone;
    public TextField txtEmployeePosition;
    public TextField txtEmployeeStatus;
    public DatePicker txtEmployeeJoinedDate;

    // ------------- ITEM --------------------
    public TableView<Item> tblItem;
    public TableColumn<Item, String>  colItemId;
    public TableColumn<Item, String>  colItemName;
    public TableColumn<Item, String>  colItemCategory;
    public TableColumn<Item, String>  colItemSize;
    public TableColumn<Item, Double>  colItemPrice;
    public TableColumn<Item, Integer> colItemQty;
    public TableColumn<Item, Boolean> colItemIsAvailable;

    public TextField txtItemId;
    public TextField txtItemName;
    public TextField txtItemCategory;
    public TextField txtItemSize;
    public TextField txtItemPrice;
    public TextField txtItemQty;
    public CheckBox  txtItemIsAvailable;

    // ---------------- HISTORY -------------
    public TableView<Report> tblHistory;
    public TableColumn<Report, String> colHistoryOrderId;
    public TableColumn<Report, String> colHistoryCustomerId;
    public TableColumn<Report, String> colHistoryName;

    public TableView<Order> tblHistoryItem;
    public TableColumn<Order, String> colHistoryItemName;
    public TableColumn<Order, Integer> colHistoryItemQty;
    public TableColumn<Order, Double>  colHistoryItemPrice;
    public TableColumn<Order, Double>  colHistoryItemDiscountPrice;
    public TableColumn<Order, Double>  colHistoryItemTotalPrice;

    public DatePicker dateHistory;

    // ------------------ SERVICE & STATE --------------
    private final DashBoardService service = new DashBoardService();

    // Live list references — kept so we can mutate them without re-fetching
    private ObservableList<Customer>  customerList;
    private ObservableList<Supplier>  supplierList;
    private ObservableList<Employee>  employeeList;
    private ObservableList<Item>      itemList;
    private ObservableList<Item>      cartList;

    // Currently selected customer for the order flow
    private Customer selectedOrderCustomer;


    //  INITIALIZE

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupCustomerTitle();
        setupCustomerTable();
        setupSupplierTable();
        setupEmployeeTable();
        setupItemTable();
        setupOrderTable();
        setupHistoryTable();
        startClock();

        // Show only the login pane on startup
        showOnlyPane(loginPane);
    }

    //  NAV BUTTON ACTIONS
    public void btnHomeAction() {
        showOnlyPane(homePane);
    }

    public void btnCustomerAction() {
        customerList = service.getAllCustomer();
        tblCustomer.setItems(customerList);
        showOnlyPane(customerPane);
    }

    public void btnSupplierAction() {
        supplierList = service.getAllSupplier();
        tblSupplier.setItems(supplierList);
        showOnlyPane(supplierPane);
    }

    public void btnOrderAction() {
        // First select the customer, then navigate to the order pane
        customerList = service.getAllCustomer();
        tblOrderCustomer.setItems(customerList);
        showOnlyPane(orderCustomerPane);
    }

    public void btnEmployeeAction() {
        employeeList = service.getAllEmployee();
        tblEmployee.setItems(employeeList);
        showOnlyPane(employeePane);
    }

    public void btnItemAction() {
        itemList = service.getAllNewItem();
        tblItem.setItems(itemList);
        showOnlyPane(itemPane);
    }

    public void bthHistoryAction() {
        showOnlyPane(historyPane);
    }

    public void btnReportAction() {
        showOnlyPane(reportPane);
    }

    public void btnSettingAction() {
        showOnlyPane(settingPane);
    }

    //  LOGIN
    public void btnuserName() {
        txtPassword.requestFocus();
    }

    public void btnpasswordAction() {
        btnLoginAction();
    }

    public void btnLoginAction() {
        String username = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter both username and password.").show();
            return;
        }

        if (service.login(username, password)) {
            // Unlock all nav buttons after successful login
            setNavButtonsDisabled(false);
            showOnlyPane(homePane);
            txtUserName.clear();
            txtPassword.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password.").show();
            txtPassword.clear();
        }
    }

    //  CUSTOMER CRUD
    public void btnCustomerAddAction() {
        Customer customer = buildCustomerFromForm();
        if (customer == null) return;
        service.addCustomer(customer, customerList);
        clearCustomerForm();
    }

    public void btnCustomerDeleteAction() {
        Customer selected = tblCustomer.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer to delete.").show();
            return;
        }
        service.deleteCustomer(selected, customerList);
        clearCustomerForm();
    }

    public void btnCustomerUpdateAction() {
        Customer customer = buildCustomerFromForm();
        if (customer == null) return;
        service.updateCustomer(customer, customerList);
        clearCustomerForm();
    }

    public void btnCustomerResetAction() {
        clearCustomerForm();
        tblCustomer.getSelectionModel().clearSelection();
    }

    private Customer buildCustomerFromForm() {
        String id    = txtCustomerId.getText().trim();
        String title = txtCustomerTitle.getValue();
        String name  = txtCustomerName.getText().trim();
        String dob   = txtCustomerDate.getValue() != null
                ? txtCustomerDate.getValue().toString() : "";
        String addr  = txtCustomerAddress.getText().trim();
        String city  = txtCustomerCity.getText().trim();
        String prov  = txtCustomerProvince.getText().trim();
        String post  = txtCustomerPostalCode.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Customer ID and Name are required.").show();
            return null;
        }
        // email stored in dob field for this model (FXML has no email text field in customer pane)
        return new Customer(id, title, name, dob, addr, city, prov, post);
    }

    private void clearCustomerForm() {
        txtCustomerId.clear();
        txtCustomerTitle.setValue(null);
        txtCustomerName.clear();
        txtCustomerDate.setValue(null);
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtCustomerPostalCode.clear();
    }

    //  SUPPLIER CRUD

    public void btnSupplierAddAction() {
        Supplier supplier = buildSupplierFromForm();
        if (supplier == null) return;
        service.addSupplier(supplier, supplierList);
        clearSupplierForm();
    }

    public void btnSupplierDeleteAction() {
        Supplier selected = tblSupplier.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a supplier to delete.").show();
            return;
        }
        service.deleteSupplier(selected.getId(), supplierList);
        clearSupplierForm();
    }

    public void btnSupplierUpdateAction() {
        Supplier supplier = buildSupplierFromForm();
        if (supplier == null) return;
        service.updateSupplier(supplier, supplierList);
        clearSupplierForm();
    }

    public void btnSupplierResetAction() {
        clearSupplierForm();
        tblSupplier.getSelectionModel().clearSelection();
    }

    private Supplier buildSupplierFromForm() {
        String id      = txtSupplierId.getText().trim();
        String name    = txtSupplierName.getText().trim();
        String company = txtSupplierCompanyName.getText().trim();
        String addr    = txtSupplierAddress.getText().trim();
        String city    = txtSupplierCity.getText().trim();
        String prov    = txtSupplierProvince.getText().trim();
        String post    = txtSupplierPostalCode.getText().trim();
        String phone   = txtSupplierPhone.getText().trim();
        String email   = txtSupplierEmail.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Supplier ID and Name are required.").show();
            return null;
        }
        return new Supplier(id, name, company, addr, city, prov, post, phone, email);
    }

    private void clearSupplierForm() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierCompanyName.clear();
        txtSupplierAddress.clear();
        txtSupplierCity.clear();
        txtSupplierProvince.clear();
        txtSupplierPostalCode.clear();
        txtSupplierPhone.clear();
        txtSupplierEmail.clear();
    }

    //  EMPLOYEE CRUD

    public void btnEmployeeAddAction() {
        Employee employee = buildEmployeeFromForm();
        if (employee == null) return;
        service.addEmployee(employee, employeeList);
        clearEmployeeForm();
    }

    public void btnEmployeeDeleteAction() {
        Employee selected = tblEmployee.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an employee to delete.").show();
            return;
        }
        service.deleteEmployee(selected, employeeList);
        clearEmployeeForm();
    }

    public void btnEmployeeUpdateAction() {
        Employee employee = buildEmployeeFromForm();
        if (employee == null) return;
        service.updateEmployee(employee, employeeList);
        clearEmployeeForm();
    }

    public void btnEmployeeResetAction() {
        clearEmployeeForm();
        tblEmployee.getSelectionModel().clearSelection();
    }

    private Employee buildEmployeeFromForm() {
        String id       = txtEmployeeId.getText().trim();
        String name     = txtEmployeeName.getText().trim();
        String nic      = txtEmployeeNic.getText().trim();
        String dob      = txtEmployeeDOB.getValue() != null
                ? txtEmployeeDOB.getValue().toString() : "";
        String position = txtEmployeePosition.getText().trim();
        String salaryStr = txtEmployeeSalary.getText().trim();
        String phone    = txtEmployeePhone.getText().trim();
        String address  = txtEmployeeAddress.getText().trim();
        String joined   = txtEmployeeJoinedDate.getValue() != null
                ? txtEmployeeJoinedDate.getValue().toString() : "";
        String status   = txtEmployeeStatus.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Employee ID and Name are required.").show();
            return null;
        }
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Salary must be a number.").show();
            return null;
        }
        return new Employee(id, name, nic, dob, position, salary, phone, address, joined, status);
    }

    private void clearEmployeeForm() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeNic.clear();
        txtEmployeeDOB.setValue(null);
        txtEmployeeSalary.clear();
        txtEmployeeAddress.clear();
        txtEmployeePhone.clear();
        txtEmployeePosition.clear();
        txtEmployeeStatus.clear();
        txtEmployeeJoinedDate.setValue(null);
    }


    //  ITEM CRUD
    public void btnItemAddAction() {
        Item item = buildItemFromForm();
        if (item == null) return;
        service.addNewItem(item, itemList);
        clearItemForm();
    }

    public void btnItemDeleteAction() {
        Item selected = tblItem.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an item to delete.").show();
            return;
        }
        service.deleteItem(selected, itemList);
        clearItemForm();
    }

    public void btnItemUpdateAction() {
        Item item = buildItemFromForm();
        if (item == null) return;
        service.updateItem(item, itemList);
        clearItemForm();
    }

    public void btnItemResetAction() {
        clearItemForm();
        tblItem.getSelectionModel().clearSelection();
    }

    private Item buildItemFromForm() {
        String id       = txtItemId.getText().trim();
        String name     = txtItemName.getText().trim();
        String category = txtItemCategory.getText().trim();
        String size     = txtItemSize.getText().trim();
        String priceStr = txtItemPrice.getText().trim();
        String qtyStr   = txtItemQty.getText().trim();
        boolean avail   = txtItemIsAvailable.isSelected();

        if (id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Item ID and Name are required.").show();
            return null;
        }
        double price = 0;
        int qty = 0;
        try {
            price = Double.parseDouble(priceStr);
            qty   = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Price and Qty must be valid numbers.").show();
            return null;
        }
        return new Item(id, name, category, size, price, qty, avail, 0);
    }

    private void clearItemForm() {
        txtItemId.clear();
        txtItemName.clear();
        txtItemCategory.clear();
        txtItemSize.clear();
        txtItemPrice.clear();
        txtItemQty.clear();
        txtItemIsAvailable.setSelected(false);
    }

        //  ORDER FLOW

    /** Customer selector — called when "Order 👉" is clicked */
    public void btnCustomerSelectOrderAction() {
        Customer selected = tblOrderCustomer.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer first.").show();
            return;
        }
        selectedOrderCustomer = selected;
        lblOrderCustomerId.setText(selected.getId());
        lblOrderCustomerName.setText(selected.getName());

        // Load catalogue into the top order table
        itemList = service.getAllNewItem();
        tblOrder.setItems(itemList);

        // Clear the cart
        cartList = service.cancelOrder();
        tblOrder1.setItems(cartList);

        updateOrderSummary();
        showOnlyPane(orderPane);
    }

    /** Search bar on the order pane — filter items by typing customer ID
     *  (repurposed here to search items by name) */
    public void txtOrderSearchAction() {
        btnOrderSearchAction();
    }

    public void btnOrderSearchAction() {
        String keyword = txtOrderSearch.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            tblOrder.setItems(itemList);
            return;
        }
        ObservableList<Item> filtered = FXCollections.observableArrayList();
        for (Item item : itemList) {
            if (item.getName().toLowerCase().contains(keyword)
                    || item.getId().toLowerCase().contains(keyword)) {
                filtered.add(item);
            }
        }
        tblOrder.setItems(filtered);
    }

    /** Add item to the cart */
    public void btnAddOrderAction() {
        Item selected = tblOrder.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an item from the catalogue.").show();
            return;
        }
        String qtyText = txtOrderQty.getText().trim();
        if (qtyText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a quantity.").show();
            return;
        }
        int qty;
        try {
            qty = Integer.parseInt(qtyText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Quantity must be a whole number.").show();
            return;
        }
        cartList = service.addItem(selected, qty);
        tblOrder1.setItems(cartList);
        updateOrderSummary();
        txtOrderQty.clear();
    }

    /** Remove selected cart item */
    public void btnDeleteOrderAction() {
        Item selected = tblOrder1.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a cart item to remove.").show();
            return;
        }
        service.deleteOrder(selected);
        updateOrderSummary();
    }

    /** Update quantity of selected cart item */
    public void btnUpdateOrderAction() {
        Item selected = tblOrder1.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a cart item to update.").show();
            return;
        }
        String qtyText = txtOrderQty.getText().trim();
        if (qtyText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter the new quantity.").show();
            return;
        }
        try {
            int qty = Integer.parseInt(qtyText);
            service.updateOrder(selected, qty);
            updateOrderSummary();
            txtOrderQty.clear();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Quantity must be a whole number.").show();
        }
    }

    /** Reset / clear the cart */
    public void btnResetOrderAction() {
        cartList = service.cancelOrder();
        tblOrder1.setItems(cartList);
        updateOrderSummary();
        txtOrderQty.clear();
        txtDiscountField.setText("0");
    }

    public void txtDiscountFieldAction() {
        updateOrderSummary();
    }

    public void btnPlaceOrder() {
        if (selectedOrderCustomer == null) {
            new Alert(Alert.AlertType.WARNING, "No customer selected.").show();
            return;
        }
        if (cartList == null || cartList.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Cart is empty.").show();
            return;
        }
        double discount = 0;
        try {
            discount = Double.parseDouble(txtDiscountField.getText().trim());
        } catch (NumberFormatException ignored) {}

        String today = LocalDate.now().toString();
        service.placeOrder(cartList, selectedOrderCustomer.getId(), discount, today);

        // Reset after placing
        cartList = service.cancelOrder();
        tblOrder1.setItems(cartList);
        updateOrderSummary();
        txtDiscountField.setText("0");

        // Reload item catalogue to reflect updated stock
        itemList = service.getAllNewItem();
        tblOrder.setItems(itemList);
    }

    public void btnCancelOrder() {
        cartList = service.cancelOrder();
        tblOrder1.setItems(cartList);
        updateOrderSummary();
        txtDiscountField.setText("0");
        showOnlyPane(orderCustomerPane);
    }

    private void updateOrderSummary() {
        if (cartList == null) return;
        int itemCount = cartList.size();
        double subTotal = 0;
        for (Item item : cartList) {
            subTotal += item.getTotal();
        }
        double discount = 0;
        try {
            discount = Double.parseDouble(txtDiscountField.getText().trim());
        } catch (NumberFormatException ignored) {}

        double total = subTotal - (subTotal * discount / 100);

        lblOrderItem.setText(itemCount + (itemCount == 1 ? " Item" : " Items"));
        lblOrderSubTotal.setText(String.format("Rs.%.2f", subTotal));
        lblOrderTotal.setText(String.format("Rs.%.2f", total));
    }

        //  HISTORY

    public void btnSearchDataHistoryAction() {
        LocalDate date = dateHistory.getValue();
        if (date == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a date.").show();
            return;
        }
        ObservableList<Report> reports = service.getOrdersByDate(date);
        tblHistory.setItems(reports);

        // When a row is selected, load its items
        tblHistory.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, selected) -> {
                    if (selected != null) {
                        ObservableList<Order> details =
                                service.getOrderItems(selected.getOrderId());
                        tblHistoryItem.setItems(details);
                    }
                });
    }

    //  TABLE SETUP HELPERS

    private void setupCustomerTable() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerDOB.setCellValueFactory(new PropertyValueFactory<>("email")); // mapped to DOB/email field
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustomerProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        // Fill form when a row is clicked
        tblCustomer.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selected) -> {
                    if (selected != null) {
                        txtCustomerId.setText(selected.getId());
                        txtCustomerTitle.setValue(selected.getTitle());
                        txtCustomerName.setText(selected.getName());
                        txtCustomerAddress.setText(selected.getAddress());
                        txtCustomerCity.setText(selected.getCity());
                        txtCustomerProvince.setText(selected.getProvince());
                        txtCustomerPostalCode.setText(selected.getPostalCode());
                    }
                });

        // Customer order selector table
        colOrderCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colOrderCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOrderCustomerDOB.setCellValueFactory(new PropertyValueFactory<>("email"));
        colOrderCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOrderCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colOrderCustomerProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colOrderCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
    }

    private void setupSupplierTable() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupplierCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colSupplierProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colSupplierPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colSupplierPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selected) -> {
                    if (selected != null) {
                        txtSupplierId.setText(selected.getId());
                        txtSupplierName.setText(selected.getName());
                        txtSupplierCompanyName.setText(selected.getCompanyName());
                        txtSupplierAddress.setText(selected.getAddress());
                        txtSupplierCity.setText(selected.getCity());
                        txtSupplierProvince.setText(selected.getProvince());
                        txtSupplierPostalCode.setText(selected.getPostalCode());
                        txtSupplierPhone.setText(selected.getPhone());
                        txtSupplierEmail.setText(selected.getEmail());
                    }
                });
    }

    private void setupEmployeeTable() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmployeeNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmployeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmployeePhone.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmployeeJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        colEmployeeStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selected) -> {
                    if (selected != null) {
                        txtEmployeeId.setText(selected.getId());
                        txtEmployeeName.setText(selected.getName());
                        txtEmployeeNic.setText(selected.getNic());
                        txtEmployeeSalary.setText(String.valueOf(selected.getSalary()));
                        txtEmployeeAddress.setText(selected.getAddress());
                        txtEmployeePhone.setText(selected.getContactNumber());
                        txtEmployeePosition.setText(selected.getPosition());
                        txtEmployeeStatus.setText(selected.getStatus());
                        if (selected.getDob() != null && !selected.getDob().isEmpty()) {
                            txtEmployeeDOB.setValue(LocalDate.parse(selected.getDob()));
                        }
                        if (selected.getJoinedDate() != null && !selected.getJoinedDate().isEmpty()) {
                            txtEmployeeJoinedDate.setValue(LocalDate.parse(selected.getJoinedDate()));
                        }
                    }
                });
    }

    private void setupItemTable() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemIsAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));

        tblItem.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selected) -> {
                    if (selected != null) {
                        txtItemId.setText(selected.getId());
                        txtItemName.setText(selected.getName());
                        txtItemCategory.setText(selected.getCategory());
                        txtItemSize.setText(selected.getSize());
                        txtItemPrice.setText(String.valueOf(selected.getPrice()));
                        txtItemQty.setText(String.valueOf(selected.getQty()));
                        txtItemIsAvailable.setSelected(selected.getAvailable());
                    }
                });
    }

    private void setupOrderTable() {
        // Catalogue table (top)
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOrderSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Fill name/price from catalogue selection
        tblOrder.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, selected) -> {
                    if (selected != null) {
                        txtOrderName.setText(selected.getName());
                        txtOrderPrice.setText(String.valueOf(selected.getPrice()));
                    }
                });

        // Cart table (bottom)
        colOrderName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOrderSize1.setCellValueFactory(new PropertyValueFactory<>("size"));
        colOrderQty1.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderPrice1.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOrderTotalPrice1.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void setupHistoryTable() {
        colHistoryOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colHistoryCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colHistoryName.setCellValueFactory(new PropertyValueFactory<>("name"));

        colHistoryItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colHistoryItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colHistoryItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colHistoryItemDiscountPrice.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));
        colHistoryItemTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void setupCustomerTitle() {
        txtCustomerTitle.setItems(FXCollections.observableArrayList(
                "Mr.", "Mrs.", "Ms.", "Dr.", "Prof."
        ));
    }


    //  CLOCK

    private void startClock() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalDate now  = LocalDate.now();
            LocalTime time = LocalTime.now();
            lblDate.setText(now.format(dateFormatter));
            lblTime.setText(time.format(timeFormatter));
            lblDateName.setText(days[now.getDayOfWeek().getValue() % 7]);
        }));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

     //  PANE / NAV HELPERS

    private void showOnlyPane(AnchorPane target) {
        AnchorPane[] allPanes = {
                loginPane, homePane, customerPane, supplierPane,
                orderPane, orderCustomerPane, employeePane, itemPane,
                historyPane, reportPane, settingPane
        };
        for (AnchorPane pane : allPanes) {
            pane.setVisible(pane == target);
        }
    }

    private void setNavButtonsDisabled(boolean disabled) {
        btnHome.setDisable(disabled);
        btnCustomer.setDisable(disabled);
        btnOrder.setDisable(disabled);
        btnSupplier.setDisable(disabled);
        btnHistory.setDisable(disabled);
        btnItem.setDisable(disabled);
        btnSetting.setDisable(disabled);
        btnReport.setDisable(disabled);
        btnEmployee.setDisable(disabled);
    }
}
