FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:17-alpine3.14
WORKDIR /app
COPY --from=build /app/target/bisa-api-0.0.1-SNAPSHOT.jar .
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "bisa-api-0.0.1-SNAPSHOT.jar"]