# Usa la imagen oficial de OpenJDK (Eclipse Temurin)
FROM eclipse-temurin:21.0.3_9-jdk

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos esenciales para la configuración de Maven Wrapper
COPY .mvn /app/.mvn
COPY mvnw /app/mvnw
COPY pom.xml /app/

# Asegurar permisos de ejecución del wrapper de Maven
RUN chmod +x /app/mvnw

# Descarga dependencias sin ejecutarlas para aprovechar la caché de Docker
RUN /app/mvnw dependency:go-offline

# Copia el código fuente de la aplicación
COPY src /app/src

# Construye la aplicación sin ejecutar los tests
RUN /app/mvnw clean package -DskipTests

# Expone el puerto de la aplicación
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/target/adoptions-0.0.1-SNAPSHOT.jar"]



