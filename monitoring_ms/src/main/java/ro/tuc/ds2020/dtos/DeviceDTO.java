package ro.tuc.ds2020.dtos;

public class DeviceDTO {
    private int deviceId;
    private int userId;
    private double maxHourlyEnergConsumption;
    private String operation;

    public DeviceDTO(int deviceId, int userId, double maxHourlyEnergConsumption, String operation) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
        this.operation = operation;
    }

    public DeviceDTO(int deviceId, String operation) {
        this.deviceId = deviceId;
        this.operation = operation;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
