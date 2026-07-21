# 🔐 ShopSharper - Auth Service

A secure authentication and authorization microservice for the **ShopSharper E-Commerce Platform** built using **Spring Boot**, **Spring Security**, **JWT**, **JPA**, and **MySQL**.

This service is responsible for user registration, login, JWT token generation, authentication, and role-based authorization.

---

## 🚀 Features

- User Registration
- User Login
- JWT Authentication
- Role-Based Authorization
- Password Encryption using BCrypt
- Spring Security Integration
- RESTful APIs
- MySQL Database Integration
- Exception Handling
- DTO Pattern
- Clean Layered Architecture

---

## 🛠 Tech Stack

### Backend
- Java 8 and 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Token)
- Maven

### Database
- MySQL

### Tools
- IntelliJ IDEA
- Postman
- Git & GitHub

---

## 📂 Project Structure

```
src
 ├── main
 │   ├── java
 │   │   └── com.shopsharper.auth_service
 │   │        ├── config
 │   │        ├── controller
 │   │        ├── custom
 │   │        ├── dto
 │   │        ├── entity
 │   │        ├── enums
 │   │        ├── mapper
 │   │        ├── repository
 │   │        ├── security
 │   │        ├── service
 │   │        └── AuthServiceApplication
 │   │
 │   └── resources
 │        ├── static
 │        ├── templates
 │        └── application.properties
 │
 └── test
```

---

## 🏗 Architecture

```
Client
   │
   ▼
REST Controller
   │
   ▼
Service Layer
   │
   ▼
Repository
   │
   ▼
MySQL Database

        ▲
        │
Spring Security
        │
JWT Filter
        │
Authentication Manager
```

---

## 🔑 Authentication Flow

1. User registers with email and password.
2. Password is encrypted using BCrypt.
3. User logs in with credentials.
4. Spring Security authenticates the user.
5. JWT token is generated.
6. Client stores the token.
7. Token is sent in the Authorization header for protected APIs.
8. JWT Filter validates the token for every request.

---

## 🔐 Security Features

- JWT Authentication
- Stateless Session Management
- Password Encryption (BCrypt)
- AuthenticationManager
- SecurityFilterChain
- Custom JWT Filter
- UserDetailsService
- Role-Based Authorization

---

## 📦 Main Packages

### config
Contains Spring Security configuration.

### controller
REST APIs for authentication.

### service
Business logic.

### repository
Database operations using Spring Data JPA.

### entity
JPA Entity classes.

### dto
Request and Response DTOs.

### mapper
Converts Entity ↔ DTO.

### security
JWT Service, JWT Filter, UserDetailsService, Security Components.

### custom
Custom Exceptions and Utility Classes.

### enums
Application enums such as Roles.

---

## 📌 REST APIs

### Register User

```
POST /api/auth/register
```

Request

```json
{
  "name": "Rohit",
  "email": "rohit@gmail.com",
  "password": "password123"
}
```

---

### Login

```
POST /api/auth/login
```

Request

```json
{
  "email": "rohit@gmail.com",
  "password": "password123"
}
```

Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### Protected API

```
GET /api/users/profile
```

Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## ⚙ Configuration

Configure your database in:

```
application.properties
```

Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopsharper_auth
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶ Running the Project

### Clone Repository

```bash
  git clone https://github.com/yourusername/auth-service.git
```

### Go to Project

```bash
  cd auth-service
```

### Build

```bash
  mvn clean install
```

### Run

```bash
  mvn spring-boot:run
```

or

Run **AuthServiceApplication.java**

---

## 🧪 Testing APIs

Use **Postman** to test:

- Register
- Login
- JWT Authentication
- Protected APIs

---

## 📚 Concepts Used

- Spring Boot
- Spring Security
- JWT Authentication
- AuthenticationManager
- SecurityFilterChain
- UserDetailsService
- BCryptPasswordEncoder
- Spring Data JPA
- Hibernate
- DTO Pattern
- Exception Handling
- REST API Development
- Dependency Injection
- Layered Architecture

---


## 👨‍💻 Author

**Rohit Kumar Pandit**

Java Backend Developer

### Skills

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate
- JPA
- MySQL
- REST APIs
- Redis
- Kafka
- Docker
- Microservices

---

## ⭐ If you found this project useful, don't forget to star the repository.