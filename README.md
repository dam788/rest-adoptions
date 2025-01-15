# API de Adopciones

![image](https://github.com/user-attachments/assets/24040f12-5b27-4bfd-a84a-b925db96620d)




## Instalación


 - Dentro de la carpeta resources encontrará el archivo **application.properties** con la siguiente configuración:
	
		# Database configuration
	    spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}}?	
	    autoReconnect=true
	    spring.datasource.username=${DB_USER}  
		spring.datasource.password=${DB_PASSWORD}  
		spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

		# create-drop  
		spring.jpa.hibernate.ddl-auto=update  
		spring.jpa.show-sql=true  
		spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
        
        # JWT  
		security.jwt.key.private=${SECURITY_KEY}  
		security.jwt.user.generator=${SECURITY_USER}  
  
		# Swagger  
		springdoc.api-docs.path=/api-docs  
		springdoc.swagger-ui.path=/swagger-ui.html
	    
	    
 - Lo siguiente que tiene que hacer es ejecutar este script de **mySQL workbench** para crear una base de datos, el usuario que utilizará para la misma y su contraseña:

	    CREATE DATABASE adoptions;  
		USE adoptions;  
  
		CREATE USER 'test-user'@'localhost' IDENTIFIED BY '123456';  
		GRANT ALL PRIVILEGES ON adoptions.* TO 'test-user'@'localhost';  
  
		FLUSH PRIVILEGES;

 - Luego vuelva a su archivo **application.properties** y reemplace las variables por lo siguiente:

	    # Database configuration
	    spring.datasource.url=jdbc:mysql://localhost:3306/adoptions?autoReconnect=true
	    spring.datasource.username=test-user
	    spring.datasource.password=123456
	    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    
	    spring.jpa.hibernate.ddl-auto=update
	    spring.jpa.show-sql=true
	    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    
	    # JWT
	    security.jwt.key.private=655e786674d9d3e77bc05ed1de37b4b6bc89f788829f9f3c679e7687b410c89b
	    security.jwt.user.generator=AUTH
    
	    # Swagger
	    springdoc.api-docs.path=/api-docs
	    springdoc.swagger-ui.path=/swagger-ui.html

 - En la **security.jwt.key.private** puede poner lo que quiera siempre y cuando este codificado, si no sabe como puede utilizar esta página para generara una agregando la palabra que usted desee -> [LINK](https://emn178.github.io/online-tools/sha256.html). En **security.jwt.user.generator** también debe agregar la palabra secreta que usted quiera.
 - Ahora ya puede levantar su aplicación de springboot, si quiere utilizando los shortcuts de **maven** que vienen por defecto en su **IDE IntelliJ**.

## Documentación de la API

- Una vez que levanto su proyecto puede acceder al navegador web con la siguiente dirección: http://localhost:8080/swagger-ui.html
