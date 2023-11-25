package ro.tuc.ds2020.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

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

    public ProcessedData() {
    }

    public ProcessedData(int id, double totalEnergyConsumption, Device device) {
        this.id = id;
        this.totalConsumption = totalEnergyConsumption;
        this.device = device;
    }

    public ProcessedData(double totalEnergyConsumption, Device device) {
        this.totalConsumption = totalEnergyConsumption;
        this.device = device;
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

}
