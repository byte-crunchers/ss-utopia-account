FROM openjdk:11-jre-slim as builder
RUN mkdir /app
COPY  /target/*.war /app/java-application.war
WORKDIR /app
EXPOSE 8088
CMD "java" "-jar" "java-application.war"