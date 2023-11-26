package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.contracts.MeasurementRequest;
import ro.tuc.ds2020.contracts.MeasurementResponse;
import ro.tuc.ds2020.services.ProcessedMeasurementsService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/measurement")
public class MeasurementController {

    private final ProcessedMeasurementsService processedMeasurementsService;

    @Autowired
    public MeasurementController(ProcessedMeasurementsService processedMeasurementsService) {
        this.processedMeasurementsService = processedMeasurementsService;
    }

    @PostMapping()
    public ResponseEntity<List<MeasurementResponse>> getHistoricalData(@RequestBody MeasurementRequest measurementRequest) {
        List<MeasurementResponse> measurements = processedMeasurementsService.getMeasurements(measurementRequest);
        return new ResponseEntity<>(measurements, HttpStatus.CREATED);
    }
}
