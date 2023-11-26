package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Measurement;

public class MeasurementBuilder {

    private MeasurementBuilder() {
    }

    public static MeasurementDTO toMeasurementDTO(Measurement measurement) {
        return new MeasurementDTO(measurement.getDate(), measurement.getValue(), measurement.getDevice().getDeviceId());
    }

    public static Measurement toEntity(MeasurementDTO measurementDTO) {
        return new Measurement(measurementDTO.getTimestamp(), measurementDTO.getValue(), new Device(measurementDTO.getDeviceId()));
    }
}
