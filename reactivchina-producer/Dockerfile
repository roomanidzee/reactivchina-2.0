FROM openjdk:11.0.2-jdk-slim-stretch
MAINTAINER Andrey Romanov <steampart@gmail.com>

WORKDIR /usr/src/application
EXPOSE 8080

ARG JVM_ARGS
ARG SPRING_PROFILES_ACTIVE
ARG JAR_FILE=build/libs/reactivchina-producer-1.0.jar

ADD ${JAR_FILE} app.jar
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh  wait-for-it.sh
RUN chmod +x wait-for-it.sh

CMD ./wait-for-it.sh -t 180 cassandra-service:9042 -- java $JVM_ARGS -jar app.jar