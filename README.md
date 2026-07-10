# 🚀 Spring Boot Microservices Project

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge\&logo=springboot)
![Spring Cloud Gateway](https://img.shields.io/badge/Spring%20Cloud-Gateway-blue?style=for-the-badge)
![WebClient](https://img.shields.io/badge/WebClient-Reactive-success?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/Database-H2-blue?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge\&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

A simple **Spring Boot Microservices** project demonstrating **Product**, **Order**, and **Payment** services communicating with each other using **Spring WebClient**, with **Spring Cloud Gateway** acting as the single entry point.

---

# 📑 Table of Contents

* [Overview](#-overview)
* [Architecture](#-architecture)
* [Tech Stack](#-tech-stack)
* [Project Structure](#-project-structure)
* [Microservices](#-microservices)
* [API Gateway](#-api-gateway)
* [Application Flow](#-application-flow)
* [REST APIs](#-rest-apis)
* [Sample Requests](#-sample-requests)
* [Running the Project](#-running-the-project)
* [Screenshots](#-screenshots)
* [Future Improvements](#-future-improvements)
* [Author](#-author)

---

# 📖 Overview

This project demonstrates the implementation of a **Microservices Architecture** using Spring Boot.

The application is divided into four independent services:

* 📦 Product Service
* 🛒 Order Service
* 💳 Payment Service
* 🌐 API Gateway

The **Order Service** communicates with the **Product Service** and **Payment Service** using **Spring WebClient**.

The **API Gateway** routes all client requests to the appropriate microservice.

---

# 🏗 Architecture

```text
                        Client
                           │
                           ▼
                Spring Cloud API Gateway
                         (8080)
                           │
        ┌──────────────────┼──────────────────┐
        ▼                  ▼                  ▼
 Product Service      Order Service     Payment Service
    (8081)               (8082)             (8083)
                              │
                ┌─────────────┴─────────────┐
                ▼                           ▼
         Product Service             Payment Service
          (WebClient)                 (WebClient)
```

---

# 💻 Tech Stack

| Technology           | Description                 |
| -------------------- | --------------------------- |
| Java 21              | Programming Language        |
| Spring Boot          | Backend Framework           |
| Spring Data JPA      | Persistence Layer           |
| Spring WebClient     | Inter-Service Communication |
| Spring Cloud Gateway | API Gateway                 |
| H2 Database          | In-Memory Database          |
| Maven                | Dependency Management       |

---

# 📂 Project Structure

```text
microservices-project
│
├── product-service
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── ProductServiceApplication.java
│
├── order-service
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── config
│   └── OrderServiceApplication.java
│
├── payment-service
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── PaymentServiceApplication.java
│
└── api-gateway
    ├── src/main/resources/application.yml
    └── ApiGatewayApplication.java
```

---

# 🔹 Microservices

## 📦 Product Service

**Port:** `8081`

### Features

* Create Product
* Get Product
* Update Product
* Delete Product

---

## 🛒 Order Service

**Port:** `8082`

### Features

* Create Order
* Retrieve Orders
* Update Order
* Delete Order
* Calls Product Service using WebClient
* Calls Payment Service using WebClient
* Calculates Total Price

---

## 💳 Payment Service

**Port:** `8083`

### Features

* Create Payment
* Get Payment
* Update Payment
* Delete Payment

---

# 🌐 API Gateway

**Port:** `8080`

The API Gateway provides a **single entry point** to all microservices.

| Incoming Request   | Forwarded To    |
| ------------------ | --------------- |
| `/api/products/**` | Product Service |
| `/api/orders/**`   | Order Service   |
| `/api/payments/**` | Payment Service |

---

# 🔄 Application Flow

```text
Client
   │
   ▼
API Gateway
   │
   ▼
Order Service
   │
   ├──────────────► Product Service
   │                    │
   │◄───────────────────┘
   │
Calculate Total Price
   │
   ├──────────────► Payment Service
   │                    │
   │◄───────────────────┘
   │
Save Order
   │
Return Response
```

---

# 📡 REST APIs

## Product APIs

| Method | Endpoint             |
| ------ | -------------------- |
| POST   | `/api/products`      |
| GET    | `/api/products`      |
| GET    | `/api/products/{id}` |
| PUT    | `/api/products/{id}` |
| DELETE | `/api/products/{id}` |

---

## Order APIs

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | `/api/orders`      |
| GET    | `/api/orders`      |
| GET    | `/api/orders/{id}` |
| PUT    | `/api/orders/{id}` |
| DELETE | `/api/orders/{id}` |

---

## Payment APIs

| Method | Endpoint             |
| ------ | -------------------- |
| POST   | `/api/payments`      |
| GET    | `/api/payments`      |
| GET    | `/api/payments/{id}` |
| PUT    | `/api/payments/{id}` |
| DELETE | `/api/payments/{id}` |

---

# 📝 Sample Requests

## Create Product

```json
{
    "name": "Laptop",
    "price": 65000,
    "quantity": 10
}
```

---

## Create Order

```json
{
    "customerName": "Elavarasan",
    "productId": 1,
    "quantity": 2,
    "paymentMethod": "UPI"
}
```

---

# ▶️ Running the Project

Start the applications in the following order:

1. Product Service
2. Payment Service
3. Order Service
4. API Gateway

Access the APIs using:

```text
http://localhost:8080/api/products
http://localhost:8080/api/orders
http://localhost:8080/api/payments
```

---

# 📷 Screenshots

Add screenshots after testing your application.

Suggested screenshots:

* Product API (Postman)
* Order API (Postman)
* Payment API (Postman)
* API Gateway Requests
* H2 Database Console
* IntelliJ Project Structure

Example folder structure:

```text
screenshots/
│
├── product-api.png
├── order-api.png
├── payment-api.png
├── gateway.png
├── h2-console.png
└── project-structure.png
```

Then embed them like:

```markdown
## Product API

![Product API](screenshots/product-api.png)

## Order API

![Order API](screenshots/order-api.png)

## Payment API

![Payment API](screenshots/payment-api.png)

## API Gateway

![Gateway](screenshots/gateway.png)
```

---

# 🚀 Future Improvements

* Eureka Server (Service Discovery)
* Spring Cloud Config Server
* Circuit Breaker (Resilience4j)
* JWT Authentication
* Spring Security
* Docker
* Docker Compose
* Kubernetes
* MySQL / PostgreSQL
* OpenAPI (Swagger)
* Centralized Logging
* Distributed Tracing
* Monitoring with Prometheus & Grafana

---

# 👨‍💻 Author

**Elavarasan M**

* 🎓 Computer Science & Engineering Student
* ☕ Java & Spring Boot Developer
* 🌱 Learning Microservices Architecture
* 💡 Exploring Spring Cloud and Distributed Systems

If you found this project useful, consider giving it a ⭐ on GitHub!
