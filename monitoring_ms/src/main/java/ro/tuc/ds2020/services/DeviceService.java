package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.ProcessedData;
import ro.tuc.ds2020.repositories.DeviceRepository;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public int insert(DeviceDTO deviceDTO) {
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getDeviceId());
        return device.getDeviceId();
    }

    public void update(DeviceDTO updateDeviceDTO) {
        Device existingDevice = deviceRepository.findById(updateDeviceDTO.getDeviceId());

        if (existingDevice != null) {
            // Update the existingPerson entity with data from updatedPersonDTO
            existingDevice.setMaxHourlyEnergConsumption(updateDeviceDTO.getMaxHourlyEnergConsumption());
            existingDevice.setUserId(updateDeviceDTO.getUserId());

            // Save the updated entity back to the database
            deviceRepository.save(existingDevice);

            LOGGER.debug("Device with id {} was updated in db", existingDevice.getDeviceId());
        } else {
            LOGGER.debug("Device with id {} not found in db", existingDevice.getDeviceId());
            throw new ResourceNotFoundException(ProcessedData.class.getSimpleName() + " with id: " + existingDevice.getDeviceId());
        }
    }

    public boolean delete(int id) {
        Device existingDevice = deviceRepository.findById(id);

        if (existingDevice != null) {
            // Delete the deviceRepository entity from the database
            deviceRepository.delete(existingDevice);
            return true;
        } else {
            return false; // Device with the specified ID not found
        }
    }
}
