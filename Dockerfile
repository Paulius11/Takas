FROM openjdk:11 AS builder

ADD target/takas-0.0.1-SNAPSHOT.jar takas-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","takas-0.0.1-SNAPSHOT.jar"]

