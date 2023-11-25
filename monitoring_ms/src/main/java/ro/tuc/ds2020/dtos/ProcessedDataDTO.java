package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;

import java.util.Date;
import java.util.Objects;

public class ProcessedDataDTO {
    private int id;
    private double totalConsumption;
    private Device device;

    private Date date;

    public ProcessedDataDTO() {
    }

    public ProcessedDataDTO(int id, double totalConsumption, Device device, Date date) {
        this.id = id;
        this.totalConsumption = totalConsumption;
        this.device = device;
        this.date = date;
    }

    public ProcessedDataDTO(double totalConsumption, Device device, Date date) {
        this.totalConsumption = totalConsumption;
        this.device = device;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedDataDTO that = (ProcessedDataDTO) o;
        return id == that.id && totalConsumption == that.totalConsumption && Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalConsumption, device);
    }
}
