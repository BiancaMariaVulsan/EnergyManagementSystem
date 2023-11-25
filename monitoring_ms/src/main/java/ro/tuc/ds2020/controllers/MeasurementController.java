package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.contracts.MeasurementRequest;
import ro.tuc.ds2020.contracts.MeasurementResponse;
import ro.tuc.ds2020.services.MeasurementsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class MeasurementController {

    private final MeasurementsService measurementsService;

    @Autowired
    public MeasurementController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @PostMapping()
    public ResponseEntity<List<MeasurementResponse>> insertMeasurement(@RequestBody MeasurementRequest measurementRequest) {
        List<MeasurementResponse> measurements = measurementsService.getMeasurements(measurementRequest);
        return new ResponseEntity<>(measurements, HttpStatus.CREATED);
    }
}
