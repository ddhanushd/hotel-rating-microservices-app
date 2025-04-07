# 🏨 Hotel Rating Microservices Application

This project demonstrates a microservices-based architecture using Spring Boot and Spring Cloud for managing Users, Hotels, and Ratings.

## 🔧 Tech Stack
- **Spring Boot** (User, Hotel, Rating services)
- **Spring Cloud Gateway** – API Gateway for routing
- **Eureka Server** – Service Discovery
- **Feign Client** – Inter-service communication
- **Resilience4j** – Circuit Breaker for fault tolerance
- **OAuth2 + Okta** – API Security
- **Redis** – Caching layer for performance
- **MySQL** – Persistent storage

## 🧱 Microservices
- `User-Service`: Manages users
- `Hotel-Service`: Manages hotel data
- `Rating-Service`: Stores ratings and maps them to users and hotels
- `API-Gateway`: Entry point to route all requests
- `Eureka-Server`: Registers and discovers services dynamically

## ✅ Features
- Secure API access with OAuth2 and Okta
- Circuit breaker to handle service failures
- Redis caching for faster responses
- Scalable, independently deployable services

## 🧠 Key Concepts Covered
- Microservices design and decomposition
- RESTful communication with Feign Clients
- Service discovery, API Gateway routing
- Security and caching in distributed systems

## 🚀 Getting Started
Each service can be run independently after configuring the required environment properties and registering with Eureka.


