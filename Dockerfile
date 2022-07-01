FROM openjdk:18.0.1.1-slim-buster
EXPOSE 8080
ADD target/comex.jar teste.jar
ENTRYPOINT ["java", "-jar","/teste.jar"]
#testando
#testando
#
#
#
#
#
#
#
#RUN adduser --system --group spring
# ADD target/comex.jar app.jar
#USER spring:spring