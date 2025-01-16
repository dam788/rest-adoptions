# API de Adopciones

![image](https://github.com/user-attachments/assets/24040f12-5b27-4bfd-a84a-b925db96620d)

>Hola! Sean bienvenidos, es esta ocación estamos orgullosos de traerles buenas nuevas! Con mucho esfuerzo estuvimos desarrollando una api para adopción de mascotas, que es útil para facilitar  visibilidad, la transacción de las partes y mantener un registro de los animalitos que fueron adoptados. Con el tiempo  vamos a ir iterando y agregando nuevas features, por lo pronto esta en su versión básica la cual ya cuenta con login y registro de mascotas. Espero que les guste y esperamos su aporte!.


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

---

## Documentación de la API

- Una vez que levanto su proyecto puede acceder al navegador web con la siguiente dirección: http://localhost:8080/swagger-ui.html

---

## Curl de pruebas

Podes copiar y pegar estos curls en posman para agregar los los endpoints y realizar pruebas. Es tan facil como apretar el botón import y pegar el curl.

***Nota Importante:***

- Si querés podes agregar unos usuarios creador por patrón builder que dejé en el archivo **AdoptionsApplication**, lo único que tenes que hacer es descomentar las líneas de **user.saveAll(...)** 
 que están al final y correr la app una primera vez y luego volver a comentarlas ya que sino cuando vuelva a subir la app no va a funcionar porque los usuarios ya van a estar registrados en la base de datos y no se puede repetir data.

 - Una vez que agregue los **curls**, lo primero que tiene que hacer es iniciar sesión con un usuario por postman (...si, podes usar el que está de prueba, ese ya es usuario admin) y en la respuesta le va a llegar un **jwt**. Copie solo el jwt (sin comillas).

![image](https://github.com/user-attachments/assets/e107abb5-2ec5-40e7-bd97-4a72e0dc733c)

 - Ahora ve la pestaña **authorization** de postman,  debe seleccionar
   **Auth Type** como **Bearer Token** y debe pegar su nuevo token de sesión.

![image](https://github.com/user-attachments/assets/a56ec38f-70f0-45d5-8fc2-b1e202b80929)

**Ahora SI, ya estas listo para hacer Pruebas!**

### Users y auth

**Get users**

    curl --location 'http://localhost:8080/api/v1/users' \
    --data  ''
    
**Create user**

    curl --location 'http://localhost:8080/api/v1/auth/sing-up' \
	--header 'Content-Type: application/json' \
	--data-raw '{
	    "username": "ale22",
	    "email": "ale22@gmail.com",
	    "password": "1234",
	    "roleRequest": {
	        "roleListName": ["ADMIN", "DEV"]
	    }
	}
	'
	
**Login user**

    curl --location 'http://localhost:8080/api/v1/auth/login' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Basic ZGFtNzg4OjEyMzQ=' \
	--data '
	{
	    "username": "dam788",
	    "password": "1234"
	}
	'
	
### Locations

**Get one location**

    curl --location 'http://localhost:8080/api/v1/locations/1'

**Create location**

    curl --location 'http://localhost:8080/api/v1/locations' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODAzMzcsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODIxMzcsImlhdCI6MTczNjg4MDMzNywiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJjNjEwMWVjMC1jOTU1LTQ0MmUtOTIwMi00ZTkyNDlmMGIwNDEifQ.HDNaeP_U6Vp1mxhMJ-JUVaojCBuzMGJKogJqhJJLR88' \
	--data '{
	    "province": "nqn",
	    "city": "zapala",
	    "lon": 1.2,
	    "lat": 4.56
	}'

**Update location**

	curl --location --request PUT 'http://localhost:8080/api/v1/locations/1' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODAzMzcsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODIxMzcsImlhdCI6MTczNjg4MDMzNywiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJjNjEwMWVjMC1jOTU1LTQ0MmUtOTIwMi00ZTkyNDlmMGIwNDEifQ.HDNaeP_U6Vp1mxhMJ-JUVaojCBuzMGJKogJqhJJLR88' \
	--data '{
	    "province": "Neuquén",
	    "city": "zapala",
	    "lon": 2331.432,
	    "lat": 44.56
	}'

**Delete location**

	curl --location --request DELETE 'http://localhost:8080/api/v1/locations/delete/2' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODAzMzcsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODIxMzcsImlhdCI6MTczNjg4MDMzNywiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJjNjEwMWVjMC1jOTU1LTQ0MmUtOTIwMi00ZTkyNDlmMGIwNDEifQ.HDNaeP_U6Vp1mxhMJ-JUVaojCBuzMGJKogJqhJJLR88' \
	--data ''
	
### Species

**Get species**

    curl --location 'http://localhost:8080/api/v1/species'

**Create species**

    curl --location 'http://localhost:8080/api/v1/species' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODI4OTQsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODQ2OTQsImlhdCI6MTczNjg4Mjg5NCwiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJlM2RjMDgwYi01NjA1LTRlN2UtOGYyNy1hYjg3ZDUyNTc2YTAifQ.aWyvKPOdvOV9zDmucStfzNa2XZ7IUWRJnj2uMqDWyJ8' \
	--data '{
	    "name": "dog"
	}'
	
**Update species**

	curl --location --request PUT 'http://localhost:8080/api/v1/species/4' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODAzMzcsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODIxMzcsImlhdCI6MTczNjg4MDMzNywiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJjNjEwMWVjMC1jOTU1LTQ0MmUtOTIwMi00ZTkyNDlmMGIwNDEifQ.HDNaeP_U6Vp1mxhMJ-JUVaojCBuzMGJKogJqhJJLR88' \
	--data '{
	    "name": "perro"
	}'

**Delete species**

	curl --location --request DELETE 'http://localhost:8080/api/v1/species/delete/5' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODAzMzcsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODIxMzcsImlhdCI6MTczNjg4MDMzNywiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJjNjEwMWVjMC1jOTU1LTQ0MmUtOTIwMi00ZTkyNDlmMGIwNDEifQ.HDNaeP_U6Vp1mxhMJ-JUVaojCBuzMGJKogJqhJJLR88' \
	--data ''
	
### Images

**Get images**

    curl --location 'http://localhost:8080/api/v1/images'

**Create image**

    curl --location 'http://localhost:8080/api/v1/images' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY5ODM4OTIsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY5ODU2OTIsImlhdCI6MTczNjk4Mzg5MiwiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiIxYzkxMTg2YS1kMDU1LTQwMzQtOWUxYi1mNzc3MGUxMzFkM2YifQ.Aa-mLcG6l8ondvtF7G2WsauT9w8QbMf-w__A0yQ7s6k' \
	--data '
	    {
	        "imageUrl": "https://example.com/images/pancho.jpg",
	    }
	'
	
**Delete image**

	curl --location --request DELETE 'http://localhost:8080/api/v1/images/delete/10' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzcwMjkxMjQsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzcwMzA5MjQsImlhdCI6MTczNzAyOTEyNCwiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiI5ZTQ5Yzk1Zi00MmQ4LTQ2MDctODEwZS00MGM1NTFiNTU5M2IifQ.0Xysq4YQIwP5fWHlxcAzX-hUuf0uJwLdiGJztnTntGc' \
	--data ''

### Pets

**Get pets**

    curl --location 'http://localhost:8080/api/v1/pets' \
	--data ''

**Create pet**

    curl --location 'http://localhost:8080/api/v1/pets' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzcwMjgwMTQsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzcwMjk4MTQsImlhdCI6MTczNzAyODAxNCwiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiIwM2UwOGI3OS03NDIwLTRiMTQtYmYyYS1jNGVkNWUwZWRiMzkifQ.9-NUeBFw3fZbUR61pUYh9agXBiRTNRvHKV5HWEj6y6I' \
	--data '{
	    "name": "mocca",
	    "description": "es una perrita caniche",
	    "birthday": 2020,
	    "weight": 30.5,
	    "size": "small",
	    "gender": "female",
	    "available": true,
	    "active": true,
	    "avatarUrl": "https://www.google.com.ar",
	    "species": { "idSpecies": 1 },
	    "location": { "idLocation": 1 },
	    "images": [
	        {"idImage": 1}
	    ]
	}
	'
	
**Update pet**

	curl --location --request PUT 'http://localhost:8080/api/v1/pets/1' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzcwMjgwMTQsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzcwMjk4MTQsImlhdCI6MTczNzAyODAxNCwiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiIwM2UwOGI3OS03NDIwLTRiMTQtYmYyYS1jNGVkNWUwZWRiMzkifQ.9-NUeBFw3fZbUR61pUYh9agXBiRTNRvHKV5HWEj6y6I' \
	--data '{
	    "name": "mocca zuki",
	    "description": "es una perrita caniche pricot",
	    "birthday": 2020,
	    "weight": 30.5,
	    "size": "small",
	    "gender": "female",
	    "available": true,
	    "active": true,
	    "avatarUrl": "https://www.google.com.ar",
	    "species": { "idSpecies": 1 },
	    "location": { "idLocation": 1 },
	    "images": [
	        {"idImage": 2}
	    ]
}'

**Delete pet**

	curl --location --request PUT 
	'http://localhost:8080/api/v1/pets/delete/1' \
	--data ''
	
**No available pet**

	curl --location --request PUT 
	'http://localhost:8080/api/v1/pets/not-available/1' \
	--data ''

### Adoptions

**Get adoptions**

    curl --location 'http://localhost:8080/api/v1/adoptions' \
	--data ''

**Create adoption**

    curl --location 'http://localhost:8080/api/v1/adoptions' \
	--header 'Content-Type: application/json' \
	--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW03ODgiLCJuYmYiOjE3MzY4ODIyMDQsImlzcyI6IkFVVEhPSldULUJBQ0tFTkQiLCJleHAiOjE3MzY4ODQwMDQsImlhdCI6MTczNjg4MjIwNCwiYXV0aG9yaXRpZXMiOiJDUkVBVEUsREVMRVRFLFJFQUQsUk9MRV9BRE1JTixVUERBVEUiLCJqdGkiOiJiMGUyYjNmNi1iYzM4LTRiNjQtYTgzMi1mZTY3MDg4M2YwYTEifQ._NE01NzRWcWXM9bmTtVKmtVT3Asam3k5yDVZXMgm9FI' \
	--data '    {
	        "user": {
	            "idUser": 1
	        },
	        "pet": {
	            "idPet": 1
	        }
	    }
	'

**Update adoption**

	curl --location --request PUT 'http://localhost:8080/api/v1/adoptions/1' \
	--header 'Content-Type: application/json' \
	--data '    {
	        "user": {
	            "idUser": 2
	        },
	        "pet": {
	            "idPet": 1
	        }
	    }
	'
	
**Remove adoption**

	curl --location --request DELETE 
	'http://localhost:8080/api/v1/adoptions/delete/1' \
	--data ''




---

_With effort By [Hugo Morales](https://github.com/dam788/) y [Alejandro Alonso](https://github.com/aleAlonso90)._
