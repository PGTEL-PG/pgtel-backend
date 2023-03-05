FROM openjdk:19-jdk-alpine3.16
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]