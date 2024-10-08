
# Weather Forecast Service

This project includes both the backend (Spring Boot) and frontend (Angular) services for fetching and displaying weather forecasts.

---

## Backend (Spring Boot) README

### Description
This is a Spring Boot-based microservice that provides weather forecast data for a specific city. It integrates with an external weather API and exposes an API endpoint to get the weather forecast for the next few days.

### Features
- Fetches weather forecast for a given city.
- Provides 3-day forecast including temperature, weather conditions, and suggestions.
- Implements CORS policy for secure cross-origin requests.

### Technologies Used
- Java 17
- Spring Boot 2.7.9
- Maven
- OpenWeather API

### API Endpoints

#### `GET /api/weather/{city}`
Fetches the weather forecast for the specified city.

**Example Response:**
```json
{
  "cod": "200",
  "message": 0,
  "cnt": 10,
  "list": [
    {
      "dt_txt": "2024-10-05 18:00:00",
      "main": {
        "temp": 299.03,
        "temp_min": 296.12,
        "temp_max": 299.03
      },
      "weather": [
        {
          "main": "Rain",
          "description": "Carry an umbrella"
        }
      ],
      "wind": {
        "speed": 1.92
      }
    }
  ],
  "city": {
    "name": "London",
    "country": "GB"
  }
}
```

### Running the Backend Locally

1. Clone the repository:
   ```bash
   git clone <backend-repo-url>
   cd weather-backend
   ```

2. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Access the API via:
   ```
   http://localhost:8080/api/weather/{city}
   ```

### Dockerization

1. Build the Docker image:
   ```bash
   docker build -t weather-backend .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 weather-backend
   ```

### Jenkinsfile

```groovy
pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/your-repo/weather-backend.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Package') {
            steps {
                sh './mvnw package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t weather-backend .'
                sh 'docker run -d -p 8080:8080 weather-backend'
            }
        }
    }
}
```

---

## Frontend (Angular) README

### Description
This is the Angular frontend for the Weather Forecast Service. It fetches data from the backend API and displays the weather forecast for a city.

### Features
- Search for a city to get a 3-day weather forecast.
- Displays temperature in Celsius, weather conditions, and suggestions.
- Responsive UI built with Angular Material.

### Technologies Used
- Angular 18
- Angular Material
- TypeScript
- HTML/CSS
- HTTP Client for API integration

### Running the Frontend Locally

1. Clone the repository:
   ```bash
   git clone <frontend-repo-url>
   cd weather-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the Angular application:
   ```bash
   ng serve
   ```

4. Access the application via:
   ```
   http://localhost:4200
   ```

### Dockerization

1. Build the Docker image:
   ```bash
   docker build -t weather-frontend .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 4200:80 weather-frontend
   ```

### Jenkinsfile

```groovy
pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/your-repo/weather-frontend.git'
            }
        }
        stage('Install Dependencies') {
            steps {
                sh 'npm install'
            }
        }
        stage('Build') {
            steps {
                sh 'ng build --prod'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t weather-frontend .'
                sh 'docker run -d -p 4200:80 weather-frontend'
            }
        }
    }
}
```

### API Integration

The frontend calls the backend API to fetch the weather data for the city entered by the user. Ensure that both frontend and backend are running, and the API endpoint is configured correctly in `weather.service.ts`.

---

### Docker Compose for Both Services

To run both backend and frontend together, you can use Docker Compose:

```yaml
version: '3'
services:
  backend:
    build: ./backend
    ports:
      - "8080:8080"
  frontend:
    build: ./frontend
    ports:
      - "4200:80"
```
