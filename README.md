# Smart Wallet & Expense Management - Backend

A powerful, secure, and modern Digital Wallet backend built with **Spring Boot 3**, **Spring Security 6**, and **MySQL**.

## Features

- **JWT Authentication**: Secure stateless session management.
- **Simplified Registration**: Quick signup with just a username and password.
- **Wallet Core**:
    - Add funds with automatic balance updates.
    - Real-time balance inquiry.
- **Expense Tracking**:
    - Record income and expenses with detailed categories.
    - Chronological transaction history.
- **Security First**: 
    - Password hashing using BCrypt.
    - CORS-enabled for seamless frontend integration.

##  Tech Stack

- **Framework**: Spring Boot 3.4.3
- **Database**: MySQL
- **Security**: Spring Security & JJWT
- **Persistence**: Spring Data JPA
- **Language**: Java 17+

## Getting Started

### Prerequisites

- JDK 17 or higher
- MySQL Server

### 1. Database Configuration

Create a database named `wallet_db` in MySQL and update the `src/main/resources/application.properties` file with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/wallet_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2. Run the Application

You can run the application directly from your IDE or using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The server will start on [http://localhost:8080](http://localhost:8080).

## 🔌 API Endpoints

### Auth
- `POST /api/auth/signup` - Register a new user
- `POST /api/auth/signin` - Login and receive JWT

### Wallet
- `GET /api/wallet/balance` - Get current balance (Auth Required)
- `POST /api/wallet/add-funds` - Add money to wallet (Auth Required)

### Transactions
- `POST /api/transactions/add` - Record income/expense (Auth Required)
- `GET /api/transactions/history` - Get transaction history (Auth Required)

## Project Structure

```
src/main/java/com/example/walletBackend/
├── controller   # REST Endpoints
├── dto          # Data Transfer Objects (Requests/Responses)
├── entity       # JPA Database Models
├── repository   # Data Access Layer
└── security     # JWT & Spring Security Logic
```

---
Developed as part of the **Smart Wallet & Expense Management System**.
