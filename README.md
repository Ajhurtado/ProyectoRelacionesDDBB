# ProyectoRelacionesDDBB
*Nota: Para que el proyecto funcione correctamente, recuerde tener MySql con una data base "travel" y los ajustes necesarios.

-application.properties
spring.application.name=ProyectoRelation
spring.datasource.url = jdbc:mysql://localhost:3306/travel
spring.datasource.username = "user_base_datos"
spring.datasource.password = "password_base_datos"
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.generate-ddl = true
spring.jpa.hibernate.ddl-auto = update
server.port=8080

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

logging.level.org.hibernate.SQL=DEBUG:
Esta configuración establece el nivel de registro (logging) para las consultas SQL ejecutadas por Hibernate en DEBUG. Esto significa que Hibernate registrará en el archivo de logs todas las consultas SQL que se ejecuten, lo cual es útil para depurar y entender cómo Hibernate está interactuando con la base de datos.
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE:
Esta configuración establece el nivel de registro (logging) para los enlazadores (binders) de parámetros SQL básicos en TRACE. Los binders de parámetros son responsables de mapear los parámetros de Java a los tipos SQL correspondientes.
Estructura del Proyecto
Package repositories:
Contiene interfaces que extienden JpaRepository para cada una de tus entidades (Person, Passport, Trip). Estas interfaces permiten la interacción con la base de datos mediante métodos CRUD estándar.

Package model:
Contiene las entidades principales de tu aplicación:

Person: Representa una persona con atributos como nombre, apellido y relaciones como Passport y una lista de Trip.
Passport: Representa el pasaporte de una persona con un número de pasaporte y una relación OneToOne con Person.
Trip: Representa un viaje con destino, fecha y una relación ManyToOne con Person.
Package services:
Aquí se encuentran las clases que manejan la lógica de negocio y la interacción con los repositorios (ServicePerson, ServicePassport, ServiceTrip). Estas clases están anotadas con @Service y utilizan @Autowired para inyectar los repositorios necesarios. Implementan operaciones CRUD y métodos específicos de negocio.

Package controller:
El Controller maneja las solicitudes HTTP relacionadas con las operaciones CRUD para Person, Passport y Trip. Está anotado con @Controller y utiliza @Autowired para inyectar los servicios correspondientes (ServicePerson, ServicePassport, ServiceTrip). Actúa como un intermediario entre las solicitudes del usuario y la lógica de negocio.

Package main:
La clase Main es la clase principal de tu aplicación Spring Boot. Inicia el contexto de la aplicación y ejecuta tu controlador para manejar las operaciones CRUD. Está anotada con @SpringBootApplication, que combina varias anotaciones de Spring para configurar la aplicación.

Resumen de las Operaciones
Person: Creación, actualización, eliminación y consulta de personas, gestionando relaciones con Passport y Trip.
Passport: Gestión del pasaporte de una persona, asociación y desasociación.
Trip: Gestión de viajes asociados a una persona, creación, actualización y eliminación

-ApplicationContext context = SpringApplication.run(Main.class, args);: SpringApplication.run(Main.class, args) arranca la aplicación Spring Boot, crea y configura el contexto de la aplicación Spring, y devuelve un ApplicationContext.
-ApplicationContext context: Es una interfaz que proporciona métodos para acceder a los componentes de Spring y administrar el ciclo de vida de los beans de Spring.
-Controller controller = context.getBean(Controller.class);: Se obtiene una instancia del bean Controller del contexto de Spring. context.getBean(Controller.class) busca en el contexto de Spring un bean de tipo Controller y lo devuelve.
