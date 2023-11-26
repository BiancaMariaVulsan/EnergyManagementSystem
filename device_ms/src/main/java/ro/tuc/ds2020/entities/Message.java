package ro.tuc.ds2020.entities;

public class Message {
    private DeviceRabbit device;
    private String operation;

    public Message(DeviceRabbit device, String operation) {
        this.device = device;
        this.operation = operation;
    }

    public Message() {
    }

    public DeviceRabbit getDevice() {
        return device;
    }

    public void setDevice(DeviceRabbit device) {
        this.device = device;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
