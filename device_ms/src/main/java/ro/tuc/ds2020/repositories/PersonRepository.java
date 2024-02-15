package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
