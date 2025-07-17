## Project Overview

This **Book Borrowing App** is a Spring Boot-based application built using Spring boot for authentication and authorization. It provides CRUD operations for managing books and borrowers, along with the ability to record borrowing transactions. The system uses **JWT (JSON Web Token)** for secure authentication, ensuring that only authorized users can access the system.

### Features:
- **Book CRUD**: Allows users to add, update, delete, and retrieve books.
- **Borrower CRUD**: Allows users to manage borrower details such as name, address, contact information, etc.
- **Borrowing Functionality**: Allows borrowers to borrow multiple books and track borrowing transactions.
  
###Tools Uses
- Java 21 (LTS)
- Spring Boot
- Mysql
- STS (IDE)
- Spring Security
  
### Authentication:
- The system uses **JWT** tokens for authentication.
- Login is done with the hardcoded credentials (`admin` / `admin`), and users can access the system and other endpoint  by sending their JWT tokens in the **Authorization** header of requests.

## API Endpoints

The following endpoints are available for interacting with the system. Each request that modifies data requires an **Authorization Bearer Token** in the request headers.

### Example endpoint Borrowers Endpoints

- **GET** `/api/borrowers`: Fetch all borrowers.
  - Authorization: Bearer Token
- **POST** `/api/borrowers`: Create a new borrower.
  - Authorization: Bearer Token
  - Request Body:
    ```json
    {
      "borrowerName": "Ganesh Nepali",
      "address": "Butwal, Rupandehi",
      "mobileNo": "9822345678",
      "email": "ganesh.nepali@example.com"
    }
    ```
- **PUT** `/api/borrowers/{id}`: Update borrower information.
  - Authorization: Bearer Token
  - Request Body:
    ```json
    {
      "borrowerName": "Sita Kumari Thapa",
      "address": "Biratnagar, Morang",
      "mobileNo": "9811122233",
      "email": "sita.sharma@example.com"
    }
    ```
- **DELETE** `/api/borrowers/{id}`: Delete a borrower.
  - Authorization: Bearer Token

### Borrows Endpoints

- **GET** `/api/borrows`: Fetch all borrow transactions.
  - Authorization: Bearer Token
- **GET** `/api/borrows/borrower/{borrowerId}`: Fetch borrow transactions for a specific borrower.
  - Authorization: Bearer Token
- **POST** `/api/borrows`: Record a new borrow transaction.
  - Authorization: Bearer Token
  - Request Body:
    ```json
    {
      "borrowerId": 1,
      "bookIds": [2, 3]
    }
    ```

### Books Endpoints

- **GET** `/api/books`: Fetch all books.
  - Authorization: Bearer Token
- **POST** `/api/books`: Create a new book.
  - Authorization: Bearer Token
  - Request Body:
    ```json
    {
      "title": "Book Title",
      "author": "Author Name",
      "price": 300,
      "quantity": 10
    }
    ```
- **PUT** `/api/books/{id}`: Update a book's details.
  - Authorization: Bearer Token
  - Request Body:
    ```json
    {
      "title": "Updated Book Title",
      "author": "Updated Author Name",
      "price": 350,
      "quantity": 15
    }
    ```
- **DELETE** `/api/books/{id}`: Delete a book.
  - Authorization: Bearer Token

### Login Endpoint

- **POST** `/api/login`: Authenticate a user and receive a JWT token.
  - Request Body:
    ```json
    {
      "username": "admin",
      "password": "admin"
    }
    ```
  - Response:
    ```json
    {
      "token": "your_jwt_token_here"
    }
    ```

## JWT Token Usage

Once you log in with the credentials (`admin` / `admin`), you will receive a JWT token. This token should be used in the **Authorization** header for subsequent requests:

## Api Response
I've used an API response class to respond to each endpoint with a proper response containing the status code, message, and data. The available and borrowed books, as well as all book data, are returned with pagination and a search feature

