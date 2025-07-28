# Etapa 1: build
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

# Etapa 2: runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
