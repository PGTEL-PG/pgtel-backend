FROM gradle:7.4-jdk17 AS builder
WORKDIR /opt/app
COPY build.gradle settings.gradle ./
COPY ./src ./src
RUN gradle bootJar

FROM openjdk:17-jdk-slim AS runner
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/build/libs/*.jar /opt/app/
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]