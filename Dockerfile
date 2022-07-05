
FROM maven
ADD . ./app
WORKDIR /app

RUN ls -l

RUN mvn install -DskipTests

FROM openjdk:17-alpine

EXPOSE 8080

VOLUME /tmp
COPY --from=0 "/app/target/*.jar" app.jar

ENV DATABASE_NAME="comexdb"
ENV DB_LINK="jdbc:mysql://mysqlcomex:3306/comexdb"
ENV DB_ROOT_PASSWORD: "root"
ENV DB_USERNAME="root"
ENV DB_PASSWORD="root"
ENV JWT_PASSWORD="root"
ENV SPRING_PROFILES_ACTIVE="production"
ENV DB_DATABASE="comexdb"
ENV PORT="8080"
ENV JAVA_OPTS="-Xmx512m -Xms512m"

CMD [ "sh", "-c", "java -XX:+UseContainerSupport -jar /app.jar" ]

# EXPOSE 8080
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
# ARG JAR_FILE=target/comex-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]