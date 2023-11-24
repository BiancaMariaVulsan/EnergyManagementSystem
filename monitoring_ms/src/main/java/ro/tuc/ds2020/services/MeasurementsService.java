package ro.tuc.ds2020.services;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.contracts.MeasurementRequest;
import ro.tuc.ds2020.contracts.MeasurementResponse;

import javax.media.Time;
import java.util.ArrayList;

@Service
public class MeasurementsService {

    public ArrayList<MeasurementResponse> getMeasurements(MeasurementRequest measurementRequest) {

        ArrayList<MeasurementResponse> measurements = new ArrayList<MeasurementResponse>();
        measurements.add(new MeasurementResponse(1.0, new Time(120)));
        measurements.add(new MeasurementResponse(1.0, new Time(121)));
        measurements.add(new MeasurementResponse(1.0, new Time(122)));
        measurements.add(new MeasurementResponse(1.0, new Time(123)));
        measurements.add(new MeasurementResponse(1.0, new Time(124)));
        return measurements;
    }
}
