package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.contracts.responses.LoginResponse;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;

public class PersonBuilder {

    private PersonBuilder() {
    }

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getId(), person.getFirstName(), person.getLastName(), person.getUsername(), person.getRole(), person.getPasswordHash());
    }

    public static PersonDetailsDTO toPersonDetailsDTO(Person person) {
        return new PersonDetailsDTO(person.getId(), person.getFirstName(), person.getLastName(), person.getUsername(), person.getEmail(), person.getRole(), person.getPasswordHash());
    }

    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getFirstName(), personDetailsDTO.getLastName(), personDetailsDTO.getUsername(), personDetailsDTO.getEmail(), "", personDetailsDTO.getRole(), personDetailsDTO.getPassword());
    }

    public static LoginResponse toLoginResponse(Person person, String token) {
        return new LoginResponse(person.getId(), person.getEmail(), person.getUsername(), person.getFirstName(), person.getLastName(), token, person.getRole());
    }
}
