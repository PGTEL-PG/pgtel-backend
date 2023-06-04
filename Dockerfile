# Estágio 1: Construção da aplicação
FROM gradle:7.4-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build --no-daemon

# Estágio 2: Imagem de tempo de execução
FROM openjdk:17-jdk-slim AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]