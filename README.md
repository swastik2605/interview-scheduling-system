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

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Messaging | Apache Kafka |
| Database | MySQL 8.0 |
| Cache | Redis |
| Containerization | Docker & Docker Compose |
| Build Tool | Maven |

## Microservices

### Candidate Service
- Register and manage candidates
- Exposes REST APIs for candidate CRUD operations
- Publishes events to Kafka on candidate creation

### Interview Service
- Manage interview slots
- Consumes Kafka events from Candidate Service
- Schedules and assigns interview slots to candidates

## Getting Started

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Maven

### Run with Docker
```bash
docker-compose up --build
```

### Run locally
```bash
# Clone the repo
git clone https://github.com/swastik2605/interview-scheduling-system.git

# Run candidate service
cd candidate-service
mvn spring-boot:run

# Run interview service
cd interview-service
mvn spring-boot:run
```
## API Endpoints

### Candidate Service
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/candidates | Register a candidate |
| GET | /api/candidates | Get all candidates |
| GET | /api/candidates/{id} | Get candidate by ID |
| DELETE | /api/candidates/{id} | Delete candidate |

### Interview Service
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/interviews | Create interview slot |
| GET | /api/interviews | Get all slots |
| GET | /api/interviews/{id} | Get slot by ID |

## Author
**Swastik** — [@swastik2605](https://github.com/swastik2605)

## License
This project is open source and available under the [MIT License](LICENSE).
