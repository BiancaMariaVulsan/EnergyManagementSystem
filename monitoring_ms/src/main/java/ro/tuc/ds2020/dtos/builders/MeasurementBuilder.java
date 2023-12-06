package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Measurement;

import java.util.Date;

public class MeasurementBuilder {

    private MeasurementBuilder() {
    }

    public static Measurement toEntity(MeasurementDTO measurementDTO) {
        return new Measurement(new Date(measurementDTO.getTimestamp()), measurementDTO.getValue(), new Device(measurementDTO.getDeviceId()));
    }
}
