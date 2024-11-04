FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/kaddem-0.0.1-RELEASE.jar kaddem-0.0.1-RELEASE.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1-RELEASE.jar"]