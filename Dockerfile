
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src /app/src
RUN mvn package -DskipTests

FROM eclipse-temurin:21.0.1_12-jre-jammy AS final
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]