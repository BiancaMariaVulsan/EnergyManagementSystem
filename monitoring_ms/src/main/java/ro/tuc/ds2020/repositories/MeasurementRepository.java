package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Measurement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByDateAndDevice(Date date, Device device);
}
