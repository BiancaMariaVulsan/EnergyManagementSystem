package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Example: JPA generate Query by Field
     */
    Optional<Person> findByUsername(String username);

    /**
     * Example: Write Custom Query
     */
//    @Query(value = "SELECT p " +
//            "FROM Person p " +
//            "WHERE p.userName = :username ")
//    Optional<Person> findByUsername(@Param("username") String username);

}
