# Clothify Store - KAIA Clothing

A desktop inventory and management application for a clothing store, built with JavaFX and MySQL. A standalone application that automates the operations of a clothing store. The system handles product management, inventory updates, supplier and employee records, order processing, basic reporting and represent a complete user friendly point of sale experience.

## Features

- **Login** - User can login by giving email and password
- **Dashboard** – Overview of store activity and reports
- **Customer Management** – Add and manage customer records (name, contact, email, address)
- **Employee Management** – Track employee details, positions, salaries, and status
- **Inventory Management** – Manage clothing items by category, size, price, and availability
- **Order Processing** – Handle orders with discount and total price calculations
- **Supplier Management** – Maintain supplier information
- **Report** – Basic sales and inventory summary reports

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
