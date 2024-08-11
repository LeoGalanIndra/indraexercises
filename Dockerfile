# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot JAR file into the container
COPY target/InventoryService-0.0.1-SNAPSHOT.jar /app/InventoryService-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot application will run on
EXPOSE 8081

# Command to run your Spring Boot application
CMD ["java", "-jar", "InventoryService-0.0.1-SNAPSHOT.jar"]