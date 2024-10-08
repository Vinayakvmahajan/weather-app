# Use a base image with JDK
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
COPY src ./src
RUN ./mvnw dependency:go-offline -B

# Package the application
RUN ./mvnw package -DskipTests

# Start a new stage for the runtime image
FROM openjdk:17-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/weatherapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
