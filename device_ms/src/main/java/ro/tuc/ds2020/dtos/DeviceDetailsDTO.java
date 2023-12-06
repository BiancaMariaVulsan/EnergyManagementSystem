package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.ConsumptionLimit;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DeviceDetailsDTO {

    private int id;
    @NotNull
    private String description;
    @NotNull
    private String address;
    @ConsumptionLimit(limit = 0)
    private int maxHourlyEnergConsumption;

    private int personId;

    public DeviceDetailsDTO() {
    }

    public DeviceDetailsDTO(String description, String address, int maxHourlyEnergConsumption, int personId) {
        this.description = description;
        this.address = address;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
        this.personId = personId;
    }

    public DeviceDetailsDTO(int id, String description, String address, int maxHourlyEnergConsumption, int personId) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxHourlyEnergConsumption() {
        return maxHourlyEnergConsumption;
    }

    public void setMaxHourlyEnergConsumption(int maxHourlyEnergConsumption) {
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDetailsDTO that = (DeviceDetailsDTO) o;
        return maxHourlyEnergConsumption == that.maxHourlyEnergConsumption &&
                Objects.equals(description, that.description) &&
                Objects.equals(maxHourlyEnergConsumption, that.maxHourlyEnergConsumption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, address, maxHourlyEnergConsumption);
    }
}
