# Maven build
FROM maven:3.8.2-jdk-11-slim AS build

COPY arch /arch
COPY atomic /atomic
COPY business /business
COPY rest /rest

COPY pom.xml /.

RUN mvn -f /pom.xml clean package spring-boot:repackage -DskipTests

# Server execution
FROM openjdk:11

COPY --from=build rest/target/*.jar /usr/local/lib/microservice.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/usr/local/lib/microservice.jar"]