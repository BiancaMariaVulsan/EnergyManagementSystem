package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.contracts.MeasurementRequest;
import ro.tuc.ds2020.contracts.MeasurementResponse;
import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.dtos.builders.MeasurementBuilder;
import ro.tuc.ds2020.entities.Measurement;
import ro.tuc.ds2020.repositories.MeasurementRepository;

import java.util.ArrayList;
import java.util.Date;

@Service
public class MeasurementsService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementsService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public ArrayList<MeasurementResponse> getMeasurements(MeasurementRequest measurementRequest) {

        ArrayList<MeasurementResponse> measurements = new ArrayList<MeasurementResponse>();
        measurements.add(new MeasurementResponse(1.0, new Date(120)));
        measurements.add(new MeasurementResponse(1.0, new Date(121)));
        measurements.add(new MeasurementResponse(1.0, new Date(122)));
        measurements.add(new MeasurementResponse(1.0, new Date(123)));
        measurements.add(new MeasurementResponse(1.0, new Date(124)));
        return measurements;
    }

    public int insert(MeasurementDTO measurementDTO) {
        Measurement measurement = MeasurementBuilder.toEntity(measurementDTO);
        measurementRepository.save(measurement);
        return measurement.getId();
    }
}
