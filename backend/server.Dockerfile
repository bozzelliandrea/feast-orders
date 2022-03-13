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

ENV JAVA_OPT="--add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.management/sun.management=ALL-UNNAMED --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED"

EXPOSE 8081
ENTRYPOINT exec java $JAVA_OPT -jar /usr/local/lib/microservice.jar