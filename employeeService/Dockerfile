FROM openjdk:11-jre-slim
VOLUME /tmp
ENV EUREKA_SERVER_PORT 8761
ENV EUREKA_SERVER_HOST localhost
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Deureka.server.host=${EUREKA_SERVER_HOST}", "-Deureka.server.port=${EUREKA_SERVER_PORT}", "-jar", "app.jar"]