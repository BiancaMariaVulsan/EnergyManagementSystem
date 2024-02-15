package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
