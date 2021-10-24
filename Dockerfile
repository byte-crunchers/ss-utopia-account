
FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f
RUN mkdir /app
COPY  /target/*.war /app/java-application.war
WORKDIR /app
EXPOSE 8088
CMD "java" "-jar" "java-application.war"