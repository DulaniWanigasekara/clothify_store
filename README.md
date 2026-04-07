# Clothify Store

A desktop inventory and management application for a clothing store, built with JavaFX and MySQL.

## Features

- **Customer Management** – Add and manage customer records (name, contact, address)
- **Employee Management** – Track employee details, positions, salaries, and status
- **Inventory Management** – Manage clothing items by category, size, price, and availability
- **Order Processing** – Handle orders with discount and total price calculations
- **Supplier Management** – Maintain supplier information
- **Dashboard** – Overview of store activity and reports

## Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| JavaFX | 19 |
| MySQL Connector/J | 9.3.0 |
| Lombok | 1.18.40 |
| Build Tool | Maven |

## Prerequisites

- Java 17+
- MySQL database server
- Maven 3.6+

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/DulaniWanigasekara/clothify_store.git
   cd clothify_store
   ```

2. **Set up the database**  
   Create a MySQL database and update the connection settings in `src/main/java/dbConnection/DBConnection.java`.

3. **Build and run**
   ```bash
   mvn clean javafx:run
   ```

## Project Structure

```
src/main/java/
├── Main.java                  # Entry point
├── Starter.java               # JavaFX application launcher
├── controller/                # JavaFX controllers
├── dbConnection/              # Database connection
├── model/dto/                 # Data Transfer Objects (Customer, Employee, Item, Order, Supplier)
├── repository/                # Database access layer
└── service/                   # Business logic layer
src/main/resources/
├── view/                      # FXML UI layouts
└── image/                     # Application images
```

## License

This project is for educational purposes.
