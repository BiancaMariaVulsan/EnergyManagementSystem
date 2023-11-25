package ro.tuc.ds2020.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ProcessedData implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;

    @Column(name = "totalEnergyConsumption", nullable = false)
    private double totalConsumption;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "deviceId")
    private Device device;
    @Column(name = "time", nullable = false)
    private Date date;

    public ProcessedData() {
    }

    public ProcessedData(int id, double totalEnergyConsumption, Device device, Date date) {
        this.id = id;
        this.totalConsumption = totalEnergyConsumption;
        this.device = device;
        this.date = date;
    }

    public ProcessedData(double totalEnergyConsumption, Device device, Date date) {
        this.totalConsumption = totalEnergyConsumption;
        this.device = device;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalEnergyConsumption) {
        this.totalConsumption = totalEnergyConsumption;
    }

    public Device getDevie() {
        return device;
    }

    public void setDevie(Device device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
