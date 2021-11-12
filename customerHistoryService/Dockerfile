FROM openjdk:11-jre-slim
VOLUME /tmp
ENV EUREKA_SERVER_USERNAME username
ENV EUREKA_SERVER_PASSWORD password
ENV EUREKA_SERVER_HOST 0.0.0.0
ENV EUREKA_SERVER_PORT 8761
ENV EUREKA_SERVER_HOST localhost
ENV KAFKA_BOOTSTRAP_SERVERS localhost:9092
ENV OAUTH_URL_SERVER localhost:8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dsecurity.oauth2.resource.userInfoUri=http://${OAUTH_URL_SERVER}/user" , "-Dspring.kafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS}" ,"-Deureka.server.username=${EUREKA_SERVER_USERNAME}", "-Deureka.server.password=${EUREKA_SERVER_PASSWORD}", "-Deureka.server.host=${EUREKA_SERVER_HOST}", "-Deureka.server.port=${EUREKA_SERVER_PORT}", "-jar", "app.jar"]