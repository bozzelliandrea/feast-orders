# Maven build
FROM maven:3.8.2-jdk-11-slim AS build

COPY ../arch /home/app/arch
COPY ../atomic /home/app/atomic
COPY ../business /home/app/business
COPY ../rest /home/app/rest

COPY ../pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Server execution
FROM openjdk:11

COPY --from=build /home/app/target/*.jar /usr/local/lib/microservice.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/usr/local/lib/microservice.jar"]