FROM maven:3-jdk-14 AS builder

WORKDIR /usr/src/app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn --errors \
    package assembly:single

FROM openjdk:14-alpine

WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/telegramwebcrawler.jar .

ENTRYPOINT ["java","-jar","telegramwebcrawler.jar"]
