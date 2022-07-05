
FROM maven
ADD . ./app
WORKDIR /app

RUN ls -l

RUN mvn install -DskipTests

FROM openjdk:17-alpine

EXPOSE 8080

VOLUME /tmp
COPY --from=0 "/app/target/*.jar" app.jar

ENV DATABASE_NAME=$DATABASE_NAME
ENV DB_LINK=$DB_LINK
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD
ENV JWT_PASSWORD=$JWT_PASSWORD
ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE
ENV DB_DATABASE=$DATABASE_NAME

CMD [ "sh", "-c", "java -XX:+UseContainerSupport -Dserver.port=$PORT $JAVA_OPTS -jar /app.jar" ]

# EXPOSE 8080
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
# ARG JAR_FILE=target/comex-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]