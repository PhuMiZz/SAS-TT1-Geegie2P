FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY mvnw /home/app/mvnw
COPY mvnw.cmd /home/app/mvnw.cmd
COPY .mvn /home/app/.mvn
WORKDIR /home/app
RUN ./mvnw clean package -Dmaven.test.skip=true


FROM openjdk:17-jdk-alpine
COPY --from=build /home/app/target/*.jar /api/app.jar
WORKDIR /api/
ENTRYPOINT ["java", "-jar", "app.jar"]