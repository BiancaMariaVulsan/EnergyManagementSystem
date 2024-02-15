package ro.tuc.ds2020.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Entity
public class Person  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @OneToMany(mappedBy = "personId", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Device> devices;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, List<Device> devices) {
        this.id = id;
        this.devices = devices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
