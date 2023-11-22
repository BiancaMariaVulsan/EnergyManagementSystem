//package ro.tuc.ds2020.services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ro.tuc.ds2020.dtos.ProcessedDataDTO;
//import ro.tuc.ds2020.dtos.builders.ProcessedDataBuilder;
//import ro.tuc.ds2020.repositories.PersonRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PersonService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
//    private final PersonRepository personRepository;
//
//    @Autowired
//    public PersonService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }
//    public Boolean insert(ProcessedDataDTO personDTO) {
//        Person person = ProcessedDataBuilder.toEntity(personDTO);
//        person = personRepository.save(person);
//        LOGGER.debug("Person with id {} was inserted in db", person.getId());
//        return Boolean.TRUE;
//    }
//
//    public boolean delete(int id) {
//        Person existingPerson = personRepository.findById(id).orElse(null);
//
//        if (existingPerson != null) {
//            // Delete the existingPerson entity from the database
//            personRepository.delete(existingPerson);
//            return true;
//        } else {
//            return false; // Person with the specified ID not found
//        }
//    }
//
//    public List<ProcessedDataDTO> findPersons() {
//        List<Person> personList = personRepository.findAll();
//        return personList.stream()
//                .map(ProcessedDataBuilder::toPersonDTO)
//                .collect(Collectors.toList());
//    }
//}
