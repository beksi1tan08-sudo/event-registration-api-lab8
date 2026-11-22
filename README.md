# Event Registration API (Lab 8)

A Spring Boot REST API supporting full CRUD operations (`GET`, `POST`, `PUT`, `DELETE`) and title-based search using an in-memory data store.

## Requirements
- JDK Version: Java 17 (or later)
- Build Tool: Gradle (Wrapper included)

## How to Run the Application
1. Clone the repository and open the project folder.
2. Run in terminal/CMD using Gradle Wrapper (Windows):
   gradlew.bat bootRun
3. Base URL: `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description | Status Codes |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/events` | Get all events (supports `?title=...` search) | `200 OK` |
| `GET` | `/api/events/{id}` | Get event by ID | `200 OK`, `404 Not Found` |
| `POST` | `/api/events` | Create a new event | `201 Created`, `400 Bad Request` |
| `PUT` | `/api/events/{id}` | Update an existing event | `200 OK`, `400 Bad Request`, `404 Not Found` |
| `DELETE` | `/api/events/{id}` | Delete an event | `204 No Content`, `404 Not Found` |

## Example Request Body (`POST` / `PUT`)
{
"title": "Web Design Workshop",
"location": "Room 101"
}