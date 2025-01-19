# Use an official JDK runtime as a parent image
FROM amazoncorretto:17.0.13-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/my-ecp-ecom-0.0.1-SNAPSHOT.jar inventory-service.jar

# Expose port 8081
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]