**Backend Task Management System** built with Java, Spring boot, PostgreSql, Hibernate and Spring Security. The application provides RESTFUL API's for managing users, projects and tasks along with authorization and authentication
🚀 **Project Overview**
The Task Management System is a Rest API designed to manage users, projects and tasks in a secure way. The project was developed to demonstrate real world java spring boot backend development including REST API development, database integration, exception handling, authentication and authorization
🛠️ **Technologies Used**
Java 21
Maven
Hibernate
Spring Boot
JWT
PostgreSQL
Postman

✨ **Key Features**
**Project Management**
Ony the users with roles of Admin can update, add or delete the project while the other users can view the project. Each project must have unique Id. Name and the description of the project should be mentioned.
**User Management**
The user can add his name, email and password and the password should be in hashed format in the database PostgreSQL
**Tasks Management**
The user can create and view the tasks. But for updation and deletion of the tasks, firstly tasks ownership will be checked.
**Authentication**
Authentication has been implemented using Spring Boot Security JWT-based authentication
