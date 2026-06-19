# OrderMyFood

A food ordering platform built with Spring Boot microservices. The system allows users to search for restaurants, browse menus, and place orders — all routed through a central API Gateway with service discovery, distributed tracing, circuit breaking, and code coverage analysis.

## Architecture

```
Client
  └── API Gateway (port 8969)
        ├── /v1/api/order/**        → Order Management Service (port 8084)
        └── /v1/api/restaurants/**  → Restaurant Search Service (port 8083)

Service Discovery: Eureka Server (port 8761)
Distributed Tracing: Zipkin (port 9411)
Code Coverage: JaCoCo + SonarQube
```

## Services

| Service | Port | Description |
|---|---|---|
| EurekaServer | 8761 | Service registry and discovery |
| ApiGateway | 8969 | Single entry point with circuit breaker |
| OrderManagementService | 8084 | Users, orders, and order items |
| RestaurantSearchService | 8083 | Restaurants and menu items |

## Tech Stack

- **Java 17**, Spring Boot 2.7.8, Spring Cloud 2021.0.5
- **Spring Cloud Netflix Eureka** — service registration and discovery
- **Spring Cloud Gateway** — API gateway with reactive routing
- **OpenFeign** — declarative HTTP client (Order → Restaurant service calls)
- **Resilience4j** — circuit breaker with COUNT_BASED sliding window
- **Spring Cloud Sleuth + Zipkin** — distributed tracing
- **Spring Data JPA + MySQL** — persistence
- **JaCoCo + SonarQube** — test coverage and static analysis
- **Lombok** — boilerplate reduction

## API Reference

### Restaurant Search Service (`/v1/api/restaurants`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/` | Get all restaurants |
| `GET` | `/{id}` | Get restaurant by ID |
| `GET` | `/search/location?location=` | Search by location |
| `GET` | `/search/distance?distance=` | Search by distance (km) |
| `GET` | `/search/cuisine?cuisine=` | Search by cuisine type |
| `GET` | `/search/budget?budget=` | Search by budget |
| `GET` | `/search/name?name=` | Search by restaurant name |
| `GET` | `/restaurant/{id}/menu` | Get menu for a restaurant |
| `POST` | `/restaurant` | Add a new restaurant |
| `PUT` | `/restaurant` | Update a restaurant |
| `DELETE` | `/{id}` | Delete a restaurant |

### Menu Items (`/v1/api/menu-items`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/` | Get all menu items |
| `GET` | `/menu/{id}` | Get menu item by ID |
| `POST` | `/menu` | Add a menu item |
| `PUT` | `/menu` | Update a menu item |
| `DELETE` | `/menu/{id}` | Delete a menu item |

### Order Management Service (`/v1/api/order`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/{orderId}` | View order by ID |
| `POST` | `/create` | Place a new order |
| `PUT` | `/update` | Update an existing order |
| `DELETE` | `/cancel/{id}` | Cancel an order |

### User Management (`/v1/api/order/users`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/{id}` | Get user by ID |
| `POST` | `/create` | Create a new user |
| `PUT` | `/update` | Update a user |
| `DELETE` | `/{id}` | Delete a user |

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+
- Zipkin (for distributed tracing)

## Setup

**1. Database**

Create a MySQL database:

```sql
CREATE DATABASE ordermyfood;
```

**2. Update credentials**

Edit `application.properties` in both `OrderManagementService` and `RestaurantSearchService` with your MySQL credentials:

```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

**3. Start services in order**

```bash
# 1. Eureka Server
cd EurekaServer && ../mvnw spring-boot:run

# 2. Restaurant Search Service
cd RestaurantSearchService && ../mvnw spring-boot:run

# 3. Order Management Service
cd OrderManagementService && ../mvnw spring-boot:run

# 4. API Gateway
cd ApiGateway && ./mvnw spring-boot:run

# 5. Zipkin (optional, for tracing)
docker run -d -p 9411:9411 openzipkin/zipkin
```

All requests should go through the API Gateway at `http://localhost:8969`.

## Running Tests

```bash
# Run all tests with coverage report
./mvnw test

# Run SonarQube analysis
./mvnw sonar:sonar
```

JaCoCo coverage reports are generated under `target/site/jacoco/index.html` in each module.

## Circuit Breaker Configuration

The API Gateway applies circuit breakers to both downstream services:

- **Failure rate threshold:** 50%
- **Sliding window:** 10 calls (COUNT_BASED)
- **Wait duration in open state:** 5 seconds
- **Minimum calls before evaluation:** 5

When a service is unavailable, the gateway falls back to dedicated fallback endpoints to return a graceful error response instead of propagating failures.

## Screen Captures

Output screenshots are available in the `output screen captures/` directory, including:

- Service discovery via Eureka
- Distributed tracing via Zipkin
- Circuit breaker states
- REST API responses for all operations
- JaCoCo and SonarQube code coverage reports
- Unit test results for all services
