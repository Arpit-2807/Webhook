# Payment Webhook Dashboard

A full-stack Java web application that simulates a real-time payment webhook processing system with a live dashboard built using Spring Boot and Thymeleaf.

## What is a Webhook?

A webhook is a way for external services (like Razorpay or Stripe) to notify your application when an event happens. Instead of your app constantly asking "did a payment happen?", the payment service calls YOUR app automatically.

## Project Overview

This project simulates a payment webhook system where:
- External services can send payment events to your endpoint
- The app processes and saves the event to a database
- A live dashboard displays all payment events in real time
- A logs page shows detailed webhook event history

## Features

- REST API endpoint to receive webhook POST requests
- Real-time payment dashboard with stats cards
- Webhook logs page with detailed event cards
- In-memory H2 database with JPA and Hibernate
- Spring Security to protect endpoints
- Thymeleaf HTML templates for the frontend
- Test endpoint to simulate incoming webhooks

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 25 | Programming language |
| Spring Boot 3.2 | Backend framework |
| Spring MVC | Web layer and REST API |
| Spring Security | Endpoint protection |
| Spring Data JPA | Database operations |
| Hibernate | ORM framework |
| H2 Database | In-memory database |
| Thymeleaf | HTML template engine |
| Maven | Build and dependency management |

## How to Run

**Prerequisites:**
- Java 17 or higher
- Maven
- IntelliJ IDEA

**Steps:**

1. Clone the repository: 
git clone https://github.com/yourusername/webhook.git

2. Open in IntelliJ IDEA

3. Let Maven download dependencies automatically

4. Run `WebhookApplication.java`

5. Open browser and go to:
   http://localhost:8080

## How to Test

**Trigger a test payment:**
http://localhost:8080/webhook/test

**View dashboard:**
http://localhost:8080

**View webhook logs:**
http://localhost:8080/logs

**View H2 database console:**
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:webhookdb
Username: sa
Password: (leave empty)

**Send a real webhook using Postman:**
POST http://localhost:8080/webhook

## Send a Real Webhook Using Postman

**URL:**
```
POST http://localhost:8080/webhook
```

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
    "event": "payment.success",
    "paymentId": "pay_123456",
    "amount": 1500.00,
    "currency": "INR",
    "status": "success"
}
```

**Failed Payment Example:**
```json
{
    "event": "payment.failed",
    "paymentId": "pay_789012",
    "amount": 2500.00,
    "currency": "INR",
    "status": "failed"
}
```

**Expected Response:**
```json
{
    "id": 1,
    "eventType": "payment.success",
    "paymentId": "pay_123456",
    "amount": 1500.0,
    "currency": "INR",
    "status": "success",
    "createdAt": "2026-05-16T09:15:53.347943"
}
```
## Project Structure

```
webhook/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/webhook/
│       │       ├── config/
│       │       │   ├── AppConfig.java
│       │       │   └── SecurityConfig.java
│       │       ├── controller/
│       │       │   ├── DashboardController.java
│       │       │   └── WebhookController.java
│       │       ├── exception/
│       │       │   ├── GlobalExceptionHandler.java
│       │       │   └── WebhookException.java
│       │       ├── model/
│       │       │   ├── PaymentEvent.java
│       │       │   └── WebhookPayload.java
│       │       ├── repository/
│       │       │   └── PaymentEventRepository.java
│       │       ├── service/
│       │       │   ├── DashboardService.java
│       │       │   └── WebhookService.java
│       │       └── WebhookApplication.java
│       └── resources/
│           ├── static/
│           │   ├── css/
│           │   │   └── style.css
│           │   └── js/
│           │       └── dashboard.js
│           ├── templates/
│           │   ├── dashboard.html
│           │   ├── layout.html
│           │   └── webhook-log.html
│           └── application.properties
└── pom.xml
```


## Architecture

```
┌─────────────────────────────────────────────────────┐
│              External Service                        │
│          (Razorpay / Stripe / Postman)               │
└─────────────────────┬───────────────────────────────┘
                      │
                      │ POST /webhook (JSON)
                      ▼
┌─────────────────────────────────────────────────────┐
│              WebhookController                       │
│         Receives incoming HTTP requests              │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│               WebhookService                         │
│   Converts WebhookPayload → PaymentEvent             │
│        Processes business logic                      │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│          PaymentEventRepository                      │
│         Saves data to H2 Database                    │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│               H2 Database                            │
│           payment_events table                       │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│           DashboardController                        │
│      Fetches data and sends to Thymeleaf             │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│         dashboard.html / webhook-log.html            │
│              Thymeleaf Templates                     │
└─────────────────────┬───────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────┐
│                  Browser                             │
│         You see the live dashboard!                  │
└─────────────────────────────────────────────────────┘
```

## Key Concepts Demonstrated

- **MVC Architecture** - clear separation of Controller, Service, Repository layers
- **REST API** - POST endpoint for receiving webhooks
- **Dependency Injection** - using @Autowired throughout
- **ORM** - JPA/Hibernate mapping Java objects to database tables
- **Spring Security** - protecting endpoints with security configuration
- **Thymeleaf** - server side rendering of HTML with dynamic data

## Author
**Arpit Carpenter**
- GitHub: https://github.com/Arpit-2807
- LinkedIn: https://www.linkedin.com/in/arpit-carpenter-106b89277/
## License

This project is open source and available under the MIT License.
