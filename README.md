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

Primer Proyecto
Este proyecto implica el manejo de entidades como Person (Persona), Passport (Pasaporte) y Trip (Viaje) en una aplicación Java utilizando Spring Boot y JPA/Hibernate para la persistencia de datos. Además del uso de MySql.
Un resumen general de las entidades y las operaciones que estás realizando:
Tenemos 4 diferentes package donde describire de forma general el uso de cada uno.

Package repositories
Donde tendremos las interfaces que utilizaremos para nuestro proyecto.
IPerson - IPassport - ITrip
-La anotación @Repository usadas en cada interfaz es utilizada en Spring Framework para indicar que la clase anotada es un componente de acceso a datos (DAO - Data Access Object). 
-JpaRepository es una interfaz proporcionada por Spring Data JPA que extiende la interfaz CrudRepository. Proporciona métodos CRUD (Crear, Leer, Actualizar, Eliminar) estándar y métodos para operaciones comunes de consulta.
-Person es la clase de entidad que representa una tabla en la base de datos.

De esta forma estara estructurado todas las interfaces.

Package model
Donde tendremos nuestras entidades principales con las cuales trabajaremos para las tablas en nuestra base datos.

Entidades Principales
-Person (Persona)
Representa a una persona con atributos como nombre (name), apellido (lastName) y relaciones como Passport y List<Trip>.

Anotaciones a tener en cuenta
-@Entity
La anotación @Entity indica que la clase Person es una entidad JPA y está mapeada a una tabla en la base de datos.
-@Table(name = "person")
La anotación @Table se utiliza para especificar el nombre de la tabla en la base de datos a la cual está mapeada la entidad Person. En este caso, @Table(name = "person") indica que esta clase está mapeada a la tabla "person".
-@Id
La anotación @Id se utiliza para especificar la clave primaria de la entidad. En este caso, private int id; marca el campo id como la clave primaria de la tabla "person".
-@GeneratedValue
@GeneratedValue especifica la estrategia para generar valores automáticamente para la clave primaria. 
-strategy = GenerationType.SEQUENCE indica que se utilizará una secuencia para generar valores de id. 
-generator = "seq_person" se refiere al nombre de la secuencia en la base de datos.

-@SequenceGenerator
@SequenceGenerator se utiliza junto con @GeneratedValue para definir la secuencia que generará los valores para la clave primaria. 
-name = "seq_person" es el nombre de la secuencia en la base de datos, 
-sequenceName = "seq_person" especifica el nombre real de la secuencia en el sistema de base de datos.

-@Column
La anotación @Column se usa para mapear un campo de la entidad a una columna en la tabla de la base de datos. En este caso, @Column está presente en los campos name y lastName, lo que indica que estos campos se mapean a las columnas correspondientes en la tabla "person".

-@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
@OneToOne establece una relación uno a uno entre Person y Passport. mappedBy = "person" especifica el nombre del atributo en la clase Passport que mapea esta relación inversa (bidireccional). 
-cascade = CascadeType.ALL indica que las operaciones de guardado, actualización y eliminación realizadas en Person también se aplicarán a Passport. 
-orphanRemoval = true asegura que si se elimina Person, también se elimine su Passport.

-@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
@OneToMany establece una relación uno a muchos entre Person y Trip. 
-mappedBy = "person" especifica el nombre del atributo en la clase Trip que mapea esta relación inversa (bidireccional). 
-cascade = CascadeType.ALL indica que las operaciones de guardado, actualización y eliminación realizadas en Person también se aplicarán a Trip. 
-orphanRemoval = true asegura que si se elimina Person, también se eliminarán todos sus Trip. 
-fetch = FetchType.EAGER indica que la colección de Trip se cargará de manera ansiosa (todos los Trip se cargarán de inmediato cuando se cargue Person).

Métodos Getter y Setter
Los métodos get y set son getters y setters estándar para acceder y modificar los campos privados de la clase Person.
-removeTrip(Trip trip)
removeTrip elimina un viaje (Trip) específico de la lista de viajes (trips) de la persona. También establece el campo person de Trip a null, lo que asegura que el viaje se desasocie correctamente de la persona.
-removePassport()
removePassport elimina el pasaporte (Passport) asociado a la persona. Primero establece el campo person de Passport a null y luego establece el campo passport de Person a null, asegurando que la relación entre Person y Passport se rompa correctamente.

Esta seria una descripcion general de como esta compuesta nuestra clase Person, del mismo modo estara compuesta nuestras clases Passport y Trip

-Passport (Pasaporte)
Representa el pasaporte de una persona con atributos como número de pasaporte (number) y una relación OneToOne con Person.

-@OneToOne indica que la relación entre la entidad actual (en este caso, supongamos que es Passport) y la entidad Person es de uno a uno.
-@JoinColumn
La anotación @JoinColumn se utiliza para especificar la columna en la tabla de la base de datos que se utilizará para la relación. En tu caso:
-name = "person_id" especifica el nombre de la columna en la tabla de la base de datos de la entidad Passport que va a contener las claves primarias o externas de la entidad relacionada (Person en este caso).

-Trip (Viaje)
Representa un viaje con atributos como destino (destination), fecha (date) y una relación ManyToOne con Person.

-@ManyToOne indica que cada instancia de la entidad Trip puede estar asociada a una única instancia de la entidad Person, pero muchas instancias de Trip pueden estar asociadas a la misma instancia de Person.

-@JoinColumn(name = "person_id") especifica que en la tabla de la base de datos de la entidad Trip, habrá una columna llamada person_id que contendrá la clave primaria o externa (dependiendo de la configuración) de la entidad Person asociada a cada instancia de Trip.

Package services
Estas clases son responsables de interactuar con los repositorios (Repositories) para realizar operaciones CRUD (Create, Read, Update, Delete) en las entidades, aplicando reglas de negocio si es necesario. 

Anotaciones a tener en cuenta
-@Service: Esta anotación marca esta clase como un componente de servicio en Spring, lo cual indica que esta clase maneja la lógica de negocio relacionada con los pasaportes.
-@Autowired: Estas anotaciones se utilizan para inyectar las dependencias necesarias (IPassport y IPerson). 
IPassport es una interfaz que extiende JpaRepository para la entidad Passport, proporcionando métodos para operaciones CRUD en la tabla de pasaportes. 
IPerson es una interfaz similar para la entidad Person, necesaria para algunas operaciones relacionadas con la actualización del pasaporte.


Operaciones CRUD (Crear, Leer, Actualizar, Eliminar):
Crear Persona: Agregar una nueva persona con nombre y apellido.
Eliminar Persona por ID: Eliminar una persona específica mediante su ID.
Actualizar Persona por ID: Modificar los datos de una persona existente.

getAllPersons()
Este método devuelve una lista de todas las personas almacenadas en la base de datos. Utiliza el método findAll() proporcionado por JpaRepository, que recupera todos los registros de la tabla Person y los devuelve como una lista.

getPersonById(int id)
Este método busca una persona específica por su ID en la base de datos. Utiliza el método findById(id) proporcionado por JpaRepository, que devuelve un Optional que puede contener la persona encontrada o ser vacío si no se encuentra.
 -Hibernate.initialize(person.getTrips()): Se utiliza para inicializar la colección trips de la persona.

updatePerson(int id, Person updatedPerson)
 Este método actualiza la información de una persona existente en la base de datos. Primero busca la persona por su ID utilizando findById(id). Si la persona existe (existingPersonOptional.isPresent() es true), se obtiene la instancia de la persona (existingPerson) y se actualizan los campos name y lastName con los valores proporcionados en updatedPerson. Luego se guarda la persona actualizada utilizando el método save() de JpaRepository, que actualiza la entrada correspondiente en la base de datos y devuelve la persona actualizada.

deletePersonById(int id)
Este método elimina una persona de la base de datos por su ID. Utiliza el método deleteById(id) proporcionado por JpaRepository, que elimina la entrada correspondiente en la tabla Person según el ID proporcionado.
 
Del mismo modo tendremos métodos similares para la clase ServicePassport y ServiceTrip

Agregar Pasaporte a Persona: Asociar un pasaporte a una persona existente mediante su ID.
Eliminar Pasaporte por ID de Persona: Quitar el pasaporte de una persona específica y eliminar el pasaporte de la base de datos.
Actualizar Pasaporte por ID de Persona: Actualizar el número de pasaporte de una persona específica.

Crear Viaje para Persona: Agregar un nuevo viaje asociado a una persona existente.
Actualizar Viaje por ID para Persona: Modificar los detalles de un viaje específico de una persona.
Eliminar Viaje por ID para Persona: Eliminar un viaje específico de una persona.

Package controller
Class Controller
El cual maneja operaciones CRUD relacionadas con entidades Person, Passport y Trip a través de los servicios correspondientes (ServicePerson, ServicePassport, ServiceTrip).
-@Controller marca esta clase como un controlador de Spring MVC, lo cual indica que esta clase define métodos que manejan peticiones HTTP y generan respuestas HTTP, comúnmente utilizada en aplicaciones web.
-@Autowired se utiliza para la inyección de dependencias de Spring, que permite a Spring inyectar instancias de ServicePerson, ServicePassport y ServiceTrip en este controlador.
El constructor Controller realiza la inyección de dependencias de los servicios mencionados. Spring automáticamente proporciona instancias adecuadas de estos servicios cuando se crea un objeto Controller.
Controlador actúa como un intermediario entre las solicitudes del usuario (como crear, actualizar, eliminar o consultar) y los servicios que interactúan con la capa de persistencia (la base de datos).

Package main
Class Main
Clase principal de tu aplicación Spring Boot, donde llamaremos nuestro controller para poder dar inicio a nuestra aplicación.
-@SpringBootApplication es una anotación de conveniencia que combina varias anotaciones de Spring:
-@Configuration: Indica que la clase contiene configuraciones de Spring.
-@EnableAutoConfiguration: Permite la configuración automática basada en el classpath de la aplicación y en las dependencias de Spring.
-@ComponentScan: Escanea el paquete base y sus subpaquetes en busca de componentes anotados para registrarse en el contexto de Spring.
scanBasePackages = "ec.edu.uce.ProyectoRelation.demo": Especifica el paquete base que Spring debe escanear en busca de componentes, configuraciones y controladores. En este caso, se escanea el paquete ec.edu.uce.ProyectoRelation.demo y sus subpaquetes para encontrar componentes de Spring.

-ApplicationContext context = SpringApplication.run(Main.class, args);: SpringApplication.run(Main.class, args) arranca la aplicación Spring Boot, crea y configura el contexto de la aplicación Spring, y devuelve un ApplicationContext.
-ApplicationContext context: Es una interfaz que proporciona métodos para acceder a los componentes de Spring y administrar el ciclo de vida de los beans de Spring.
-Controller controller = context.getBean(Controller.class);: Se obtiene una instancia del bean Controller del contexto de Spring. context.getBean(Controller.class) busca en el contexto de Spring un bean de tipo Controller y lo devuelve.
