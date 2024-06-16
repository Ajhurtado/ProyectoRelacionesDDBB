package ec.edu.uce.ProyectoRelation.demo.repositories;

import ec.edu.uce.ProyectoRelation.demo.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPassport extends JpaRepository<Passport, Integer> {
}
