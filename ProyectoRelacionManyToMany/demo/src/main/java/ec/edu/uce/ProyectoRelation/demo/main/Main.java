package ec.edu.uce.ProyectoRelation.demo.main;

import ec.edu.uce.ProyectoRelation.demo.controller.Controller;
import ec.edu.uce.ProyectoRelation.demo.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "ec.edu.uce.ProyectoRelation.demo")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Controller controller = context.getBean(Controller.class);

     /*   controller.createPerson("Luis", "Sanchez");
        controller.createPerson("Ana", "González");

        controller.createTrip(1, "New York", "2024-06-25");
        controller.createTrip(1, "Los Angeles", "2024-07-10");
    */

       // controller.getAllPersons();
       // controller.getAllTrips();
        controller.updatePersonById(1, "Luis", "Martínez");
       // controller.updateTrip(1, 1, "Chicago", "2024-08-15");
       // controller.deleteTrip(2);
       // controller.deletePersonById(2);
    }

}


