# Task Management API

A Spring Boot REST API for managing task lists and tasks with Docker support.

## Prerequisites

- Docker and Docker Compose installed
- Java 17+ (for local development)
- Maven (for local development)

## Getting Started

### Running with Docker

```bash
docker-compose up
```

The API will be available at `http://localhost:8080`

### Running Locally

```bash
./mvnw spring-boot:run
```

## API Documentation

### Base URL
```
http://localhost:8080
```
**or**

``https://jsb-tasks.onrender.com/``

## Task Lists API

### Get All Task Lists
- **Endpoint:** `GET /task-lists`
- **Description:** Returns a list of all task lists
- **Response:**
```json
[
    {
        "id": "9dc25b91-5f0e-4e7d-9d46-11013b803064",
        "title": "Task List 1",
        "description": "This is the task list number 1",
        "count": 5,
        "progress": 0.0,
        "tasks": [
            {
                "id": "af12cdde-0220-40bc-8dff-40a42919807a",
                "title": "Task 1",
                "description": "This is the task 1",
                "dueDate": null,
                "priority": "HIGH",
                "status": "OPEN"
            }
        ]
    }
]
```

### Create Task List
- **Endpoint:** `POST /task-lists`
- **Description:** Creates a new task list
- **Request Body:**
```json
{
    "title": "This is a title",
    "description": "description"
}
```
- **Response:**
```json
{
    "id": "42c37f56-0333-4f88-b8ee-52cf43bcd5b1",
    "title": "New task list",
    "description": "This is a new task list",
    "count": 0,
    "progress": 0.0,
    "tasks": null
}
```

### Get Task List by ID
- **Endpoint:** `GET /task-lists/{task_list_id}`
- **Description:** Returns a specific task list by ID
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list

### Update Task List
- **Endpoint:** `PUT /task-lists/{task_list_id}`
- **Description:** Updates an existing task list
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list
- **Request Body:**
```json
{
    "title": "Updated title",
    "description": "Updated description"
}
```

### Delete Task List
- **Endpoint:** `DELETE /task-lists/{task_list_id}`
- **Description:** Deletes a task list by ID
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list to delete

## Tasks API

### Get All Tasks in a Task List
- **Endpoint:** `GET /task-lists/{task_list_id}/tasks`
- **Description:** Returns all tasks within a specific task list
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list

### Create Task
- **Endpoint:** `POST /task-lists/{task_list_id}/tasks`
- **Description:** Creates a new task in a specific task list
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list
- **Request Body:**
```json
{
    "title": "This is a title",
    "description": "description",
    "dueDate": "2024-12-31T23:59:59",
    "status": "OPEN",
    "priority": "HIGH"
}
```

### Get Task by ID
- **Endpoint:** `GET /task-lists/{task_list_id}/tasks/{task_id}`
- **Description:** Returns a specific task by ID
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list
  - `task_id` (UUID): The ID of the task

### Update Task
- **Endpoint:** `PUT /task-lists/{task_list_id}/tasks/{task_id}`
- **Description:** Updates an existing task
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list
  - `task_id` (UUID): The ID of the task
- **Request Body:**
```json
{
    "title": "Updated task title",
    "description": "Updated description",
    "dueDate": "2024-12-31T23:59:59",
    "status": "CLOSED",
    "priority": "MEDIUM"
}
```

### Delete Task
- **Endpoint:** `DELETE /task-lists/{task_list_id}/tasks/{task_id}`
- **Description:** Deletes a task by ID
- **Parameters:**
  - `task_list_id` (UUID): The ID of the task list
  - `task_id` (UUID): The ID of the task to delete

## Data Models

### Task List
- `id` (UUID): Unique identifier
- `title` (String): Task list title
- `description` (String): Task list description
- `count` (Integer): Number of tasks in the list
- `progress` (Double): Progress percentage (0.0 to 1.0)
- `tasks` (Array): List of tasks

### Task
- `id` (UUID): Unique identifier
- `title` (String): Task title (required)
- `description` (String): Task description
- `dueDate` (LocalDateTime): Due date and time
- `status` (String): Task status - `OPEN` or `CLOSED`
- `priority` (String): Task priority - `HIGH`, `MEDIUM`, or `LOW`

## Error Handling

The API returns appropriate HTTP status codes:
- `200 OK`: Successful GET, PUT requests
- `201 Created`: Successful POST requests
- `204 No Content`: Successful DELETE requests
- `400 Bad Request`: Invalid request data
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server errors

## Development

### Project Structure
- `src/main/java/`: Java source code
- `src/main/resources/`: Configuration files
- `docker-compose.yml`: Docker configuration
- `pom.xml`: Maven dependencies and build configuration

### Building the Project
```bash
./mvnw clean install
```

### Running Tests
```bash
./mvnw test
```