package ro.tuc.ds2020.entities;

public class Notification {
    private int deviceId;
    private String message;

    public Notification(String message, int deviceId) {
        this.message = message;
        this.deviceId = deviceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}
