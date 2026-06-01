#  Secure Document Management System

A secure and scalable **Document Management System** built with **Spring Boot**, **Spring Security**, and **JWT (JSON Web Token)**.

This project follows a **layered architecture** and best practices to ensure **security, maintainability, and extensibility**.

---
##  Architecture Diagram

> The system architecture is illustrated below:

<img width="1536" height="1024" alt="arch" src="https://github.com/user-attachments/assets/59ef7d35-fcc5-4a90-86e0-652453b7574f" />

---

##  Architecture Overview

The system is designed with a clear separation of concerns:

- **Client Layer**  
  Web / Mobile applications consuming REST APIs

- **API Layer (Entry Point)**  
  Handles incoming requests and routes them to controllers

- **Security Layer (Spring Security + JWT)**  
  Manages authentication and authorization using JWT tokens

- **Controller Layer**  
  Exposes REST endpoints

- **Service Layer**  
  Contains business logic

- **Repository Layer (JPA)**  
  Handles database operations

- **Database Layer**  
  MySQL / PostgreSQL

---

##  Authentication Flow

1. User sends credentials via `/login`
2. User can create an account via `/register`
3. Server authenticates credentials
4. A **JWT token** is generated and returned to the client
5. Client includes the JWT in the `Authorization` header for each request
6. Spring Security filter intercepts requests and validates the token
7. `SecurityContext` is populated with authenticated user details
8. Access is granted or denied based on user roles and permissions

---

##  Security Features

- JWT-based stateless authentication
- Role-based access control (RBAC)
- Secure password hashing (BCrypt)
- Spring Security filter chain
- Protected REST endpoints

---

##  Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL / PostgreSQL
- Maven

---

##  Key Highlights

- Clean layered architecture
- Secure authentication flow
- Scalable backend design
- RESTful API design
- Production-ready structure


##  Future Improvements

- OAuth2 / Google Login integration
- Refresh token mechanism
- rate Limiter ( Bucket rate Limiter) (version 1 for one from scratch and version 2 implement the bucket4j)
- Audit logging system

---

##  License

This project is for educational purposes and can be extended for production use.
