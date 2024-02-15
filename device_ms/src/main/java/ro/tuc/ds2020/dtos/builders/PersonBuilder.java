package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;

public class PersonBuilder {
    private PersonBuilder() {
    }
    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getId());
    }
    public static PersonDetailsDTO toPersonDTO(Person person) {
        return new PersonDetailsDTO(person.getId());
    }
}
