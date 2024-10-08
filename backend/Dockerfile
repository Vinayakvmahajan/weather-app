# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory for the backend
WORKDIR /app/backend

# Copy the packaged jar file
COPY target/weatherapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the service port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]