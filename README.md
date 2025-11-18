#- Spring Boot Application

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen?logo=spring-boot)
![Java](https://img.shields.io/badge/Java-17-red?logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue?logo=docker)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apache-maven)

A modern, fully containerized **two-tier Spring Boot web application** with integrated Docker support, MySQL database, and multi-stage Docker builds for optimized production deployments.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Local Development Setup](#local-development-setup)
  - [Docker Compose Setup](#docker-compose-setup)
  - [Manual Docker Build](#manual-docker-build)
- [Configuration](#configuration)
- [Database Setup](#database-setup)
- [API Documentation](#api-documentation)
- [Docker Deep Dive](#docker-deep-dive)
  - [Multi-Stage Build Process](#multi-stage-build-process)
  - [Network Configuration](#network-configuration)
  - [Volume Management](#volume-management)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Project Overview

**PGTools** is a comprehensive web application built with Spring Boot 3.5.3 that demonstrates enterprise-level containerization practices. This project implements a complete end-to-end Docker setup with two independent containers communicating through a custom Docker bridge network.

### Key Highlights:
- ✅ **Two-Tier Architecture**: Application tier + Database tier
- ✅ **Multi-Stage Docker Build**: Optimized image size and security
- ✅ **Docker Compose Orchestration**: Single command deployment
- ✅ **Custom Bridge Network**: Secure container communication
- ✅ **Health Checks**: Database readiness monitoring
- ✅ **Volume Persistence**: Data durability across container restarts
- ✅ **Maven Build Integration**: Automated compilation and packaging

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Spring Boot** | 3.5.3 | Web Framework & MVC |
| **Java** | 17 | Programming Language |
| **MySQL** | Latest | Relational Database |
| **Maven** | 3.8.3 | Build Automation |
| **Docker** | Latest | Containerization |
| **Docker Compose** | 3.8 | Multi-Container Orchestration |
| **Thymeleaf** | 3.5.3+ | Template Engine |
| **Spring Data JPA** | 3.5.3+ | ORM & Data Access |
| **Spring Web** | 3.5.3+ | REST APIs & Web Controllers |

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│         Docker Compose Network (pgtools-network)        │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌──────────────────────┐      ┌──────────────────┐   │
│  │   PGTools App        │      │    MySQL 8.0     │   │
│  │  (Container Port)    │      │ (Container Port) │   │
│  │  8080 → Host 8080    │      │ 3306 → Host 3306 │   │
│  │                      │      │                  │   │
│  │ ┌──────────────────┐ │      │ ┌──────────────┐ │   │
│  │ │ Spring Boot App  │ │      │ │ MySQL Server │ │   │
│  │ │ (Thymeleaf UI)   │ │      │ │ pgtoolsdata  │ │   │
│  │ │ (REST APIs)      │ │      │ │ PgtoolsDB    │ │   │
│  │ │ (Mail Service)   │ │      │ │              │ │   │
│  │ └──────────────────┘ │      │ └──────────────┘ │   │
│  │                      │      │                  │   │
│  └──────────────────────┘      └──────────────────┘   │
│         depends_on: mysql          ↓ Health Check    │
└─────────────────────────────────────────────────────────┘
                          ↕ Network Communication
```

---

## ✨ Features

### Application Features:
- 📱 **Responsive Web UI** - Built with Thymeleaf templates and Bootstrap
- 🔐 **Data Validation** - Spring Validation framework
- 📊 **RESTful APIs** - Web services for data operations
- 📧 **Email Service** - SMTP integration with Gmail
- 💾 **JPA ORM** - Hibernate-based data persistence
- 📈 **Application Metrics** - Spring Boot Actuator
- 🗄️ **Large File Support** - Multipart uploads up to 1GB

### Docker Features:
- 🐳 **Multi-Stage Builds** - Optimized production images
- 🔗 **Custom Bridge Network** - Inter-container communication
- 💾 **Volume Persistence** - MySQL data durability
- 🏥 **Health Checks** - Container readiness monitoring
- ♻️ **Auto Restart** - Fault tolerance with restart policies
- 🔌 **Port Mapping** - Flexible port configuration

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

### For Local Development:
```bash
- Java Development Kit (JDK) 17+
- Maven 3.8.3+
- MySQL Server 8.0+ (for local testing)
```

### For Docker Deployment:
```bash
- Docker Desktop (or Docker Engine)
- Docker Compose 1.29+
- At least 2GB free disk space
```

### Installation Commands:

**macOS (using Homebrew):**
```bash
brew install java17
brew install maven
brew install mysql
brew install --cask docker
```

**Ubuntu/Debian:**
```bash
sudo apt-get update
sudo apt-get install openjdk-17-jdk maven mysql-server
sudo apt-get install docker.io docker-compose
```

**Windows (using Chocolatey):**
```bash
choco install openjdk17
choco install maven
choco install mysql
choco install docker-desktop
```

---

## 📁 Project Structure

```
springboot-app/
├── README.md                          # Project documentation
├── Dockerfile                         # Multi-stage Docker image definition
├── docker-compose.yml                 # Container orchestration config
├── pom.xml                            # Maven build configuration
├── mvnw / mvnw.cmd                    # Maven wrapper (Linux/Windows)
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/pg/PGTools/
│   │   │       ├── PgToolsApplication.java    # Spring Boot entry point
│   │   │       ├── config/                     # Configuration classes
│   │   │       ├── entity/                     # JPA entities
│   │   │       ├── repository/                 # Data access layer
│   │   │       ├── service/                    # Business logic interfaces
│   │   │       ├── serviceImp/                 # Service implementations
│   │   │       ├── restController/             # REST API endpoints
│   │   │       └── Controller/                 # MVC controllers
│   │   │
│   │   └── resources/
│   │       ├── application.properties          # Spring Boot configuration
│   │       ├── static/                         # Static assets (CSS, JS, Images)
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   ├── fonts/
│   │       │   └── images/
│   │       └── templates/                      # Thymeleaf HTML templates
│   │           ├── about.html
│   │           ├── contact.html
│   │           ├── customers.html
│   │           ├── gallery.html
│   │           ├── products.html
│   │           ├── index.html
│   │           └── Admin/                      # Admin dashboard templates
│   │
│   └── test/
│       └── java/com/pg/                        # Unit tests
│
├── target/                            # Build output (auto-generated)
└── .gitignore                         # Git ignore patterns
```

---

## 🚀 Getting Started

### Option 1: Local Development Setup

#### 1. Clone the Repository
```bash
git clone https://github.com/pratikk-devops/springboot-app.git
cd springboot-app
```

#### 2. Configure Database Connection
Edit `src/main/resources/application.properties`:

```properties
# Local MySQL setup
spring.datasource.url=jdbc:mysql://localhost:3306/pgtoolsdata?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

#### 3. Build the Project
```bash
# Using Maven wrapper (recommended)
./mvnw clean install

# Or using system Maven
mvn clean install
```

#### 4. Run the Application
```bash
./mvnw spring-boot:run
```

Access the application at: **http://localhost:8080**

---

### Option 2: Docker Compose Setup (⭐ Recommended)

This is the easiest way to run the entire two-tier application with a single command.

#### 1. Prepare Your Environment

Ensure Docker and Docker Compose are running:
```bash
docker --version
docker-compose --version
```

#### 2. Review the Configuration

Check `docker-compose.yml` for environment variables and credentials:

```yaml
services:
  mysql:
    container_name: mysql
    image: mysql:latest
    ports:
      - 3306:3306
    environment:
      MYSQL_ROOT_PASSWORD: pgtools@2025
      MYSQL_DATABASE: PgtoolsDB

  pgtoolapp:
    container_name: pgtoolapp
    image: pratikchaudhari555/springboot-pgtool-app:latest
    ports:
      - 8080:8080
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://13.49.232.110:3306/pgtoolsdata?allowPublicKeyRetrieval=true&useSSL=false
      SPRING_DATASOURCE_USERNAME: pgtools
      SPRING_DATASOURCE_PASSWORD: pgtools@2025
```

#### 3. Start the Application Stack

```bash
# Start all containers in detached mode
docker-compose up -d

# View running containers
docker-compose ps

# View logs
docker-compose logs -f pgtoolapp
docker-compose logs -f mysql
```

#### 4. Verify Services Are Running

```bash
# Check MySQL health
docker-compose exec mysql mysqladmin ping -h localhost -upgtools -ppgtools@2025

# Check app logs
docker-compose logs pgtoolapp | grep "Started"
```

#### 5. Access the Application

- **Application**: http://localhost:8080
- **MySQL**: localhost:3306

#### 6. Stop the Application

```bash
# Stop all containers
docker-compose stop

# Stop and remove containers
docker-compose down

# Stop and remove containers + volumes (cleans everything)
docker-compose down -v
```

---

### Option 3: Manual Docker Build

For understanding the containerization process step-by-step:

#### 1. Build the Docker Image

```bash
# Build using the provided Dockerfile
docker build -t springboot-pgtool-app:latest .

# Or with custom tags
docker build -t springboot-pgtool-app:1.0 .
docker build -t pratikchaudhari555/springboot-pgtool-app:latest .
```

#### 2. Create a Custom Bridge Network

```bash
# Create the bridge network
docker network create pgtools-network

# Verify network creation
docker network ls
```

#### 3. Run MySQL Container

```bash
docker run -d \
  --name mysql \
  --network pgtools-network \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=pgtools@2025 \
  -e MYSQL_DATABASE=PgtoolsDB \
  -v mysql-data:/var/lib/mysql \
  mysql:latest
```

#### 4. Wait for MySQL to Be Ready

```bash
docker exec mysql mysqladmin ping -h localhost -uroot -ppgtools@2025

# Keep checking until you get "mysqld is alive"
```

#### 5. Run Application Container

```bash
docker run -d \
  --name pgtoolapp \
  --network pgtools-network \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql:3306/pgtoolsdata?allowPublicKeyRetrieval=true&useSSL=false" \
  -e SPRING_DATASOURCE_USERNAME=pgtools \
  -e SPRING_DATASOURCE_PASSWORD=pgtools@2025 \
  springboot-pgtool-app:latest
```

#### 6. Verify Both Containers Are Running

```bash
docker ps

# View logs
docker logs -f pgtoolapp
docker logs -f mysql
```

---

## ⚙️ Configuration

### Application Properties

Located at: `src/main/resources/application.properties`

#### Database Configuration
```properties
# Database connection
spring.datasource.url=jdbc:mysql://13.49.232.110:3306/pgtoolsdata?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=pgtools
spring.datasource.password=pgtools@2025
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Connection pooling
spring.datasource.hikari.connection-timeout=30000

# Hibernate/JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

#### Server Configuration
```properties
server.port=8080

# Caching
spring.resources.cache.cachecontrol.max-age=0
spring.resources.cache.cachecontrol.no-cache=true
spring.resources.cache.cachecontrol.must-revalidate=true
```

#### File Upload Configuration
```properties
spring.servlet.multipart.max-file-size=1000MB
spring.servlet.multipart.max-request-size=1200MB
spring.servlet.multipart.file-size-threshold=1000000KB
```

#### Email Configuration
```properties
# SMTP Server (Gmail)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=pgtoolsindiapvtltd@gmail.com
spring.mail.password=fqqi shij ttnf jsfc
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
```

---

## 🗄️ Database Setup

### Database Structure

```sql
-- Database
CREATE DATABASE PgtoolsDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tables are auto-created by Hibernate (spring.jpa.hibernate.ddl-auto=update)
-- Entities defined in: src/main/java/com/pg/PGTools/entity/
```

### Manual Database Connection

If running MySQL locally:

```bash
# Connect to MySQL
mysql -h localhost -u root -p

# Create database
CREATE DATABASE pgtoolsdata CHARACTER SET utf8mb4;

# Create user
CREATE USER 'pgtools'@'%' IDENTIFIED BY 'pgtools@2025';
GRANT ALL PRIVILEGES ON pgtoolsdata.* TO 'pgtools'@'%';
FLUSH PRIVILEGES;
```

### Using Docker MySQL Shell

```bash
# Access MySQL inside Docker
docker exec -it mysql mysql -u root -ppgtools@2025

# View databases
SHOW DATABASES;

# Use database
USE PgtoolsDB;

# View tables
SHOW TABLES;
```

---

## 📚 API Documentation

### Available Endpoints

#### Web Pages (MVC)
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Home page |
| `/about` | GET | About page |
| `/products` | GET | Products listing |
| `/customers` | GET | Customers page |
| `/contact` | GET | Contact form page |
| `/gallery` | GET | Gallery page |

#### Admin Dashboard
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/Admin/dashboard` | GET | Admin dashboard |
| `/Admin/adminProducts` | GET | Manage products |
| `/Admin/adminCustomers` | GET | Manage customers |
| `/Admin/adminIndustries` | GET | Manage industries |

#### REST APIs
View the `restController/` directory in the source code for available REST endpoints.

---

## 🐳 Docker Deep Dive

### Multi-Stage Build Process

Our Dockerfile uses **two-stage build** for optimal image size and security:

#### Stage 1: Builder Stage
```dockerfile
FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app
COPY . /app
RUN mvn clean install -DskipTests=true
```

**Purpose**: 
- Uses Maven image to compile source code
- Generates executable JAR file
- Skips tests for faster builds
- **Only this stage contains Maven and build tools**

#### Stage 2: Runtime Stage
```dockerfile
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar /app/target/pgtools.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/pgtools.jar"]
```

**Purpose**:
- Uses lightweight JRE image (Alpine Linux)
- Only copies the compiled JAR
- **Final image is 300MB+ smaller** than using single-stage build
- No Maven or build tools in production image

### Benefits of Multi-Stage Build:

| Benefit | Details |
|---------|---------|
| **Smaller Image Size** | ~400MB vs ~900MB single-stage |
| **Faster Deployments** | Quicker image pulls and startups |
| **Enhanced Security** | No build tools in production image |
| **Better Practices** | Follows production image optimization |

### Docker Build Commands:

```bash
# Build with default tag
docker build -t pgtoolapp:latest .

# Build with specific version
docker build -t pgtoolapp:1.0.0 .

# Build with progress output
docker build --progress=plain -t pgtoolapp:latest .

# Build without cache
docker build --no-cache -t pgtoolapp:latest .

# Multi-stage build - view specific stage
docker build --target builder -t pgtoolapp:builder .
```

---

### Network Configuration

#### Docker Bridge Network

A custom bridge network (`pgtools-network`) provides:

1. **Service Discovery**: Containers can reference each other by name
   ```bash
   # Inside app container, connect to: mysql:3306
   jdbc:mysql://mysql:3306/pgtoolsdata
   ```

2. **Isolation**: Network isolation from other containers
3. **Automatic DNS**: Container names resolve to IP addresses

#### Network Commands:

```bash
# View network
docker network inspect pgtools-network

# Output example:
# {
#   "Name": "pgtools-network",
#   "Driver": "bridge",
#   "Containers": {
#     "mysql": { ... },
#     "pgtoolapp": { ... }
#   }
# }

# Create custom network
docker network create my-network --driver bridge

# Connect container to network
docker network connect my-network container-name

# Disconnect
docker network disconnect my-network container-name
```

---

### Volume Management

#### MySQL Data Persistence

```yaml
volumes:
  mysql-data:
    
services:
  mysql:
    volumes:
      - mysql-data:/var/lib/mysql
```

#### Volume Operations:

```bash
# List volumes
docker volume ls

# Inspect volume
docker volume inspect mysql-data

# Remove volume (⚠️ Deletes data)
docker volume rm mysql-data

# Backup data
docker run --rm -v mysql-data:/data -v $(pwd):/backup \
  alpine tar czf /backup/mysql-backup.tar.gz -C /data .

# Restore data
docker run --rm -v mysql-data:/data -v $(pwd):/backup \
  alpine tar xzf /backup/mysql-backup.tar.gz -C /data .
```

---

### Health Checks

MySQL container includes health checking:

```yaml
healthcheck:
  test: ["CMD","mysqladmin","ping","-h","localhost","-upgtools","-ppgtools@2025"]
  interval: 10s
  timeout: 5s
  retries: 5
  start_period: 50s
```

**Monitor health:**
```bash
docker compose ps

# View health status
docker inspect --format='{{json .State.Health}}' mysql | jq .

# Output:
# {
#   "Status": "healthy",
#   "FailingStreak": 0,
#   "Passes": 5,
#   "StartedAt": "2025-11-17T10:00:00Z"
# }
```

---

## 🔧 Troubleshooting

### Issue: Application fails to connect to MySQL

**Symptoms**: 
```
com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure
```

**Solutions**:

1. **Verify MySQL is healthy**:
   ```bash
   docker-compose ps
   docker-compose exec mysql mysqladmin ping -u root -ppgtools@2025
   ```

2. **Check network connectivity**:
   ```bash
   docker-compose exec pgtoolapp ping mysql
   ```

3. **Wait for MySQL startup**:
   ```bash
   # Add wait script or increase start_period in docker-compose.yml
   docker-compose up -d mysql
   sleep 30
   docker-compose up pgtoolapp
   ```

4. **Verify environment variables**:
   ```bash
   docker inspect pgtoolapp | grep -A 5 "Env"
   ```

---

### Issue: Port already in use

**Error**: `Error response from daemon: driver failed programming external connectivity`

**Solutions**:

```bash
# Find what's using port 8080
lsof -i :8080

# Find what's using port 3306
lsof -i :3306

# Kill process
kill -9 <PID>

# Or use different ports in docker-compose.yml
ports:
  - "8081:8080"  # Use 8081 instead
  - "3307:3306"  # Use 3307 instead
```

---

### Issue: Docker image build fails

**Error**: `RUN mvn clean install -DskipTests=true failed`

**Solutions**:

```bash
# Clear Maven cache
rm -rf ~/.m2/repository

# Build with verbose output
docker build --progress=plain --no-cache -t pgtoolapp:latest .

# Check available disk space
df -h

# Build locally first to verify
./mvnw clean install -DskipTests=true
```

---

### Issue: Application logs show errors

**Commands**:

```bash
# View real-time logs
docker-compose logs -f pgtoolapp

# View last 100 lines
docker-compose logs --tail=100 pgtoolapp

# View logs from specific time
docker-compose logs --since 5m pgtoolapp

# Export logs to file
docker-compose logs pgtoolapp > app-logs.txt
```

---

### Performance Optimization

```bash
# Check container resource usage
docker stats

# Limit container resources
docker run -d \
  --name pgtoolapp \
  --memory 512m \
  --cpus 1 \
  ...

# View system info
docker system info

# Prune unused resources
docker system prune -a
```

---

## 📊 Docker Commands Reference

### Container Management

```bash
# List running containers
docker ps

# List all containers (including stopped)
docker ps -a

# View container logs
docker logs container_name

# Execute command in container
docker exec -it container_name bash

# Stop container
docker stop container_name

# Start stopped container
docker start container_name

# Remove container
docker rm container_name

# View container details
docker inspect container_name
```

### Image Management

```bash
# List images
docker images

# Remove image
docker rmi image_name

# Tag image
docker tag image_name:old_tag image_name:new_tag

# Push to registry
docker push image_name:tag

# Pull from registry
docker pull image_name:tag
```

### Docker Compose

```bash
# Start services
docker-compose up -d

# Stop services
docker-compose stop

# Remove services
docker-compose down

# View service logs
docker-compose logs -f service_name

# Execute command
docker-compose exec service_name command
```

---

## 🌐 Environment Variables

### For Docker Deployment

```yaml
# MySQL
MYSQL_ROOT_PASSWORD=pgtools@2025
MYSQL_DATABASE=PgtoolsDB
MYSQL_USER=pgtools
MYSQL_PASSWORD=pgtools@2025

# Spring Boot
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/pgtoolsdata
SPRING_DATASOURCE_USERNAME=pgtools
SPRING_DATASOURCE_PASSWORD=pgtools@2025
```

### For Local Development

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/pgtoolsdata
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=your_password
```

---

## 📦 Maven Build

### Common Maven Commands

```bash
# Clean and install
./mvnw clean install

# Skip tests
./mvnw clean install -DskipTests=true

# Run tests
./mvnw test

# Run specific test
./mvnw test -Dtest=ClassName

# Package only (no tests)
./mvnw package -DskipTests

# View dependency tree
./mvnw dependency:tree

# Update dependencies
./mvnw versions:display-dependency-updates

# Check for vulnerabilities
./mvnw org.owasp:dependency-check-maven:check
```

---

## 📝 Dependencies

Core dependencies used in this project:

```xml
<!-- Spring Boot -->
spring-boot-starter-web
spring-boot-starter-thymeleaf
spring-boot-starter-data-jpa
spring-boot-starter-validation
spring-boot-starter-mail
spring-boot-starter-actuator
spring-boot-starter-web-services

<!-- Database -->
mysql-connector-j

<!-- Testing -->
spring-boot-starter-test
```

See `pom.xml` for complete dependency list and versions.

---

## 🚀 Deployment Best Practices

### Pre-Deployment Checklist

- [ ] All tests passing locally
- [ ] Docker image builds successfully
- [ ] Docker Compose file validated
- [ ] Environment variables configured
- [ ] Database backups created
- [ ] Security vulnerabilities checked
- [ ] Performance tested
- [ ] Documentation updated

### Production Deployment

```bash
# Build production image
docker build -t registry.example.com/pgtoolapp:1.0.0 .

# Push to registry
docker push registry.example.com/pgtoolapp:1.0.0

# Deploy using Compose
docker-compose -f docker-compose.prod.yml up -d
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is open source and available for educational and reference purposes.

---

## 👤 Author

**Pratik Chaudhari**
- GitHub: [@pratikk-devops](https://github.com/pratikk-devops)
- Docker Hub: [@pratikchaudhari555](https://hub.docker.com/u/pratikchaudhari555)

---

## 📞 Support

For issues, questions, or suggestions:
1. Check the [Troubleshooting](#troubleshooting) section
2. Review Docker Compose configuration
3. Check application logs
4. Open an issue on GitHub

---

## 🔗 Quick Links

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Maven Documentation](https://maven.apache.org/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)

---

## 📊 Project Statistics

- **Language**: Java
- **Framework**: Spring Boot 3.5.3
- **Build Tool**: Maven 3.8.3+
- **Container Runtime**: Docker
- **Database**: MySQL 8.0+
- **JDK Version**: 17+
- **Lines of Code**: Custom implementation
- **Test Coverage**: Unit tests included

---

**Last Updated**: November 17, 2025

**Version**: 1.0.0

---

> ⭐ If this project helped you, please consider giving it a star on GitHub!
