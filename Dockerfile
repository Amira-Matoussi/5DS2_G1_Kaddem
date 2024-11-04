FROM openjdk:8-jdk-alpine
EXPOSE 9096
COPY target/kaddem-1.0.0.jar kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]
