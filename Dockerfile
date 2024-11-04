FROM openjdk:8-jdk-alpine
EXPOSE 9096
COPY target/kaddem-0.0.1.RELEASE.jar kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]

