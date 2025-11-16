FROM maven:3.9.11-amazoncorretto-25 AS build

WORKDIR /app
COPY .. .
RUN mvn clean package

FROM amazoncorretto:25

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY ../src/main/resources/appsettings.docker.properties /app/appsettings.properties

ENTRYPOINT ["java", "-jar", "app.jar"]
