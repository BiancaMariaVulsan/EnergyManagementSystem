//package ro.tuc.ds2020.controllers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
//import ro.tuc.ds2020.dtos.ProcessedDataDTO;
//import ro.tuc.ds2020.services.DeviceService;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value = "/device")
//public class DeviceController {
//
//    private final DeviceService deviceService;
//
//    @Autowired
//    public DeviceController(DeviceService deviceService) {
//        this.deviceService = deviceService;
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<ProcessedDataDTO>> getDevices() {
//        List<ProcessedDataDTO> dtos = deviceService.findDevices();
//        return new ResponseEntity<>(dtos, HttpStatus.OK);
//    }
//
//    @PostMapping()
//    public ResponseEntity<Integer> insertDevice(@Valid @RequestBody ProcessedDataDTO deviceDTO) {
//        int deviceID = deviceService.insert(deviceDTO);
//        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
//    }
//
//    // post cu insert person => tabela de user cu userid
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<List<ProcessedDataDTO>> getDevice(@PathVariable("id") Integer personId) {
//        List<ProcessedDataDTO> dto = deviceService.findDevicesById(personId);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Boolean> updateDevice(@PathVariable int id, @Valid @RequestBody ProcessedDataDTO updatedDeviceDTO) {
//        try {
//            deviceService.update(id, updatedDeviceDTO);
//            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
//        } catch (ResourceNotFoundException e) {
//            // Handle the case where the device with the given ID was not found
//            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            // Handle other exceptions if necessary
//            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> deleteDevice(@PathVariable Integer id) {
//        try {
//            boolean deleted = deviceService.delete(id);
//            if (deleted) {
//                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
//            } else {
//                // Handle the case where the device with the given ID was not found
//                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            // Handle other exceptions if necessary
//            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
