package ec.edu.uce.ProyectoRelation.demo.controller;
import ec.edu.uce.ProyectoRelation.demo.model.Passport;
import ec.edu.uce.ProyectoRelation.demo.model.Person;
import ec.edu.uce.ProyectoRelation.demo.model.Trip;
import ec.edu.uce.ProyectoRelation.demo.services.ServicePassport;
import ec.edu.uce.ProyectoRelation.demo.services.ServicePerson;
import ec.edu.uce.ProyectoRelation.demo.services.ServiceTrip;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private final ServicePerson servicePerson;
    @Autowired
    private final ServicePassport servicePassport;
    @Autowired
    private final ServiceTrip serviceTrip;

    @Autowired
    public Controller(ServicePerson servicePerson, ServicePassport servicePassport, ServiceTrip serviceTrip) {
        this.servicePerson = servicePerson;
        this.servicePassport = servicePassport;
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
    //PASSPORT
    public void addPassportToPerson(int personId, String passportNumber) {
        Optional<Person> existingPersonOptional = servicePerson.getPersonById(personId);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            Passport passport = new Passport();
            passport.setNumber(passportNumber);
            passport.setPerson(existingPerson);
            servicePassport.save(passport);
            System.out.println("Pasaporte agregado a la persona con ID " + personId);
        } else {
            throw new IllegalArgumentException("Persona con el ID " + personId + " no encontrada");
        }
    }

    public void getAllPassports() {
        List<Passport> passports = servicePassport.getAllPassports();
        System.out.println("Pasaportes encontrados:");
        for (Passport passport : passports) {
            System.out.println(passport);
        }
    }

    public void updatePassportByPersonId(int personId, String newPassport) {
        Passport updatedPassport = new Passport();
        updatedPassport.setNumber(newPassport);
        Passport passportActualizado = servicePassport.updatePassportByPersonId(personId, updatedPassport);
        System.out.println("Pasaporte actualizado: " + passportActualizado);
    }

    public void deletePassportByPersonId(int personId) {
        Optional<Person> personOptional = servicePerson.getPersonById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            if (person.getPassport() != null) {
                Passport passport = person.getPassport();
                person.removePassport();
                servicePerson.save(person);
                servicePassport.deletePassportById(passport.getId());
                System.out.println("Pasaporte eliminado con ID: " + passport.getId() + " para la persona con ID " + personId);
            } else {
                System.out.println("No se encontró pasaporte para la persona con ID " + personId);
            }
        } else {
            System.out.println("Persona con ID " + personId + " no encontrada.");
        }
    }
    //TRIP
    public void createTrip(int personId, String destination, String date) {
        Optional<Person> personOptional = servicePerson.getPersonById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            Trip newTrip = new Trip(destination, date);
            newTrip.setPerson(person);
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
        Optional<Person> personOptional = servicePerson.getPersonById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            List<Trip> trips = person.getTrips();
            Optional<Trip> tripOptional = trips.stream()
                    .filter(trip -> trip.getId() == tripId)
                    .findFirst();
            if (tripOptional.isPresent()) {
                Trip existingTrip = tripOptional.get();
                existingTrip.setDestination(newDestination);
                existingTrip.setDate(date);
                serviceTrip.save(existingTrip);
                System.out.println("Viaje actualizado con ID: " + tripId);
            } else {
                System.out.println("Viaje con ID " + tripId + " no encontrado para la persona con ID " + personId);
            }
        } else {
            System.out.println("Persona con ID " + personId + " no encontrada.");
        }
    }

    public void deleteTrip(int personId, int tripId) {
        Optional<Person> personOptional = servicePerson.getPersonById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            List<Trip> trips = person.getTrips();
            Optional<Trip> tripOptional = trips.stream()
                    .filter(trip -> trip.getId() == tripId)
                    .findFirst();
            if (tripOptional.isPresent()) {
                Trip trip = tripOptional.get();
                person.removeTrip(trip);
                servicePerson.save(person);
                serviceTrip.deleteTripById(tripId);
                System.out.println("Viaje eliminado con ID: " + tripId + " para la persona con ID " + personId);
            } else {
                System.out.println("Viaje con ID " + tripId + " no encontrado para la persona con ID " + personId);
            }
        } else {
            System.out.println("Persona con ID " + personId + " no encontrada.");
        }
    }
}

