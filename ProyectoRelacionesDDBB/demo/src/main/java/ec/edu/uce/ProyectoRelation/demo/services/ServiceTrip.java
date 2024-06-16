package ec.edu.uce.ProyectoRelation.demo.services;

import ec.edu.uce.ProyectoRelation.demo.repositories.ITrip;
import ec.edu.uce.ProyectoRelation.demo.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTrip {

    @Autowired
    private ITrip iTrip;

    public Trip save(Trip trip) {
        return iTrip.save(trip);
    }

    public List<Trip> getAllTrips() {
        return iTrip.findAll();
    }

    public Optional<Trip> getTripById(int id) {
        return iTrip.findById(id);
    }

    public Trip updateTrip(int id, Trip updatedTrip) {
        Optional<Trip> existingTripOptional = iTrip.findById(id);
        if (existingTripOptional.isPresent()) {
            Trip existingTrip = existingTripOptional.get();
            existingTrip.setDestination(updatedTrip.getDestination());
            existingTrip.setDate(updatedTrip.getDate());
            return iTrip.save(existingTrip);
        } else {
            throw new IllegalArgumentException("Viaje con el ID " + id + " no encontrado");
        }
    }

    public void deleteTripById(int id) {
        iTrip.deleteById(id);
    }

}
