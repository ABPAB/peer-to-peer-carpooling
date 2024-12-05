FROM gradle:8.3-jdk17 as builder
 
# Set the working directory inside the container
WORKDIR /app
 
# Copy Gradle configuration files and project source code
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
COPY src src
 
# Make the gradlew file executable
RUN chmod +x gradlew
 
# Build the application
RUN ./gradlew clean build --no-daemon
 
# Stage 2: Run the Spring Boot application
FROM eclipse-temurin:17-jre
 
# Set the working directory inside the container
WORKDIR /app
 
# Copy the built application JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar
 
 
# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]