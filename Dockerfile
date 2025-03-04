# Use an official JDK runtime as a parent image
FROM openjdk:17

ARG JAR_FILE=target/*-exec.jar

COPY ${JAR_FILE} inventory-service.jar

# Run the application
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]

# Expose port 8081
EXPOSE 8087