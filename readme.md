# System Integration Assignment 3

## Overview
This project implements a book ordering system for students. 
It built in an event driven architecture, using Kafka as the broker of the events.
Users can order a book on the Book Service, using a gRPC function call. An OrderCreated event will then be raised in Kafka.
There are two consumers: AccountingService and LibraryService.
AccountingService adds the order to a list of all orders. 
LibraryService adds the book to the student's owned books, charges the student's balance, and updates the stock.

AccountingService has a REST endpoint where students can request all their orders.
LibraryService has the following REST endpoints:
- Get recommended books for student
- Get owned books by student
- Get books by study-program id

![pis][diagram.png]

## Kafka
### How to run
1. Navigate to the Kafka directory
2. run docker-compose up -d

### Documentation
Kafka exposes the topic "OrderCreated".
Kafka can be accessed on the port: 9092

## Accounting Servce
### How to run
1. Navigate to the AccountingService directory
2. run docker-compose up -d
3. Open AccountingService.sln in Visual Studio, and run.

If you make changes to the init.sql file, you need to delete the posgres-data/ folder for the init.sql file to be re-run. 
NOTE: This does delete all the data in the database!

### Documentation
Postgres can be accessed on the port: 5432

## Library Service
### How to run
1. Navigate to the Library directory
2. run `docker build . -t librarydb`
3. run `docker run -d --name=library -p 3306:3306 librarydb`
4. run the service with `mvn spring-boot:run`

### Documentation
The service includes a consumer for buying books, and endpoints at `http://localhost:8080/books/program/{id}`, `http://localhost:8080/books/student/{id}` and `http://localhost:8080/books/student/recommended/{id}`

## Book Service
### How to run
1. Navigate to the BookService directory
2. run the service with `mvn spring-boot:run`