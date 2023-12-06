package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {
    private int id;
    private String description;

    private String address;
    private int maxHourlyEnergConsumption;

    public DeviceDTO() {
    }

    public DeviceDTO(int id, String description, String address, int maxHourlyEnergConsumption) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
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

    public int getMaxHourlyEnergConsumption() {
        return maxHourlyEnergConsumption;
    }

    public void setMaxHourlyEnergConsumption(int maxHourlyEnergConsumption) {
        this.maxHourlyEnergConsumption = maxHourlyEnergConsumption;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return maxHourlyEnergConsumption == deviceDTO.maxHourlyEnergConsumption && id == deviceDTO.id && description.equals(deviceDTO.description) && address.equals(deviceDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, description, address, maxHourlyEnergConsumption);
    }
}
