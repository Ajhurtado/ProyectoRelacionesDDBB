package ec.edu.uce.ProyectoRelation.demo.services;

import ec.edu.uce.ProyectoRelation.demo.repositories.IPerson;
import ec.edu.uce.ProyectoRelation.demo.model.Person;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePerson {

    @Autowired
    private IPerson iPerson;

    public Person save(Person person){
        iPerson.save(person);
        return person;
    }

    public List<Person> getAllPersons() {
        return iPerson.findAll();
    }

    public Optional<Person> getPersonById(int id) {
        return iPerson.findById(id)
                .map(person -> {
                    Hibernate.initialize(person.getTrips());
                    return person;
                });
    }

    public Person updatePerson(int id, Person updatedPerson) {
        Optional<Person> existingPersonOptional = iPerson.findById(id);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            existingPerson.setName(updatedPerson.getName());
            existingPerson.setLastName(updatedPerson.getLastName());
            return iPerson.save(existingPerson);
        } else {
            throw new IllegalArgumentException("Persona con el ID " + id + " no encontrada");
        }
    }

    public void deletePersonById(int id) {
        iPerson.deleteById(id);
    }
}

