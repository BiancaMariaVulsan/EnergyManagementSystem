package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.Objects;

public class MeasurementDTO {

    private Date timestamp;
    private int deviceId;
    private double value;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Date timestamp, double value, int device) {
        this.timestamp = timestamp;
        this.value = value;
        this.deviceId = device;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementDTO that = (MeasurementDTO) o;
        return timestamp == that.timestamp && value == that.value && Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, value, deviceId);
    }
}
