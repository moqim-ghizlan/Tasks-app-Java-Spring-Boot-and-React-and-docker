# Task Management API

## Description
Ce projet est une API REST construite avec **Spring Boot**, **Java 17**, **JPA/Hibernate** et **PostgreSQL**.
Elle permet de gérer des listes de tâches et des tâches individuelles avec des opérations CRUD complètes,
incluant la gestion des priorités, statuts et dates d'échéance.

---

## Objectifs
- Développer une API REST robuste avec Spring Boot.
- Implémenter une architecture en couches (Controller, Service, Repository).
- Gérer la persistance des données avec JPA/Hibernate.
- Utiliser Docker pour la containerisation et le déploiement.
- Appliquer les bonnes pratiques de développement Java/Spring.

---

## Structure du Projet
```
src/main/java/
├── controllers/
│    ├── TaskController.java           → Endpoints pour les tâches
│    ├── TaskListController.java       → Endpoints pour les listes de
│    └── GlobalExceptionHandler.java   → Handler d'exception globale
├── domain/
│    ├── dto/
│    │    ├── TaskDto.java             → DTO pour les tâches
│    │    └── TaskListDto.java         → DTO pour les listes de tâches
│    ├── entities/
│    │    ├── Task.java                → Entité JPA Task
│    │    ├── TaskList.java            → Entité JPA TaskList
│    │    ├── TaskStatus.java          → Enum des statuts
│    │    └── TaskPriority.java        → Enum des priorités
├── mappers/
│    ├── TaskMapper.java               → Mapping Task/TaskDto
│    └── TaskListMapper.java           → Mapping TaskList/TaskListDto
├── repositories/
│    ├── TaskRepository.java           → Repository JPA pour Task
│    └── TaskListRepository.java       → Repository JPA pour TaskList
├── services/
│    ├── TaskService.java              → Interface service Task
│    ├── TaskServiceImpl.java          → Implémentation service Task
│    ├── TaskListService.java          → Interface service TaskList
│    └── TaskListServiceImpl.java      → Implémentation service TaskList
└── TasksApplication.java              → Point d'entrée Spring Boot
```

---

## Installation et Prérequis

### Prérequis
- **Java 17+**
- **Maven 3.6+**
- **Docker & Docker Compose**
- **PostgreSQL** (si exécution locale sans Docker)

### Étape 1 : Cloner le Répertoire
```bash
git clone https://github.com/moqim-ghizlan/Tasks-app-Java-Spring-Boot-and-React-and-docker
cd tasks
```

### Étape 2 : Lancer avec Docker (Recommandé)
```bash
docker-compose up
```

### Étape 3 : Lancer en Local (Alternative)
```bash
# Compiler le projet
./mvnw clean install

# Lancer l'application
./mvnw spring-boot:run
```

L'API sera accessible à l'adresse :
👉 [http://localhost:8080](http://localhost:8080)

---

## Fonctionnalités

### Gestion des Listes de Tâches
- ✅ Créer une nouvelle liste de tâches
- ✅ Récupérer toutes les listes de tâches
- ✅ Récupérer une liste de tâches par ID
- ✅ Mettre à jour une liste de tâches
- ✅ Supprimer une liste de tâches

### Gestion des Tâches
- ✅ Créer une nouvelle tâche dans une liste
- ✅ Récupérer toutes les tâches d'une liste
- ✅ Récupérer une tâche par ID
- ✅ Mettre à jour une tâche existante
- ✅ Supprimer une tâche
- ✅ Gestion des priorités (HIGH, MEDIUM, LOW)
- ✅ Gestion des statuts (OPEN, CLOSED)
- ✅ Dates d'échéance optionnelles

---

## API Endpoints

### Task Lists
```http
GET    /task-lists              # Récupérer toutes les listes
POST   /task-lists              # Créer une nouvelle liste
GET    /task-lists/{id}         # Récupérer une liste par ID
PUT    /task-lists/{id}         # Mettre à jour une liste
DELETE /task-lists/{id}         # Supprimer une liste
```

### Tasks
```http
GET    /task-lists/{listId}/tasks           # Récupérer toutes les tâches d'une liste
POST   /task-lists/{listId}/tasks           # Créer une nouvelle tâche
GET    /task-lists/{listId}/tasks/{taskId}  # Récupérer une tâche par ID
PUT    /task-lists/{listId}/tasks/{taskId}  # Mettre à jour une tâche
DELETE /task-lists/{listId}/tasks/{taskId}  # Supprimer une tâche
```

---

## Modèles de Données

### TaskList
```json
{
  "id": "uuid",
  "title": "string",
  "description": "string",
  "count": "integer",
  "progress": "double",
  "tasks": ["Task[]"]
}
```

### Task
```json
{
  "id": "uuid",
  "title": "string",
  "description": "string",
  "dueDate": "LocalDateTime",
  "priority": "HIGH|MEDIUM|LOW",
  "status": "OPEN|CLOSED"
}
```

---

## Technologies Utilisées
- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - Persistance des données
- **PostgreSQL** - Base de données
- **Maven** - Gestion des dépendances
- **Docker** - Containerisation
- **Java 17** - Langage de programmation

---

## Commandes Utiles
```bash
# Compiler le projet
./mvnw clean compile

# Exécuter les tests
./mvnw test

# Créer le package JAR
./mvnw clean package

# Lancer avec Docker
docker-compose up -d

# Arrêter les conteneurs
docker-compose down
```

---

## Auteur
**Moqim Ghizlan**

---

## Licence
Ce projet est sous licence libre.