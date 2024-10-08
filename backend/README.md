# Weather Forecast Service

## Overview

The Weather Forecast Service is a Spring Boot application that provides weather forecasts for specified cities using the OpenWeather API. It retrieves the next three days' high and low temperatures and offers recommendations based on the weather conditions.

## Features

- Fetches weather data from the OpenWeather API.
- Supports offline mode with fallback responses.
- Provides recommendations for users based on weather conditions.
- Implements SOLID principles, 12-Factor App methodology, and HATEOAS.
- Supports Docker for containerization.
- CI/CD pipeline integration with Jenkins.

## Design and Implementation Approach

### Architecture

The service follows a microservices architecture, where the main components are:

- **Controller Layer**: Handles HTTP requests and responses. It interacts with the service layer to fetch weather data and send responses to the client.
- **Service Layer**: Contains the business logic for fetching and processing weather data.
- **Model Layer**: Contains data models representing the responses from the OpenWeather API.

### Design Patterns Used

- **Singleton Pattern**: The `RestTemplate` is instantiated as a singleton to manage connections efficiently.
- **Factory Pattern**: Used to create fallback responses in case of API failures.
- **Adapter Pattern**: Adapts the OpenWeather API response to the internal model representation.
- **Strategy Pattern**: Weather conditions evaluation is done using strategies to determine recommendations based on different weather scenarios.

### Implementation Details

1. **API Integration**: The service integrates with the OpenWeather API to fetch weather data.
2. **Offline Mode**: Implemented as a configuration parameter to simulate responses when the API is unavailable.
3. **Recommendations Logic**: The service evaluates weather conditions (temperature, rain, wind) and provides relevant suggestions to users.
4. **Configuration Management**: Uses `application.properties` for managing API keys, URLs, and offline mode settings.
