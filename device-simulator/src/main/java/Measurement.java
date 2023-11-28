import java.util.Date;

public class Measurement {
    private long timestamp;
    private int deviceId;
    private double value;

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
