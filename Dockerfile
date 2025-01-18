FROM openjdk:21-slim
WORKDIR /app
COPY target/adoptions-0.0.1-SNAPSHOT.jar /app/adoptions.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/adoptions.jar"]

