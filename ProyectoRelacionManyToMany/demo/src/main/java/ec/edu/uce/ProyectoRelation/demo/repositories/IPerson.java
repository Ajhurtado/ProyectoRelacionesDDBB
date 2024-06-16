package ec.edu.uce.ProyectoRelation.demo.repositories;


import ec.edu.uce.ProyectoRelation.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerson extends JpaRepository<Person,Integer> {
}
