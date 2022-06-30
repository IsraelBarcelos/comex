FROM openjdk:18.0.1-slim
RUN adduser --system --group spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port.=${PORT}", "-jar","./app.jar"]