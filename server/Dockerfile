FROM maven:3.8.1-openjdk-20 AS MAVEN_BUILD

MAINTAINER Ali Javidan

COPY pom.xml /build/

COPY src /build/src/

WORKDIR /build/

RUN mvn package

FROM openjdk:20

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/*.war /app/app.war

ENTRYPOINT ["java", "-jar", "app.war"]