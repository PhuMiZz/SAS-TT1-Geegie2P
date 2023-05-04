FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true


FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/*.jar /api/app.jar
WORKDIR /api/
ENTRYPOINT ["java", "-jar", "app.jar"]