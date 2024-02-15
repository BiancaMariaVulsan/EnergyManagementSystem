package ro.tuc.ds2020.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.configs.JwtService;
import ro.tuc.ds2020.contracts.responses.LoginResponse;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public List<PersonDetailsDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDetailsDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(int id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(personOptional.get());
    }

    public LoginResponse insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        String token = jwtService.generateToken(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return PersonBuilder.toLoginResponse(person, token);
    }

    public void update(int id, PersonDetailsDTO updatedPersonDTO) {
        Person existingPerson = personRepository.findById(id).orElse(null);

        if (existingPerson != null) {
            // Update the existingPerson entity with data from updatedPersonDTO
            existingPerson.setFirstName(updatedPersonDTO.getFirstName());
            existingPerson.setLastName(updatedPersonDTO.getLastName());
            existingPerson.setEmail(updatedPersonDTO.getEmail());
            existingPerson.setUsername(updatedPersonDTO.getUsername());
            existingPerson.setPasswordHash(updatedPersonDTO.getPassword());
            existingPerson.setRole(updatedPersonDTO.getRole());

            // Save the updated entity back to the database
            personRepository.save(existingPerson);

            LOGGER.debug("Person with id {} was updated in db", id);
        } else {
            LOGGER.debug("Person with id {} not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
    }

    public boolean delete(int id) {
        Person existingPerson = personRepository.findById(id).orElse(null);

        if (existingPerson != null) {
            // Delete the existingPerson entity from the database
            personRepository.delete(existingPerson);
            return true;
        } else {
            return false; // Person with the specified ID not found
        }
    }

    public LoginResponse login(String username, String password) {
        Optional<Person> person = personRepository.findByUsername(username);

        if (person.isPresent() && isValidPassword(password, person.get().getPasswordHash())) {
            final String token = jwtService.generateToken(person.get());
            LOGGER.debug("Login successful for user with id {}", person.get().getId());
            return PersonBuilder.toLoginResponse(person.get(), token);
        } else {
            LOGGER.debug("Login failed for user with username {}", username);
            throw new ResourceNotFoundException(Person.class.getSimpleName());
        }
    }

    private boolean isValidPassword(String inputPassword, String storedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(inputPassword, storedPassword);
    }

}
