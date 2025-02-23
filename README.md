# Putholi2025

**Project Documentation**

**Project Overview**

This document provides an overview of the technologies and tools used in the project, including backend, frontend, build tools, and deployment strategies.

**Technologies Used**

Backend

Spring Boot: Used for developing the backend REST APIs.

Maven: Used for dependency management and project build automation.

Frontend

Angular 10: Used for developing the frontend application.

Server

Node.js v14: Used for handling server-side logic and API interactions.

Development Environment

Prerequisites

Ensure the following dependencies are installed:

Java 1.7v

Node.js v14 and npm (for frontend and server dependencies)

Angular CLI (for managing the Angular project)

Maven (for building and running the backend)

Setting Up the Project

Clone the Repository:

git clone <repository-url>
cd <project-directory>

Backend Setup (Spring Boot + Maven):

cd backend
mvn clean install

Frontend Setup (Angular 10):

cd <frontend-project-directory>
npm install
ng serve


Deployment Strategy

Version Control: The project is hosted on GitHub.

Build & Deployment:

Spring Boot applications are packaged as JAR files and deployed on a server.

Angular applications are built using ng build --prod and deployed to a web server.


Additional Information

Database: PostgreSQL (if applicable)



