FROM gradle:7.4-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar

FROM amazoncorretto:17-alpine-jdk AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/pgtel-backend.jar /app/pgtel-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/pgtel-backend.jar"]