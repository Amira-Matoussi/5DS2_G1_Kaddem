#FROM openjdk:8-jdk-alpine
#EXPOSE 9096
#ADD target/kaddem-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
# Use the official OpenJDK 8 image as the base image
FROM openjdk:8-jdk-alpine

# Expose the port your application will run on
EXPOSE 9096

# Set environment variables
ENV NEXUS_USERNAME=admin
ENV NEXUS_PASSWORD=nexus
ENV NEXUS_REPO_URL=http://172.10.0.140:8081/repository/maven-releases/

# Download the JAR file from Nexus and copy it to the container
RUN curl -L -o app.jar -u $NEXUS_USERNAME:$NEXUS_PASSWORD $NEXUS_REPO_URL

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "app.jar"]