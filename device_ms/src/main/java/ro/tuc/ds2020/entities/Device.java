package ro.tuc.ds2020.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
public class Device implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "maxHourlyEnergConsumption", nullable = false)
    private int maxHourlyEnergConsumption;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "person_id")
    private Person personId;

    public Device() {
    }

    public Device(String description, String address, int maxHourlyEnergConsumption, Person personId) {
        this.description = description;
        this.address = address;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
        this.personId = personId;
    }

    public Device(int id, String description, String address, int maxHourlyEnergConsumption, Person personId) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxHourlyEnergConsumption() {
        return maxHourlyEnergConsumption;
    }

    public void setMaxHourlyEnergConsumption(int maxHourlyEnergConsumption) {
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }
}
