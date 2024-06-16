package ec.edu.uce.ProyectoRelation.demo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "destination")
    private String destination;

    @Column(name = "date")
    private String date;

    @ManyToMany(mappedBy = "trips")
    private Set<Person> persons = new HashSet<>();

    public Trip() {
    }

    public Trip(String destination, String date) {
        this.destination = destination;
        this.date = date;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

