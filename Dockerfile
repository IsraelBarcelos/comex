
FROM 3-openjdk-18-slim

ADD . ./app
WORKDIR /app

RUN ls -l

RUN mvn clean install

FROM openjdk:17-alpine

EXPOSE 8080

VOLUME /tmp
COPY --from=0 "/app/target/*.jar" app.jar

CMD [ "sh", "-c", "java -XX:+UseContainerSupport -Dserver.port=$PORT $JAVA_OPTS -jar /app.jar" ]

# EXPOSE 8080
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
# ARG JAR_FILE=target/comex-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]