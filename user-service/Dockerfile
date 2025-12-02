# 1. Start with a base OS that has Java 17 installed
FROM amazoncorretto:17

# 2. Copy the jar file (the built app) from our computer into the container
# We will rename it to app.jar inside the box
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# 3. Expose port 8080 (so we can access it)
EXPOSE 8080

# 4. The command to run when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]