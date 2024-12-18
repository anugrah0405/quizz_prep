# Quiz Application

This is a Spring Boot-based Quiz Application that provides an API for managing and taking quizzes. It supports operations such as starting a quiz, fetching random questions, submitting answers, and viewing quiz statistics.

---

## Features

- **Start a Quiz**: Reset session stats and begin a new quiz.
- **Fetch Random Questions**: Retrieve random quiz questions from the database.
- **Submit Answers**: Submit individual answers and get immediate feedback.
- **Submit All Answers**: Submit multiple answers in bulk.
- **Quiz Statistics**: View session statistics, including correct and incorrect answers.
- **Database Seeding**: Preloaded with sample questions for testing.

---

## Project Setup

### Prerequisites

1. **Java Development Kit (JDK 17)**
2. **Maven (latest version)**
3. **Postman** (for testing API endpoints)
4. **H2 Database Console** (enabled by default)

### Installation Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd quizz_prep
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
    - H2 Console: [http://localhost:8084/h2-console](http://localhost:8084/h2-console)
    - API Base URL: [http://localhost:8084/api/quiz](http://localhost:8084/api/quiz)

---

## Configuration

The application uses the H2 in-memory database. Configuration details can be found in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create
logging.level.org.springframework.orm.jpa=DEBUG
server.port=8084
```

---

## Endpoints

### 1. Start a Quiz
- **URL**: `/api/quiz/start`
- **Method**: POST
- **Description**: Resets session stats and starts a new quiz.
- **Sample Response**:
  ```json
  {
    "id": 1,
    "username": "default_user",
    "correctAnswers": 0,
    "incorrectAnswers": 0,
    "totalAnswered": 0
  }
  ```

### 2. Get Random Question
- **URL**: `/api/quiz/question`
- **Method**: GET
- **Description**: Fetches a random question.
- **Sample Response**:
  ```json
  {
    "id": 1,
    "text": "What is the capital of France?",
    "options": ["Paris", "Berlin", "Rome", "Madrid"],
    "correctAnswer": "Paris"
  }
  ```

### 3. Submit an Answer
- **URL**: `/api/quiz/submit`
- **Method**: POST
- **Params**:
    - `questionId` (Long): The ID of the question being answered.
    - `answer` (String): The answer provided.
- **Description**: Submits an answer for a question.
- **Sample Response**:
  ```json
  "Correct!"
  ```
- **Incorrect Answer Example**:
    - **Params**:
        - `questionId`: 3
        - `answer`: Tolstoy
    - **Response**:
      ```json
      "Incorrect! Correct answer is Shakespeare!"
      ```
- **Correct Answer Example**:
    - **Params**:
        - `questionId`: 4
        - `answer`: Jupiter
    - **Response**:
      ```json
      "Correct!"
      ```

### 4. Submit All Answers
- **URL**: `/api/quiz/submit-all`
- **Method**: POST
- **Body** (JSON Array):
  ```json
  [
    {
        "questionId": 1,
        "answer": "Paris"
    },
    {
        "questionId": 2,
        "answer": "4"
    },
    {
        "questionId": 3,
        "answer": "Shakespeare"
    },
    {
        "questionId": 4,
        "answer": "Jupiter"
    },
    {
        "questionId": 5,
        "answer": "10"
    },
    {
        "questionId": 6,
        "answer": "Pablo Picasso"
    },
    {
        "questionId": 7,
        "answer": "Tokyo"
    },
    {
        "questionId": 8,
        "answer": "120°C"
    },
    {
        "questionId": 9,
        "answer": "Jupiter"
    },
    {
        "questionId": 10,
        "answer": null
    }
  ]
  ```
- **Description**: Submits answers for multiple questions.
- **Sample Response**:
  ```json
  "You answered 5 questions correctly and 5 incorrectly."
  ```

### 5. Quiz Stats
- **URL**: `/api/quiz/stats`
- **Method**: GET
- **Description**: Fetches quiz session statistics.
- **Sample Response**:
  ```json
  {
    "incorrectAnswers": 5,
    "totalAnswered": 10,
    "correctAnswers": 5
  }
  ```

### 6. Get All Questions
- **URL**: `/api/quiz/all-questions`
- **Method**: GET
- **Description**: Fetches all questions with their options.
- **Sample Response**:
  ```json
  [
    {
        "1": {
            "question": "What is the capital of France?",
            "options": [
                "Paris",
                "Berlin",
                "Rome",
                "Madrid"
            ]
        }
    },
    {
        "2": {
            "question": "What is 2 + 2?",
            "options": [
                "3",
                "4",
                "5",
                "6"
            ]
        }
    },
    {
        "3": {
            "question": "Who wrote 'Hamlet'?",
            "options": [
                "Shakespeare",
                "Dickens",
                "Austen",
                "Tolstoy"
            ]
        }
    },
    {
        "4": {
            "question": "What is the largest planet in our solar system?",
            "options": [
                "Earth",
                "Jupiter",
                "Mars",
                "Venus"
            ]
        }
    },
    {
        "5": {
            "question": "What is the square root of 64?",
            "options": [
                "6",
                "8",
                "10",
                "12"
            ]
        }
    },
    {
        "6": {
            "question": "Who painted the Mona Lisa?",
            "options": [
                "Leonardo da Vinci",
                "Vincent van Gogh",
                "Pablo Picasso",
                "Claude Monet"
            ]
        }
    },
    {
        "7": {
            "question": "What is the capital of Japan?",
            "options": [
                "Seoul",
                "Tokyo",
                "Beijing",
                "Bangkok"
            ]
        }
    },
    {
        "8": {
            "question": "What is the boiling point of water?",
            "options": [
                "90°C",
                "100°C",
                "110°C",
                "120°C"
            ]
        }
    },
    {
        "9": {
            "question": "Which planet is known as the Red Planet?",
            "options": [
                "Earth",
                "Mars",
                "Jupiter",
                "Saturn"
            ]
        }
    },
    {
        "10": {
            "question": "Who wrote '1984'?",
            "options": [
                "George Orwell",
                "Aldous Huxley",
                "J.K. Rowling",
                "Ernest Hemingway"
            ]
        }
    }
  ]
  ```

---

## Testing with Postman

### Import the Postman Collection
1. Open Postman.
2. Click on **Import**.
3. Select the `quiz_app_postman_collection.json` file located in the `Postman collection/` folder.
4. Use the predefined requests to test the API endpoints.

---

## Built With

- **Spring Boot** - Backend framework
- **H2 Database** - In-memory database for testing
- **Maven** - Dependency management

---

## Notes

- The application seeds the database with sample questions on startup.
- Default user: `default_user`.
- Modify `application.properties` for custom database configurations.
- Assumption: One user in each session can answer a question multiple times. So, even though there are ten questions in database, some values like totalAnswered can go beyond 10. 
  - Sample Response for GET stats:
  ```json
  {
    "incorrectAnswers": 5,
    "totalAnswered": 12,
    "correctAnswers": 7
  }
  ```

---

## License

This project is licensed under the MIT License.

