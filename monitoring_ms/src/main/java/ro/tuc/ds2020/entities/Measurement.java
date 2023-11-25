package ro.tuc.ds2020.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class Measurement {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;

    @Column(name = "time", nullable = false)
    private Date date;

    @Column(name = "value", nullable = false)
    private double value;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "deviceId")
    private Device device;

    public Measurement() {
    }

    public Measurement(int id, Date timestamp, double value, Device device) {
        this.id = id;
        this.date = timestamp;
        this.value = value;
        this.device = device;
    }

    public Measurement(Date timestamp, double value, Device device) {
        this.date = timestamp;
        this.value = value;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date timestamp) {
        this.date = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
