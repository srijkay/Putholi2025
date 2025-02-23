**Project Documentation**

## Project Overview

This document provides an overview of the technologies and tools used in the project, including backend, frontend, build tools, and deployment strategies.

## Technologies Used

### Backend

- **Spring Boot**: Used for developing the backend REST APIs.
- **Maven**: Used for dependency management and project build automation.

### Frontend

- **Angular 10**: Used for developing the frontend application.

### Server

- **Node.js v14**: Used for handling server-side logic and API interactions.

## Development Environment

### Prerequisites

Ensure the following dependencies are installed:

- **Java 11 or later** (for Spring Boot)
- **Node.js v14** and **npm** (for frontend and server dependencies)
- **Angular CLI** (for managing the Angular project)
- **Maven** (for building and running the backend)

### Setting Up the Project

1. **Clone the Repository:**

   ```sh
   git clone <repository-url>
   cd <project-directory>
   ```

2. **Backend Setup (Spring Boot + Maven):**

   ```sh
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

3. **Frontend Setup (Angular 10):**

   ```sh
   cd frontend
   npm install
   ng serve
   ```

4. **Node.js Server Setup:**

   ```sh
   cd server
   npm install
   node server.js
   ```

## Deployment Strategy

- **Version Control:** The project is hosted on **GitHub**.
- **Build & Deployment:**
  - Spring Boot applications are packaged as JAR files and deployed on a server.
  - Angular applications are built using `ng build --prod` and deployed to a web server.
  - Node.js server is hosted on a cloud platform or an internal server.

## Additional Information

- **Database:** PostgreSQL (if applicable)
- **Authentication & Security:** JWT-based authentication (if implemented)
- **API Documentation:** Swagger/OpenAPI (if applicable)

For further details, refer to the repository README or project wiki.

