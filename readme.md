
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

If you make changes to the init.sql file, you need to delete the posgres-data/ folder for the init.sql file to be re-run. 
NOTE: This does delete all the data in the database!

### Documentation
Postgres can be accessed on the port: 5432

## Library Servce
### How to run
1. Navigate to the Library directory
2. run `docker build . -t librarydb`
3. run `docker run -d --name=library -p 3306:3306 librarydb`
4. run the service with `mvn spring-boot:run`
