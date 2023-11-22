package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Query("SELECT d FROM Device d WHERE d.personId.id = :id")
    List<Device> findByUserId(@Param("id") int id);
}
