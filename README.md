# Proyecto Relaciones Base de Datos

*Nota: Para que el proyecto funcione correctamente, asegúrate de tener MySQL instalado con una base de datos llamada "travel" y configurar las credenciales necesarias.*

## Configuración

### `application.properties`

```properties
spring.application.name=ProyectoRelation
spring.datasource.url=jdbc:mysql://localhost:3306/travel
spring.datasource.username=user_base_datos
spring.datasource.password=password_base_datos
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
server.port=8080

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

logging.level.org.hibernate.SQL: Nivel de registro para las consultas SQL ejecutadas por Hibernate (DEBUG).
logging.level.org.hibernate.type.descriptor.sql.BasicBinder: Nivel de registro para los enlazadores de parámetros SQL (TRACE).

```
Estructura del Proyecto
Package repositories
Contiene interfaces que extienden JpaRepository para cada una de las entidades (Person, Passport, Trip). Estas interfaces permiten la interacción con la base de datos mediante métodos CRUD estándar.

Package model
Contiene las entidades principales del proyecto:

Person: Representa una persona con atributos como nombre, apellido y relaciones como Passport y una lista de Trip.
Passport: Representa el pasaporte de una persona con un número de pasaporte y una relación OneToOne con Person.
Trip: Representa un viaje con destino, fecha y una relación ManyToOne con Person.

Package services
Aquí se encuentran las clases de servicio que implementan la lógica de negocio y gestionan la interacción con los repositorios (ServicePerson, ServicePassport, ServiceTrip). Estas clases están anotadas con @Service y utilizan @Autowired para inyectar los repositorios necesarios. Implementan operaciones CRUD y métodos específicos de negocio.

Package controller
El Controller maneja las solicitudes HTTP relacionadas con las operaciones CRUD para Person, Passport y Trip. Está anotado con @Controller y utiliza @Autowired para inyectar los servicios correspondientes (ServicePerson, ServicePassport, ServiceTrip). Actúa como un intermediario entre las solicitudes del usuario y la lógica de negocio.

Package main
La clase Main es la clase principal de tu aplicación Spring Boot. Inicia el contexto de la aplicación y ejecuta tu controlador para manejar las operaciones CRUD. Está anotada con @SpringBootApplication, que combina varias anotaciones de Spring para configurar la aplicación.

Resumen de las Operaciones
Person: Creación, actualización, eliminación y consulta de personas, gestionando relaciones con Passport y Trip.
Passport: Gestión del pasaporte de una persona, asociación y desasociación.
Trip: Gestión de viajes asociados a una persona, creación, actualización y eliminación.
