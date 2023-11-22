package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.entities.Measurement;

public class MeasurementBuilder {

    private MeasurementBuilder() {
    }

    public static MeasurementDTO toMeasurementDTO(Measurement measurement) {
        return new MeasurementDTO(measurement.getId(), measurement.getTimestamp(), measurement.getValue(), measurement.getDevice());
    }

    public static Measurement toEntity(MeasurementDTO measurementDTO) {
        return new Measurement(measurementDTO.getId(), measurementDTO.getTimestamp(), measurementDTO.getValue(), measurementDTO.getDevice());
    }
}
