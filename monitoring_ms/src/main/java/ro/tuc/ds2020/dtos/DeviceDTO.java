package ro.tuc.ds2020.dtos;

public class DeviceDTO {
    private int deviceId;
    private int userId;
    private double maxHourlyEnergConsumption;

    public DeviceDTO(int deviceId, int userId, double maxHourlyEnergConsumption) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
    }

    public DeviceDTO(int deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceDTO() {
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
