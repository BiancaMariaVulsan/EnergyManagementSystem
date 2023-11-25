import java.util.Date;

public class Measurement {
    private Date timestamp;
    private int deviceId;
    private double value;

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
