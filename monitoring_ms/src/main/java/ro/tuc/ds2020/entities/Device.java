package ro.tuc.ds2020.entities;

import jakarta.persistence.*;

@Entity
public class Device {
    @Id
    @Column(name = "deviceId", nullable = false)
    private int deviceId;
    @Column(name = "userId", nullable = false)
    private int userId;
    @Column(name = "maxHourlyEnergConsumption", nullable = false)
    private double maxHourlyEnergConsumption;

    public Device(int deviceId) {
        this.deviceId = deviceId;
    }

    public Device() {
    }


    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getMaxHourlyEnergConsumption() {
        return maxHourlyEnergConsumption;
    }

    public void setMaxHourlyEnergConsumption(double maxHourlyEnergConsumption) {
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
    }
}
