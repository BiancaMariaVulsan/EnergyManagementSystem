package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;

import java.util.Objects;

public class MeasurementDTO {

    private int id;
    private int timestamp;
    private int value;

    private Device device;


    public MeasurementDTO() {
    }

    public MeasurementDTO(int id, int timestamp, int value, Device device) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
        this.device = device;
    }

    public MeasurementDTO(int timestamp, int value, Device device) {
        this.timestamp = timestamp;
        this.value = value;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementDTO that = (MeasurementDTO) o;
        return id == that.id && timestamp == that.timestamp && value == that.value && Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, value, device);
    }
}
