package ro.tuc.ds2020.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Role implements Serializable {

    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
