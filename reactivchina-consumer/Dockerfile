FROM openjdk:11.0.2-jdk-slim-stretch
MAINTAINER Andrey Romanov <steampart@gmail.com>

WORKDIR /usr/src/application
EXPOSE 8081

ARG JVM_ARGS
ARG SPRING_PROFILES_ACTIVE
ARG JAR_FILE=build/libs/reactivchina-consumer-1.0.jar

ADD ${JAR_FILE} app.jar

CMD java $JVM_ARGS -jar app.jar