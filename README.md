# Library Management System

🚀 A robust Library Management System built with Spring Boot, Spring Security, JPA, and MySQL. Designed to manage books, members, borrowing transactions, and user roles efficiently.

## 📚 Features

### **User Management**
- **Admin Role**: Full CRUD operations for users.
- **Authentication & Authorization**: Secure login with Spring Security.

### **Book Management**
- **Librarian Role**: Add/update/delete books with details (title, ISBN, year, quantity, etc.).
- **Advanced Metadata**: Cover image URLs, language, summary, and more.

### **Member Management**
- **Staff Role**: Manage members (name, email, address, phone, borrow limits).

### **Borrowing & Returning**
- Automated quantity adjustment on checkout/return.
- Track due dates, fines, and transaction history.

### **Database & Tools**
- MySQL database with Flyway migrations.
- Lombok & MapStruct for clean code.
- Externalized logging for auditing.

## 🛠️ Technologies
- **Backend**: Spring Boot, Spring Data JPA, Spring Security
- **Database**: MySQL, Flyway
- **Utilities**: Lombok, MapStruct
- **Logging**: Logback with file rotation

## 🗄️ Database Schema
| Table | Description |
|-------|-------------|
| `authors` | Author details (name, bio, nationality) |
| `books` | Book metadata + publisher relationship |
| `borrowing_transactions` | Checkout/return records with fines |
| `members` | Member profiles & borrow limits |
| `users` | System user accounts with roles |

## 👥 Roles & Permissions
| Role | Permissions |
|------|-------------|
| **ADMIN** | Manage users |
| **LIBRARIAN** | Manage books, handle checkouts/returns |
| **STAFF** | Manage members, view transactions and return books |

## 🚀 Setup Instructions

### Prerequisites
- JDK 21
- MySQL Server
- Maven/Gradle

1. **Clone Repository**
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
2. **create database schema**
   ```bash
   CREATE DATABASE library_system;
Configure application.properties

2. **Properties**
    ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/library_system
   spring.datasource.username=your_username
   spring.datasource.password=your_password
 - Change it with a proper username and password
 
4. **Run the project**
   
