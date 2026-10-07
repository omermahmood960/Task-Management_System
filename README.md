# Task Management System

A backend **Task Management System** built with **Java, Spring Boot, PostgreSQL, Hibernate, and Spring Security**. The application provides RESTful APIs for managing users, projects, and tasks, with secure authentication and authorization.

## 🚀 Project Overview

The Task Management System is a secure REST API for managing users, projects, and tasks. It was built to demonstrate real-world Java Spring Boot backend development, including:

- REST API development
- Database integration
- Exception handling
- Authentication and authorization

## 🛠️ Technologies Used

| Technology      | Purpose                          |
|-----------------|----------------------------------|
| Java 21         | Programming language             |
| Spring Boot     | Backend framework                |
| Spring Security | Authentication and authorization |
| JWT             | Token-based authentication       |
| Hibernate       | ORM (object-relational mapping)  |
| PostgreSQL      | Database                         |
| Maven           | Build and dependency management  |
| Postman         | API testing                      |

---

## ✨ Key Features

### 📁 Project Management
- Only users with the **Admin** role can add, update, or delete projects.
- All other users can view projects.
- Each project has a unique ID.
- Every project must have a name and a description.

### 👤 User Management
- Users can register with their name, email, and password.
- Passwords are stored in **hashed format** in the PostgreSQL database.

### ✅ Task Management
- Users can create and view tasks.
- Before a task is updated or deleted, **task ownership is verified**.

### 🔐 Authentication
- Authentication is implemented with **Spring Security** using **JWT (JSON Web Tokens)**.
## ⚙️ Getting Started

