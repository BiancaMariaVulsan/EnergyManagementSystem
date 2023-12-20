package ro.tuc.ds2020.entities;

public class Notification {
    private int toPersonId;
    private int fromPersonId;
    private String message;

    public Notification(String message, int deviceId, int fromPersonId) {
        this.message = message;
        this.toPersonId = deviceId;
        this.fromPersonId = fromPersonId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getToPersonId() {
        return toPersonId;
    }

    public void setToPersonId(int toPersonId) {
        this.toPersonId = toPersonId;
    }

    public int getFromPersonId() {
        return fromPersonId;
    }

    public void setFromPersonId(int fromPersonId) {
        this.fromPersonId = fromPersonId;
    }
}
