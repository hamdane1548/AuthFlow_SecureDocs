<img width="1536" height="1024" alt="arch" src="https://github.com/user-attachments/assets/59ef7d35-fcc5-4a90-86e0-652453b7574f" />
#  Secure Document Management System

A secure document management system built with **Spring Boot**, **Spring Security**, and **JWT (JSON Web Token)**.  
The project follows a layered architecture to ensure **security, scalability, and maintainability**.

# Architecture Overview

The system is designed with a clean separation of concerns:

- **Client Layer** (Web / Mobile Apps)
- **API Gateway / Entry Point**
- **Spring Security Layer (JWT-based authentication)**
- **Controller Layer (REST APIs)**
- **Service Layer (Business Logic)**
- **Data Access Layer (JPA Repositories)**
- **Database Layer (MySQL / PostgreSQL)**

--- Security Flow

1. User logs in via `/login`
2. User create account via `/register`
3. Server authenticates credentials
4. JWT token is generated and returned
5. Client includes JWT in every request header:
6.  Spring Security filter validates token
6. SecurityContext is populated with authenticated user
7. Access is granted or denied based on roles
