**📚 Library Book Management System**


A Spring Boot REST API to manage books, borrowers, and the borrowing lifecycle — featuring soft deletion, pagination, and realistic validations.

🚀 Features

Add, update, and soft-delete books

Borrower registration and borrowing history

Borrow/return book workflows with validations

Pagination and sorting for books

Overdue borrower tracking

Automatic fine calculation

🧩 Tech Stack

Java 8

Spring Boot 3.x

Spring Data JPA / Hibernate

H2 / MySQL (configurable)

Spring Web (REST API)


⚙️ Setup Instructions

Clone the repository

git clone https://github.com/your-username/library-management-system.git

cd library-management-system


Configure application.properties
Example (for H2):

spring.datasource.url=jdbc:h2:mem:librarydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=


Run the application

mvn spring-boot:run


Access API via:
👉 http://localhost:8080/
👉 http://localhost:8080/h2-console

📘 API Reference
🏷️ Book Management APIs

➕ POST /books

Add a new book or increase total copies if the title already exists.

Example Request:

{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "category": "Fiction",
  "isAvailable": true,
  "totalCopies": 7,
  "availableCopies": 5
}


Sample Endpoints:

POST http://localhost:8080/books/

📄 GET /books

List all books with optional filters, pagination, and sorting.

Parameters:

category (optional)

available (optional)

page, size, sort (for pagination/sorting)

Examples:

GET http://localhost:8080/books/?available=true
GET http://localhost:8080/books/?category=Tech
GET http://localhost:8080/books?page=0&size=5&sort=title,asc

✏️ PUT /books/{id}

Update book details or adjust available copies.

PUT http://localhost:8080/books/{bookId}

❌ DELETE /books/{id}

Soft delete a book only if it has no active borrow record.

DELETE http://localhost:8080/books/{bookId}

👥 Borrower Management APIs
➕ POST /borrowers

Register a new borrower.

POST http://localhost:8080/borrowers

📜 GET /borrowers/{id}/records

Get all borrowing history for a borrower.

GET http://localhost:8080/borrowers/{borrowerId}/records

⚠️ GET /borrowers/overdue

List borrowers with overdue books (dueDate < today and returnDate == null).

GET http://localhost:8080/borrowers/overdue

🔁 Borrowing Workflow APIs
📘 POST /records/borrow

Borrow a book.

Validations:

Borrower exists and has not exceeded maxBorrowLimit

Book has at least 1 available copy

dueDate = borrowDate + 10 days

Decrease availableCopies by 1

Example:

POST http://localhost:8080/records/borrow/?borrowerId={borrowerId}&bookId={bookId}

📗 POST /records/return

Return a borrowed book.

Validations:

Must have an active borrow record

Compute fineAmount if returnDate > dueDate

Increase availableCopies by 1

Example:

POST http://localhost:8080/records/return/?borrowerId={borrowerId}&bookId={bookId}

📂 GET /records/active

List all currently borrowed books with borrower names and due dates.

GET http://localhost:8080/records/active/

🧮 Data Model Overview

Entities:

Book

Borrower

BorrowRecord

Each entity is linked via JPA relationships with soft-deletion and data consistency validations.

🧑‍💻 Developer Notes

Use @JsonManagedReference / @JsonBackReference to prevent infinite recursion in entity serialization.

All IDs are auto-generated UUID strings.

Pagination uses Pageable interface from Spring Data JPA.
