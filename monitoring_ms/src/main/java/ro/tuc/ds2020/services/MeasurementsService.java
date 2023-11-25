package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.dtos.builders.MeasurementBuilder;
import ro.tuc.ds2020.entities.Measurement;
import ro.tuc.ds2020.repositories.MeasurementRepository;

@Service
public class MeasurementsService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementsService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public int insert(MeasurementDTO measurementDTO) {
        Measurement measurement = MeasurementBuilder.toEntity(measurementDTO);
        measurementRepository.save(measurement);
        return measurement.getId();
    }
}
