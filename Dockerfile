FROM gradle:7.4-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/api.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/api.jar"]