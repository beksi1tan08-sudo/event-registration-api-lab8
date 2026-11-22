# Event Registration API (Lab 7)

A simple Spring Boot REST API for event registration demonstrating layered architecture and constructor-based dependency injection.

## Requirements
- JDK Version: Java 17 (or later)
- Build Tool: Gradle (Wrapper included)

## How to Run the Application
1. Clone the repository and open the project folder.
2. Run in terminal/CMD using Gradle Wrapper (Windows):
   gradlew.bat bootRun
3. Open endpoints in a browser:
    - http://localhost:8080/api/hello
    - http://localhost:8080/api/events
    - http://localhost:8080/api/info
    - http://localhost:8080/api/events/1

## Project Architecture
- Model (EventInfo): A Java record representing event data (id, title, location).
- Service (EventService): Annotated with @Service, holds the in-memory list of events and business logic.
- Controller (RegistrationController): Annotated with @RestController, handles incoming HTTP GET requests, receives EventService via constructor injection, and returns JSON responses.