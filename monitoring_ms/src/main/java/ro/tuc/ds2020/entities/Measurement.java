package ro.tuc.ds2020.entities;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Measurement {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;

    @Column(name = "timestamp", nullable = false)
    private int timestamp;

    @Column(name = "value", nullable = false)
    private int value;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "deviceId")
    private Device device;

    public Measurement() {
    }

    public Measurement(int id, int timestamp, int value, Device device) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
        this.device = device;
    }

    public Measurement(int timestamp, int value, Device device) {
        this.timestamp = timestamp;
        this.value = value;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
