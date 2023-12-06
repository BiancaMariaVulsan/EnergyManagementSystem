package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.Device;

public class DeviceBuilder {
    public static Device toEntity(DeviceDTO deviceDTO) {
        return new Device(deviceDTO.getDeviceId(), deviceDTO.getUserId(), deviceDTO.getMaxHourlyEnergConsumption());
    }
}
