package ec.edu.uce.ProyectoRelation.demo.repositories;

import ec.edu.uce.ProyectoRelation.demo.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrip extends JpaRepository<Trip,Integer> {
}
