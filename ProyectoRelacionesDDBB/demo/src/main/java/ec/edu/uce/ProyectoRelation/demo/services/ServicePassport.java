package ec.edu.uce.ProyectoRelation.demo.services;

import ec.edu.uce.ProyectoRelation.demo.repositories.IPassport;
import ec.edu.uce.ProyectoRelation.demo.repositories.IPerson;
import ec.edu.uce.ProyectoRelation.demo.model.Passport;
import ec.edu.uce.ProyectoRelation.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePassport {

    @Autowired
    private IPassport iPassport;

    @Autowired
    private IPerson iPerson;

    public Passport save(Passport passport){
        iPassport.save(passport);
        return passport;
    }

    public List<Passport> getAllPassports() {
        return iPassport.findAll();
    }

    public Passport updatePassportByPersonId(int personId, Passport updatedPassport) {
        Optional<Person> existingPersonOptional = iPerson.findById(personId);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            Passport existingPassport = existingPerson.getPassport();
            if (existingPassport != null) {
                existingPassport.setNumber(updatedPassport.getNumber());
                // Guardar el pasaporte actualizado
                return iPassport.save(existingPassport);
            } else {
                throw new IllegalStateException("La persona con ID " + personId + " no tiene pasaporte asociado");
            }
        } else {
            throw new IllegalArgumentException("Persona con el ID " + personId + " no encontrada");
        }
    }

    public Optional<Passport> getPassportById(int id) {
        return iPassport.findById(id);
    }

    public void deletePassportById(int id) {
        iPassport.deleteById(id);
    }

}
