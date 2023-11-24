package ro.tuc.ds2020.services;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.contracts.MeasurementRequest;
import ro.tuc.ds2020.contracts.MeasurementResponse;

import java.util.ArrayList;
import java.util.Date;

@Service
public class MeasurementsService {

    public ArrayList<MeasurementResponse> getMeasurements(MeasurementRequest measurementRequest) {

        ArrayList<MeasurementResponse> measurements = new ArrayList<MeasurementResponse>();
        measurements.add(new MeasurementResponse(1.0, new Date(120)));
        measurements.add(new MeasurementResponse(1.0, new Date(121)));
        measurements.add(new MeasurementResponse(1.0, new Date(122)));
        measurements.add(new MeasurementResponse(1.0, new Date(123)));
        measurements.add(new MeasurementResponse(1.0, new Date(124)));
        return measurements;
    }
}
