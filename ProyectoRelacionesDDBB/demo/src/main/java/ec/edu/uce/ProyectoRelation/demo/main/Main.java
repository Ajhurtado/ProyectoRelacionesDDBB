package ec.edu.uce.ProyectoRelation.demo.main;

import ec.edu.uce.ProyectoRelation.demo.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "ec.edu.uce.ProyectoRelation.demo")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Controller controller = context.getBean(Controller.class);
        //PERSON
       controller.createPerson("Luis", "Sanchez");
       //controller.getAllPersons();
       //Actualizar una persona por su ID
       //controller.updatePersonById(8, "Dario", "Lopez");
       //controller.deletePersonById(8);

        /*
        //PASSPORT
        controller.addPassportToPerson(8, "AB123456");
        controller.getAllPassports();
        controller.updatePassportByPersonId(8, "CD789012");
        controller.deletePassportByPersonId(8);

        //TRIPS
        // Crear un nuevo viaje para una persona
        controller.createTrip(8, "New York", "2024-06-25");
        controller.getAllTrips();
        controller.updateTrip(8, 1, "Brasil", "2024-07-10");
        controller.deleteTrip(8, 1);
        */
    }

}


