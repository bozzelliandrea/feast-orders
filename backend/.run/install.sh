#bin/bash

echo todo

mvn clean package spring-boot:repackage

cd rest/target/

java -jar rest-1.0.0-SNAPSHOT.jar