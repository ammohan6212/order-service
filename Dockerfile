# Use a multi-stage build to reduce image size

# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/order-service-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-cp", "app.jar", "OrderServiceApplication"]
