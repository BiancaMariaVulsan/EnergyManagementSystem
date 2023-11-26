package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.DeviceRabbit;
import ro.tuc.ds2020.entities.Person;

public class DeviceBuilder {

    private DeviceBuilder() {
    }

    public static DeviceDTO toDeviceDTO(Device person) {
        return new DeviceDTO(person.getId(), person.getDescription(), person.getAddress(), person.getMaxHourlyEnergConsumption());
    }

    public static DeviceDetailsDTO toDeviceDetailsDTO(Device device) {
        return new DeviceDetailsDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMaxHourlyEnergConsumption(), device.getPersonId().getId());
    }

    public static Device toEntity(DeviceDetailsDTO deviceDetailsDTO) {
        return new Device(deviceDetailsDTO.getDescription(),
                deviceDetailsDTO.getAddress(),
                deviceDetailsDTO.getMaxHourlyEnergConsumption(), new Person(deviceDetailsDTO.getPersonId()));
    }

    public static DeviceRabbit toDeviceRabbit(DeviceDetailsDTO device, String operation) {
        return new DeviceRabbit(device.getId(), device.getPersonId(), device.getMaxHourlyEnergConsumption(), operation);
    }
}
