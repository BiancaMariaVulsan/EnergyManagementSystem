package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.entities.DeviceRabbit;
import ro.tuc.ds2020.services.RabbitMqSender;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDetailsDTO>> getDevices() {
        List<DeviceDetailsDTO> dtos = deviceService.findDevices();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertDevice(@Valid @RequestBody DeviceDetailsDTO deviceDTO) {
        int deviceID = deviceService.insert(deviceDTO);
        try {
            RabbitMqSender.send(DeviceBuilder.toDeviceRabbit(deviceDTO, "insert"));
        } catch (Exception e) {
            System.out.println("Error sending to RabbitMQ");
        }
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<DeviceDetailsDTO>> getDevice(@PathVariable("id") Integer personId) {
        List<DeviceDetailsDTO> dto = deviceService.findDevicesById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDevice(@PathVariable int id, @Valid @RequestBody DeviceDetailsDTO updatedDeviceDTO) {
        try {
            deviceService.update(id, updatedDeviceDTO);
            try {
                RabbitMqSender.send(DeviceBuilder.toDeviceRabbit(updatedDeviceDTO, "update"));
            } catch (Exception e) {
                System.out.println("Error sending to RabbitMQ");
            }
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            // Handle the case where the device with the given ID was not found
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions if necessary
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDevice(@PathVariable Integer id) {
        try {
            boolean deleted = deviceService.delete(id);
            if (deleted) {
                try {
                    RabbitMqSender.send(new DeviceRabbit(id, "delete"));
                } catch (Exception e) {
                    System.out.println("Error sending to RabbitMQ");
                }
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
            } else {
                // Handle the case where the device with the given ID was not found
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle other exceptions if necessary
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
