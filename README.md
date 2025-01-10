# Foro Hub

Foro Hub es una API REST desarrollada en Java utilizando Spring Boot, diseñada para gestionar un foro en línea. Proporciona funcionalidades CRUD para tópicos, autenticación con JWT y una estructura de seguridad robusta. Además, utiliza Flyway para gestionar migraciones de la base de datos.

## Tecnologías

- **Java 17**
- **Spring Boot 3.4.1**
- **JPA con Hibernate**
- **Flyway**
- **MySQL**
- **JWT (Json Web Tokens)**
- **Maven**

## Requisitos previos

- **Java 17**
- **Maven**
- **MySQL**

## Configuración de la base de datos

La estructura de la base de datos se crea automáticamente mediante Flyway. Las migraciones están definidas en la carpeta `src/main/resources/db/migration` y se ejecutan al iniciar la aplicación.

### Configuración en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}:3306/ForoHub
spring.datasource.username=${MYSQL_USER}
spring.datasource.password=${MYSQL_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

api.security.token.secret=${JWT_SECRET}
```

Asegúrate de configurar las variables de entorno `DB_HOST`, `MYSQL_USER` y `MYSQL_PASSWORD` antes de iniciar la aplicación.

## Endpoints principales

### Autenticación

| Método | Endpoint | Descripción                                  |
| ------ | -------- | -------------------------------------------- |
| POST   | `/login` | Autentica al usuario y retorna un token JWT. |

### Tópicos

| Método | Endpoint        | Descripción                       |
| ------ | --------------- | --------------------------------- |
| POST   | `/topicos`      | Crea un nuevo tópico.             |
| GET    | `/topicos`      | Lista los tópicos con paginación. |
| GET    | `/topicos/{id}` | Obtiene un tópico por su ID.      |
| PUT    | `/topicos/{id}` | Actualiza un tópico existente.    |
| DELETE | `/topicos/{id}` | Elimina un tópico por su ID.      |

## Seguridad

La API utiliza JWT para autenticar y autorizar solicitudes. Los tokens tienen una duración de 2 horas y se generan al iniciar sesión mediante el endpoint `/login`.

### Configuración del token

El secret para firmar los tokens se define en la propiedad `api.security.token.secret`. Por defecto, se utiliza `123456`, pero se recomienda cambiarlo en producción.

## Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   [git clone https://github.com/tu-usuario/foro-hub.git](https://github.com/VicentSeg/Foro.Hub.Challenge.git)
   ```
2. Configura las variables de entorno necesarias:
   - `DB_HOST`: Host de la base de datos.
   - `MYSQL_USER`: Usuario de la base de datos.
   - `MYSQL_PASSWORD`: Contraseña de la base de datos.
3. Ejecuta el comando:
   ```bash
   mvn spring-boot:run
   ```
4. Agregar un Usuario en la base de datos. 

Ingresa un usuario en la base de datos 

``` 
INSERT INTO usuarios (nombre, login, contrasena)
 VALUES (nombre, login, contrasena);
```
5. La aplicación estará disponible en `http://localhost:8080`.

## Archivos de Insomnia

En la raíz del proyecto se incluyen archivos de Insomnia para facilitar la prueba de los endpoints. Importa estos archivos en Insomnia para acceder a las colecciones de solicitudes predefinidas.


 
