package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.contracts.MeasurementResponse;
import ro.tuc.ds2020.dtos.ProcessedDataDTO;
import ro.tuc.ds2020.entities.Measurement;
import ro.tuc.ds2020.entities.ProcessedData;

public class ProcessedDataBuilder {
    private ProcessedDataBuilder() {
    }
    public static ProcessedData toEntity(ProcessedDataDTO processedDataDTO) {
        return new ProcessedData(processedDataDTO.getId(), processedDataDTO.getTotalConsumption(), processedDataDTO.getDevice(), processedDataDTO.getDate());
    }
    public static ProcessedDataDTO toPersonDTO(ProcessedData processedData) {
        return new ProcessedDataDTO(processedData.getId(), processedData.getTotalConsumption(), processedData.getDevie(), processedData.getDate());
    }
    public static MeasurementResponse toResponse(ProcessedData processedData) {
        return new MeasurementResponse(processedData.getTotalConsumption(), processedData.getDate());
    }
}
