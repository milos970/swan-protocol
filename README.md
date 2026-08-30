# 🦢 Swan Protocol

> A Spring Boot web application inspired by *The Lost*, simulating a fictional monitoring system where registered workers must periodically enter a numerical sequence within a given time limit.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square\&logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square\&logo=springboot\&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square\&logo=springsecurity\&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square\&logo=mysql\&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square\&logo=redis\&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=flat-square\&logo=flyway\&logoColor=white)
![HashiCorp Vault](https://img.shields.io/badge/HashiCorp%20Vault-FFEC6E?style=flat-square\&logo=vault\&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square\&logo=apache-maven\&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-Real--Time%20Communication-010101?style=flat-square)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square\&logo=thymeleaf\&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square\&logo=javascript\&logoColor=black)

---

## 📝 Description

Swan Protocol is a web application inspired by the TV show *The Lost*.

Registered workers log into a fictional monitoring system and are required to enter a predefined numerical sequence within a given time interval. As the countdown reaches its critical stage, additional workers can log in and continue the process.

The project combines a **Spring Boot backend**, **server-side rendered frontend**, **REST API**, **authentication**, **database persistence**, **caching/in-memory storage** and **secrets management**.

---

## ✨ Features

* 🔐 **Authentication & Authorization** — Secure user login using Spring Security.
* ⏱️ **Countdown System** — Workers must enter the required sequence within a defined interval.
* 🚨 **System Failure Mechanism** — The system changes state when the countdown expires.
* 👥 **Multi-Worker Access** — Additional workers can join during the critical stage.
* 💬 **Forum** — Authenticated workers can communicate through a simple forum.
* 🔄 **CRUD Operations** — Create, read, update and delete application data.
* 🌐 **REST API** — Endpoints for working with user-related data.
* 🔄 **AJAX** — Asynchronous updates without full page reloads.
* 🔌 **WebSocket** — Real-time communication between connected workers.
* ✅ **Client & Server Validation** — Input validation on both frontend and backend.
* 🌗 **Dynamic Theme** — UI theme changes based on the current time of day.
* 🗄️ **MySQL Persistence** — Relational data stored using MySQL and JPA/Hibernate.
* 🔄 **Flyway Migrations** — Version-controlled database schema migrations.
* ⚡ **Redis** — In-memory data storage.
* 🔐 **HashiCorp Vault** — External management of sensitive configuration.

---

## 🏗️ Architecture

The application follows a layered **Spring MVC architecture**.

```text
┌────────────────────────────────┐
│            FRONTEND            │
│                                │
│ HTML / CSS / JavaScript        │
│ Bootstrap / Thymeleaf / AJAX   │
└───────────────┬────────────────┘
                │
                │ HTTP
                ▼
┌────────────────────────────────┐
│         CONTROLLER LAYER       │
│                                │
│ Spring MVC / REST Controllers  │
└───────────────┬────────────────┘
                │
                ▼
┌────────────────────────────────┐
│           SERVICE LAYER        │
│                                │
│ Business Logic / Validation    │
└───────────────┬────────────────┘
                │
                ▼
┌────────────────────────────────┐
│        REPOSITORY LAYER        │
│                                │
│         JPA / Hibernate        │
└───────────────┬────────────────┘
                │
         ┌──────┴───────┐
         ▼              ▼
┌───────────────┐ ┌───────────────┐
│    MySQL      │ │     Redis     │
│   Database    │ │ In-memory Data│
└───────────────┘ └───────────────┘
```

---

## 🔐 Security

Authentication and authorization are implemented using **Spring Security**.

The application protects authenticated functionality and secured endpoints while sensitive configuration is externalized through **HashiCorp Vault**.

Secrets such as:

* JWT secret
* Database credentials
* Mail credentials
* Other sensitive configuration

are not stored directly in the source code.

---



## 💾 Database

The application uses **MySQL** as the primary relational database.

**Spring Data JPA / Hibernate** is used for persistence and object-relational mapping.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=validate
```

Hibernate is configured to validate the existing schema rather than modify it automatically.

---

## 🔄 Database Migrations

Database schema changes are managed using **Flyway**.

```properties
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=false
```

Migration scripts are stored in:

```text
src/main/resources/db/migration
```

Pending migrations are applied automatically when the application starts.

Spring's automatic SQL initialization is disabled because schema management is handled by Flyway:

```properties
spring.sql.init.mode=never
```

---

## ⚡ Redis

The application uses **Redis** as an in-memory data store.

Default configuration:

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

Redis is used for backend functionality that benefits from fast in-memory access.

---

## 🔐 Secrets Management

Sensitive application configuration is managed using **HashiCorp Vault** through **Spring Cloud Vault**.

The application imports configuration using:

```properties
spring.config.import=vault://
```

Vault configuration:

```properties
spring.cloud.vault.uri=http://localhost:8200
spring.cloud.vault.token=YOUR_VAULT_TOKEN
spring.cloud.vault.kv.enabled=true
spring.cloud.vault.kv.backend=secret
spring.cloud.vault.kv.default-context=swan-protocol
```

Sensitive values are stored externally and injected into the application at runtime.


---

## 📧 Email

The application uses **Gmail SMTP** for email communication.

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Mail credentials are managed through HashiCorp Vault.

---

## ⏱️ Countdown System

The countdown is the central gameplay mechanic of the application.

Workers must periodically enter a predefined numerical sequence.

```text
Normal State
     │
     ▼
Countdown
     │
     ▼
Critical Period
     │
     ├── Additional worker can join
     │
     ▼
Successful Input ──────► Countdown Continues
     │
     ▼
Time Expired
     │
     ▼
System Failure
```

The frontend handles the interactive countdown while the backend validates the corresponding application state.

---


## 🔌 WebSocket

The application uses **WebSocket** for real-time communication between the server and connected clients.

WebSocket is used for functionality that requires immediate updates without repeatedly polling the server.

```text
Client
   │
   │ WebSocket Connection
   ▼
Server
   │
   │ Real-Time Updates
   ▼
Connected Clients
```
---

## ⚙️ Configuration

The application depends on the following local services:

```text
MySQL            → localhost:3306
Redis            → localhost:6379
HashiCorp Vault  → localhost:8200
```

### MySQL

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Redis

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### HashiCorp Vault

```properties
spring.config.import=vault://

spring.cloud.vault.uri=http://localhost:8200
spring.cloud.vault.token=YOUR_VAULT_TOKEN
spring.cloud.vault.kv.enabled=true
spring.cloud.vault.kv.backend=secret
spring.cloud.vault.kv.default-context=swan-protocol
```

### Mail

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> Replace example values with your local configuration. Never commit production credentials or secrets.

---

## 🚀 Getting Started

### Requirements

* JDK 8+
* Maven
* MySQL
* Redis
* HashiCorp Vault
* IntelliJ IDEA or another Java IDE

### 1. Clone the repository

```bash
git clone https://github.com/milos970/swan-protocol.git
cd swan-protocol
```

### 2. Start required services

Make sure the following services are running:

```text
MySQL
Redis
HashiCorp Vault
```

### 3. Configure Vault

Create the required `swan-protocol` secrets in HashiCorp Vault.

Store sensitive configuration such as:

```text
JWT secret
Database username
Database password
Mail credentials
```

### 4. Install dependencies

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

You can also run the main Spring Boot application class directly from IntelliJ IDEA.

### 6. Database migrations

Flyway automatically applies pending migrations from:

```text
src/main/resources/db/migration
```

No manual SQL schema initialization is required.

---

## 📸 Screenshots

### Login

![Login](docs/screenshots/login.png)

### Dashboard

![Dashboard](docs/screenshots/dashboard.png)

### Countdown

![Countdown](docs/screenshots/countdown.png)

### Forum

![Forum](docs/screenshots/forum.png)

### User Management

![User Management](docs/screenshots/users.png)

---

## 🧪 Testing

The application has been tested across the main application flows, including:

* User registration
* Authentication
* Authorization
* CRUD operations
* Client-side validation
* Server-side validation
* Countdown behavior
* Database persistence
* Flyway migrations
* Redis functionality
* Invalid input handling

API endpoints were tested using **Postman**.

---

## 👤 Author

**Milos**

[GitHub](https://github.com/milos970)

## ⭐ Project Status

🚧 **In Development**
