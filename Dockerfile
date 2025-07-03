# ------------ Stage 1: Build the application ------------
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

# Set working directory inside container
WORKDIR /app

# Copy all project files
COPY . .

# Download dependencies and build the JAR
RUN mvn clean package -DskipTests

# ------------ Stage 2: Run the application ------------
FROM eclipse-temurin:17-jdk-alpine

# Create non-root user for better security
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose app port
EXPOSE 8080

# Change to non-root user
USER spring

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
