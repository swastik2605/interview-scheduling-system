# Interview Scheduling System

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Kafka](https://img.shields.io/badge/Kafka-enabled-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-lightblue)
![Redis](https://img.shields.io/badge/Redis-cache-red)
![Docker](https://img.shields.io/badge/Docker-ready-blue)

> A microservices-based interview scheduling system built with 
> Spring Boot, MySQL, Redis, Kafka and Docker.
## Architecture

```
interview-scheduling-system/
├── candidate-service/       # Manages candidate registration & profiles
│   ├── src/
│   └── pom.xml
└── interview-service/       # Manages interview slots & scheduling
    ├── src/
    └── pom.xml
```

**Communication:** Services communicate via **Apache Kafka** for async events.
**Caching:** Redis is used for fast data retrieval.
**Database:** Each service has its own MySQL database (DB per service pattern).
