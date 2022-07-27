
FROM maven
ADD . ./app
WORKDIR /app

RUN ls -l

ENV DB_ROOT_PASSWORD: "root123456"
ENV DB_USER="userfromdb"
ENV DB_PASSWORD="root123456"
ENV DB_PORT=3306
ENV DB_HOST="mysqlcomex"
ENV DB_NAME="comexdb"
ENV JWT_PASSWORD="123456"
ENV SPRING_PROFILES_ACTIVE="production"

RUN mvn install -DskipTests

FROM openjdk:17-alpine

EXPOSE 8080

VOLUME /tmp
COPY --from=0 "/app/target/*.jar" app.jar

ENV DB_ROOT_PASSWORD: "root123456"
ENV DB_USER="userfromdb"
ENV DB_PASSWORD="root123456"
ENV DB_PORT=3306
ENV DB_HOST="mysqlcomex"
ENV DB_NAME="comexdb"
ENV JWT_PASSWORD="123456"
ENV SPRING_PROFILES_ACTIVE="production"

CMD [ "sh", "-c", "java -XX:+UseContainerSupport -Xmx512m -Xms512m -Dserver.port=$PORT -jar /app.jar " ]

# EXPOSE 8080
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
# ARG JAR_FILE=target/comex-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]