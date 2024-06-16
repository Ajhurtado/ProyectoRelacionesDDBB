package ec.edu.uce.ProyectoRelation.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trip")
    @SequenceGenerator(name = "seq_trip", sequenceName = "seq_trip", allocationSize = 1)
    private int id;

    @Column
    private String destination;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // Constructors, Getters, and Setters

    public Trip() {
    }

    public Trip(String destination, String date) {
        this.destination = destination;
        this.date = date;
    }

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
