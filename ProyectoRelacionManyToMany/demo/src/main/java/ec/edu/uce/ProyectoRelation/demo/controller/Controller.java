package ec.edu.uce.ProyectoRelation.demo.controller;
import ec.edu.uce.ProyectoRelation.demo.model.Person;
import ec.edu.uce.ProyectoRelation.demo.model.Trip;
import ec.edu.uce.ProyectoRelation.demo.services.ServicePerson;
import ec.edu.uce.ProyectoRelation.demo.services.ServiceTrip;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private final ServicePerson servicePerson;

    @Autowired
    private final ServiceTrip serviceTrip;

    @Autowired
    public Controller(ServicePerson servicePerson, ServiceTrip serviceTrip) {
        this.servicePerson = servicePerson;
        this.serviceTrip = serviceTrip;
    }

    public void createPerson(String name, String lastName) {
        Person person = new Person();
        person.setName(name);
        person.setLastName(lastName);
        servicePerson.save(person);
        System.out.println("Persona agregada");
    }

    public void deletePersonById(int personId) {
        servicePerson.deletePersonById(personId);
        System.out.println("Persona eliminada con ID: " + personId);
    }

    public void updatePersonById(int personId, String name, String lastName) {
        Person updatedPerson = new Person();
        updatedPerson.setName(name);
        updatedPerson.setLastName(lastName);
        Person personaActualizada = servicePerson.updatePerson(personId, updatedPerson);
        System.out.println("Persona actualizada: " + personaActualizada);
    }

    public void getAllPersons() {
        List<Person> persons = servicePerson.getAllPersons();
        System.out.println("Personas en la base de datos:");
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    @Transactional
    public void createTrip(int personId, String destination, String date) {
        Optional<Person> personOptional = servicePerson.getPersonById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            Trip newTrip = new Trip(destination, date);
            newTrip.getPersons().add(person);
            serviceTrip.save(newTrip);
            System.out.println("Viaje creado para la persona con ID: " + personId);
        } else {
            System.out.println("Persona con ID " + personId + " no encontrada.");
        }
    }

    public void getAllTrips() {
        List<Trip> trips = serviceTrip.getAllTrips();
        System.out.println("Trips: " + trips);
    }

    public void updateTrip(int personId, int tripId, String newDestination, String date) {
        Optional<Trip> tripOptional = serviceTrip.getTripById(tripId);
        if (tripOptional.isPresent()) {
            Trip existingTrip = tripOptional.get();
            existingTrip.setDestination(newDestination);
            existingTrip.setDate(date);
            serviceTrip.save(existingTrip);
            System.out.println("Viaje actualizado con ID: " + tripId);
        } else {
            System.out.println("Viaje con ID " + tripId + " no encontrado.");
        }
    }

    public void deleteTrip(int tripId) {
        serviceTrip.deleteTripById(tripId);
        System.out.println("Viaje eliminado con ID: " + tripId);
    }
}

