# Book Retail Project

Clone this example's source code:

```bash
git clone https://github.com/aliemreky/book-retail.git
cd book-retail
```

##Project Detail:
```bash
• Registering New Customer
• Placing a new order
• Tracking the stock of books
• List all orders of the customer
• Viewing the order details
• Query Monthly Statistics
```
Run the application!

Maven build
```bash
mvn clean package
```

Maven Dependencies

```bash
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

###Database

**You have to create a database that names is BookDB in Mongo DB !**

Properties file
```bash
spring:
  application:
    name: BookRetail
  data:
    mongodb:
      uri: mongodb://localhost:27017/BookDB
      repositories:
        type: auto
      auto-index-creation: true
```

###API Endpoints

All Api Endpoint secures with Spring Security JWT.
First register, then get auth token from header. Then can be called endpoints.

> **POST Mapping** http://localhost:8080/login -> Public endpoint which returns a signed JWT for valid user credentials (username/password)

> **POST Mapping** http://localhost:8080/api/user/sign-up

> **POST Mapping** http://localhost:8080/api/user/get-orders

> **POST Mapping** http://localhost:8080/api/book/create

> **POST Mapping** http://localhost:8080/api/book/update

> **POST Mapping** http://localhost:8080/api/order/create

> **POST Mapping** http://localhost:8080/api/order/detail

> **POST Mapping** http://localhost:8080/api/order/get-list-by-date

> **POST Mapping** http://localhost:8080/api/order/get-statistic-monthly