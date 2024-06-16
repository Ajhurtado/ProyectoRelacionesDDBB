package ec.edu.uce.ProyectoRelation.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_passport")
    @SequenceGenerator(name = "seq_passport", sequenceName = "seq_passport", allocationSize = 1)
    private int id;

    @Column
    private String number;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
