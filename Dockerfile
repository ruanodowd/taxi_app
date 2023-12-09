
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src /app/src
RUN mvn package -DskipTests

FROM openjdk:21-jre-slim AS final
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]