import java.util.Date;

public class Measurement {
    private Date timestamp;
    private int deviceId;
    private double measurementValue;

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setMeasurementValue(double measurementValue) {
        this.measurementValue = measurementValue;
    }
}
