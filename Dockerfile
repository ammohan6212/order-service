# Use Eclipse Temurin official Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the jar file
COPY target/hello-world-spring-boot-1.0.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
