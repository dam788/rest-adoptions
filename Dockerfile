FROM openjdk:21
ARG JARFILE=target/*JAR
COPY ./target/adoptions-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]

