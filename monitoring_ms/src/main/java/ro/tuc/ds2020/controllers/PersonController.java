//package ro.tuc.ds2020.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.tuc.ds2020.dtos.ProcessedDataDTO;
//import ro.tuc.ds2020.services.PersonService;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping(value = "/person")
//public class PersonController {
//    private final PersonService personService;
//
//    @Autowired
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<ProcessedDataDTO>> getDevices() {
//        List<ProcessedDataDTO> dtos = personService.findPersons();
//        return new ResponseEntity<>(dtos, HttpStatus.OK);
//    }
//
//    @PostMapping()
//    public ResponseEntity<Boolean> insertPerson(@Valid @RequestBody ProcessedDataDTO personDetailsDTO) {
//        Boolean insertResponse = personService.insert(personDetailsDTO);
//        return new ResponseEntity<>(insertResponse, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> deletePerson(@PathVariable Integer id) {
//        try {
//            boolean deleted = personService.delete(id);
//            if (deleted) {
//                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
//            } else {
//                // Handle the case where the person with the given ID was not found
//                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            // Handle other exceptions if necessary
//            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
