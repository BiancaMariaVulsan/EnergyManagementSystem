//package ro.tuc.ds2020.services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
//import ro.tuc.ds2020.dtos.ProcessedDataDTO;
//import ro.tuc.ds2020.dtos.builders.MeasurementBuilder;
//import ro.tuc.ds2020.entities.ProcessedData;
//import ro.tuc.ds2020.repositories.DeviceRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class DeviceService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
//    private final DeviceRepository deviceRepository;
//
//    @Autowired
//    public DeviceService(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
//
//    public List<ProcessedDataDTO> findDevices() {
//        List<ProcessedData> deviceList = deviceRepository.findAll();
//        return deviceList.stream()
//                .map(MeasurementBuilder::toDeviceDetailsDTO)
//                .collect(Collectors.toList());
//    }
//
//    public List<ProcessedDataDTO> findDevicesById(int id) {
//        List<ProcessedData> devices = deviceRepository.findByUserId(id);
//        if (devices.isEmpty()) {
//            LOGGER.error("Device with id {} was not found in db", id);
//            throw new ResourceNotFoundException(ProcessedData.class.getSimpleName() + " with id: " + id);
//        }
//        return devices.stream()
//                .map(MeasurementBuilder::toDeviceDetailsDTO)
//                .collect(Collectors.toList());
//    }
//
//    public int insert(ProcessedDataDTO deviceDTO) {
//        ProcessedData device = MeasurementBuilder.toEntity(deviceDTO);
//        device = deviceRepository.save(device);
//        LOGGER.debug("Device with id {} was inserted in db", device.getId());
//        return device.getId();
//    }
//
//    public void update(int id, ProcessedDataDTO updatedPersonDTO) {
//        ProcessedData existingDevice = deviceRepository.findById(id).orElse(null);
//
//        if (existingDevice != null) {
//            // Update the existingPerson entity with data from updatedPersonDTO
//            existingDevice.setDescription(updatedPersonDTO.getDescription());
//            existingDevice.setAddress(updatedPersonDTO.getAddress());
//            existingDevice.setMaxHourlyEnergConsumption(updatedPersonDTO.getMaxHourlyEnergConsumption());
//            existingDevice.setPersonId(new Person(updatedPersonDTO.getPersonId()));
//
//            // Save the updated entity back to the database
//            deviceRepository.save(existingDevice);
//
//            LOGGER.debug("Device with id {} was updated in db", id);
//        } else {
//            LOGGER.debug("Device with id {} not found in db", id);
//            throw new ResourceNotFoundException(ProcessedData.class.getSimpleName() + " with id: " + id);
//        }
//    }
//
//    public boolean delete(int id) {
//        ProcessedData existingDevice = deviceRepository.findById(id).orElse(null);
//
//        if (existingDevice != null) {
//            // Delete the deviceRepository entity from the database
//            deviceRepository.delete(existingDevice);
//            return true;
//        } else {
//            return false; // Device with the specified ID not found
//        }
//    }
//}
