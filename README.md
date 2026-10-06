# Event Registration System

A RESTful backend application developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL** for managing users, events, and event registrations.

The application provides REST APIs to create users and events, register users for events, view registrations, and cancel registrations. It also includes business validations such as preventing duplicate registrations and checking event capacity.

---

## 🚀 Features

- Create users
- View all users
- View user by ID
- Create events
- View all events
- View event by ID
- Register users for events
- Prevent duplicate registrations
- Validate event capacity
- View registrations by user
- View registrations by event
- Cancel registrations
- MySQL database integration
- Automatic table creation/update using Hibernate
- REST API testing using Postman

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Backend programming |
| Spring Boot | Backend application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access |
| Hibernate | ORM and object-relational mapping |
| MySQL | Relational database |
| Maven | Dependency management |
| Postman | API testing |
| Eclipse | Development environment |

---

## 🏗️ Application Architecture

The application follows a layered architecture:

```text
                    Client
                  (Postman)
                      |
                      | HTTP Request
                      ↓
              ┌─────────────────┐
              │   Controller    │
              └─────────────────┘
                      |
                      ↓
              ┌─────────────────┐
              │     Service     │
              └─────────────────┘
                      |
                      ↓
              ┌─────────────────┐
              │   Repository    │
              └─────────────────┘
                      |
                      ↓
              ┌─────────────────┐
              │ JPA / Hibernate │
              └─────────────────┘
                      |
                      ↓
              ┌─────────────────┐
              │      MySQL      │
              └─────────────────┘
````

### Request Flow

```text
HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Hibernate / JPA
     ↓
MySQL Database
```

---

## 📁 Project Structure

```text
src/main/java/com/example/eventregistration
│
├── EventregistrationApplication.java
│
├── controller
│   ├── UserController.java
│   ├── EventController.java
│   └── RegistrationController.java
│
├── entity
│   ├── User.java
│   ├── Event.java
│   └── Registration.java
│
├── repository
│   ├── UserRepository.java
│   ├── EventRepository.java
│   └── RegistrationRepository.java
│
└── service
    ├── UserService.java
    ├── EventService.java
    └── RegistrationService.java
```

---

# 🗄️ Database Design

The application uses MySQL with the following database:

```text
eventregistration
```

The application contains three main entities:

### User

```text
User
----------------
id
name
email
password
```

### Event

```text
Event
----------------
id
name
description
date
location
capacity
```

### Registration

```text
Registration
----------------
id
user_id
event_id
registration_date
```

### Entity Relationship

```text
User 1 ───────── * Registration * ───────── 1 Event
```

A user can register for multiple events.

An event can have multiple users.

The `Registration` entity connects the `User` and `Event` entities.

---

# 🔗 Entity Relationships

The `Registration` entity uses JPA relationships:

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

and:

```java
@ManyToOne
@JoinColumn(name = "event_id")
private Event event;
```

This creates foreign-key relationships between the tables.

```text
registration.user_id
        ↓
     user.id

registration.event_id
        ↓
     event.id
```

---

# ⚙️ Database Configuration

## 1. Create the Database

Open MySQL Workbench and execute:

```sql
CREATE DATABASE eventregistration;
```

The application tables are automatically created/updated by Hibernate.

---

## 2. Configure `application.properties`

```properties
spring.application.name=eventregistration

spring.datasource.url=jdbc:mysql://localhost:3306/eventregistration
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Replace:

```text
YOUR_MYSQL_PASSWORD
```

with your MySQL password.

### Hibernate Configuration

The following property:

```properties
spring.jpa.hibernate.ddl-auto=update
```

allows Hibernate to automatically create and update tables based on the entity classes.

Only the database is created manually:

```sql
CREATE DATABASE eventregistration;
```

---

# 🌐 REST API Endpoints

## 👤 User APIs

| Method | Endpoint          | Description    |
| ------ | ----------------- | -------------- |
| POST   | `/api/users`      | Create a user  |
| GET    | `/api/users`      | Get all users  |
| GET    | `/api/users/{id}` | Get user by ID |

---

## 🎫 Event APIs

| Method | Endpoint           | Description     |
| ------ | ------------------ | --------------- |
| POST   | `/api/events`      | Create an event |
| GET    | `/api/events`      | Get all events  |
| GET    | `/api/events/{id}` | Get event by ID |

---

## 📝 Registration APIs

| Method | Endpoint                                               | Description              |
| ------ | ------------------------------------------------------ | ------------------------ |
| POST   | `/api/registrations?userId={userId}&eventId={eventId}` | Register a user          |
| GET    | `/api/registrations/user/{userId}`                     | Get user's registrations |
| GET    | `/api/registrations/event/{eventId}`                   | Get event registrations  |
| DELETE | `/api/registrations/{registrationId}`                  | Cancel registration      |

---

# 🧪 API Testing with Postman

The REST APIs can be tested using Postman.

Base URL:

```text
http://localhost:8080
```

---

## 1. Create User

### Request

```http
POST http://localhost:8080/api/users
```

### Body

Select:

```text
Body → raw → JSON
```

```json
{
  "name": "John",
  "email": "john@gmail.com",
  "password": "12345"
}
```

### Example Response

```json
{
  "id": 1,
  "name": "John",
  "email": "john@gmail.com",
  "password": "12345"
}
```
<img width="1366" height="721" alt="image (3)" src="https://github.com/user-attachments/assets/7276915b-0205-41bd-b820-11a29f29fbcd" />

---

## 2. Get All Users

### Request

```http
GET http://localhost:8080/api/users
```

No request body is required.

<img width="1366" height="721" alt="image (4)" src="https://github.com/user-attachments/assets/baea090a-30c1-4af4-bce0-5cdf399dd9f6" />


---

## 3. Get User by ID

### Request

```http
GET http://localhost:8080/api/users/2
```

No request body is required.

<img width="1366" height="721" alt="image (5)" src="https://github.com/user-attachments/assets/51d6abb6-70f1-481e-a093-b098e9f34f8c" />


---

# 🎫 Event APIs

## 4. Create Event

### Request

```http
POST http://localhost:8080/api/events
```

### Body

```json
{
  "name": "Java Spring Boot Workshop",
  "description": "Learn Spring Boot and REST API development",
  "date": "2026-10-15",
  "location": "Chennai",
  "capacity": 2
}
```

### Example Response

```json
{
  "id": 1,
  "name": "Java Spring Boot Workshop",
  "description": "Learn Spring Boot and REST API development",
  "date": "2026-10-15",
  "location": "Chennai",
  "capacity": 2
}
```
<img width="1366" height="725" alt="image (6)" src="https://github.com/user-attachments/assets/7fe77e26-accd-4c1b-a27c-3985206f6bca" />

---

## 5. Get All Events

### Request

```http
GET http://localhost:8080/api/events
```
<img width="1366" height="717" alt="image (7)" src="https://github.com/user-attachments/assets/a0a572fa-427b-466a-9e1a-b8952f0193af" />

---

## 6. Get Event by ID

### Request

```http
GET http://localhost:8080/api/events/1
```
<img width="1366" height="721" alt="image (8)" src="https://github.com/user-attachments/assets/a6cc7301-05b5-4ec2-ba86-5c7884dcccd5" />

---

# 📝 Registration APIs

## 7. Register User for Event

Assuming:

```text
User ID  = 1
Event ID = 1
```

### Request

```http
POST http://localhost:8080/api/registrations?userId=1&eventId=1
```

No request body is required.

The `userId` and `eventId` are sent as request parameters.

The controller receives them using:

```java
@RequestParam Long userId
@RequestParam Long eventId
```
<img width="1366" height="717" alt="image (9)" src="https://github.com/user-attachments/assets/c934c4da-7a2e-442f-8e86-0ab4a9da5257" />

---

## 8. Get User Registrations

For User ID `1`:

```http
GET http://localhost:8080/api/registrations/user/3
```

This returns all events registered by User 3.

<img width="1366" height="717" alt="image (11)" src="https://github.com/user-attachments/assets/ab856bde-0b1c-466a-9792-9598319dbaa3" />

---

## 9. Get Event Registrations

For Event ID `1`:

```http
GET http://localhost:8080/api/registrations/event/1
```

This returns all users registered for Event 1.

<img width="1366" height="725" alt="image (12)" src="https://github.com/user-attachments/assets/9408eefa-dd75-4767-9740-bf7399925e50" />

---

## 10. Cancel Registration

If the registration ID is `1`:

```http
DELETE http://localhost:8080/api/registrations/1
```

Expected response:

```text
Registration cancelled successfully
```

---

# 🔄 Registration Flow

When the following request is sent:

```http
POST /api/registrations?userId=1&eventId=1
```

the application follows this flow:

```text
Postman
   |
   ↓
RegistrationController
   |
   ↓
@RequestParam
   |
   ├── userId = 1
   └── eventId = 1
   |
   ↓
RegistrationService
   |
   ├── Find User
   |
   ├── Find Event
   |
   ├── Check duplicate registration
   |
   ├── Check event capacity
   |
   ├── Create Registration
   |
   └── Save Registration
   |
   ↓
RegistrationRepository
   |
   ↓
Hibernate / JPA
   |
   ↓
MySQL
```

---

# 🧠 Business Logic

## 1. Duplicate Registration Prevention

A user cannot register for the same event more than once.

Before creating a registration, the application checks:

```java
registrationRepository
    .existsByUserIdAndEventId(userId, eventId);
```

If the user is already registered:

```text
User is already registered for this event
```

is returned.

---

## 2. Event Capacity Validation

Each event has a maximum capacity.

For example:

```text
Event Capacity = 2
```

After:

```text
User 1 → Event 1
User 2 → Event 1
```

the event is full.

A third user cannot register.

The application checks:

```java
long registeredCount =
        registrationRepository.countByEventId(eventId);

if (registeredCount >= event.getCapacity()) {
    throw new RuntimeException("Event is full");
}
```

---

## 3. User Validation

Before registration, the application checks whether the user exists.

```java
userRepository.findById(userId)
```

If the user does not exist:

```text
User not found
```

---

## 4. Event Validation

Before registration, the application checks whether the event exists.

```java
eventRepository.findById(eventId)
```

If the event does not exist:

```text
Event not found
```

---

# 📦 Repository Layer

The project uses Spring Data JPA repositories.

### UserRepository

```java
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
```

### EventRepository

```java
public interface EventRepository extends JpaRepository<Event, Long> {
}
```

### RegistrationRepository

```java
public interface RegistrationRepository
        extends JpaRepository<Registration, Long> {

    List<Registration> findByUserId(Long userId);

    List<Registration> findByEventId(Long eventId);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

    long countByEventId(Long eventId);
}
```

Spring Data JPA automatically provides common database operations such as:

```text
save()
findAll()
findById()
existsById()
deleteById()
```

---

# 🔄 Data Flow

The application follows:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Hibernate
    ↓
MySQL
```

### Controller

Handles HTTP requests and responses.

### Service

Contains business logic and validation.

### Repository

Communicates with the database through Spring Data JPA.

### Hibernate

Maps Java objects to database tables and generates SQL.

### MySQL

Stores the application data.

---

# ▶️ How to Run the Project

## Prerequisites

Install:

* Java JDK
* Maven
* MySQL Server
* MySQL Workbench
* Eclipse
* Postman

---

## Step 1 — Clone the Repository

```bash
git clone <your-repository-url>
```

---

## Step 2 — Open the Project

Open the project in Eclipse.

---

## Step 3 — Create MySQL Database

Run:

```sql
CREATE DATABASE eventregistration;
```

---

## Step 4 — Configure MySQL Credentials

Open:

```text
src/main/resources/application.properties
```

Update:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

---

## Step 5 — Run the Application

Run:

```text
EventregistrationApplication.java
```

as a Spring Boot application.

The application runs on:

```text
http://localhost:8080
```

---

## Step 6 — Verify Database Tables

Open MySQL Workbench:

```sql
USE eventregistration;

SHOW TABLES;
```

Hibernate should create/update the required tables automatically.

<img width="1366" height="717" alt="image (13)" src="https://github.com/user-attachments/assets/8b4f26da-f107-4f72-9ae5-9b42925132f9" />

<img width="1366" height="725" alt="image (14)" src="https://github.com/user-attachments/assets/607a7dcb-da1a-43f1-95ff-8354f1200878" />

<img width="1366" height="713" alt="image (15)" src="https://github.com/user-attachments/assets/a288536c-5769-46f3-92cc-e2fbe2f6c4ba" />

---

# 🧪 Recommended Testing Sequence

Use Postman in the following order:

```text
1. Create User
       ↓
2. Create Event
       ↓
3. Get All Users
       ↓
4. Get All Events
       ↓
5. Register User for Event
       ↓
6. Get User Registrations
       ↓
7. Get Event Registrations
       ↓
8. Test Duplicate Registration
       ↓
9. Test Event Capacity
       ↓
10. Cancel Registration
```

---

# ✅ Validation Scenarios

| Test Case                      | Expected Result                 |
| ------------------------------ | ------------------------------- |
| Create valid user              | User created                    |
| Create duplicate email         | Email already registered        |
| Create valid event             | Event created                   |
| Create event with capacity `0` | Request rejected                |
| Register valid user            | Registration created            |
| Register same user again       | Duplicate registration rejected |
| Register when event is full    | Registration rejected           |
| Get user registrations         | User's registrations returned   |
| Get event registrations        | Event's registrations returned  |
| Cancel registration            | Registration deleted            |

---

# 📚 Key Concepts Demonstrated

This project demonstrates practical knowledge of:

* Java
* Spring Boot
* REST API development
* Spring Web
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* MySQL
* Entity relationships
* Primary keys
* Foreign keys
* `@Entity`
* `@Id`
* `@GeneratedValue`
* `@ManyToOne`
* `@JoinColumn`
* `@RestController`
* `@RequestMapping`
* `@PostMapping`
* `@GetMapping`
* `@DeleteMapping`
* `@RequestBody`
* `@RequestParam`
* `@PathVariable`
* Service Layer
* Repository Layer
* Business logic validation
* Postman API testing

---

# 👩‍💻 Author

**Mahaswetha R**

Java Full Stack Developer

Github Link: github.com/mahaswetha05
