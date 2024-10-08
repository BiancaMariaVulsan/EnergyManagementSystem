package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.ProcessedData;

import java.util.List;

@Repository
public interface ProcessedDataRepository extends JpaRepository<ProcessedData, Integer> {
    List<ProcessedData> findByDevice(Device device);
}
