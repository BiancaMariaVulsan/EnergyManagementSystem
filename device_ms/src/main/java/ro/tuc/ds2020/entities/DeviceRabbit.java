package ro.tuc.ds2020.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class DeviceRabbit {
    private int deviceId;
    private int userId;
    private double maxHourlyEnergConsumption;

    public DeviceRabbit(int deviceId, int userId, double maxHourlyEnergConsumption) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
    }

    public DeviceRabbit(int deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceRabbit() {
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
