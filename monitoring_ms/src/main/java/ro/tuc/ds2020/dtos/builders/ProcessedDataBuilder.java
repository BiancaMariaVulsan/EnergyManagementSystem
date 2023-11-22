package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.ProcessedDataDTO;
import ro.tuc.ds2020.entities.ProcessedData;

public class ProcessedDataBuilder {
    private ProcessedDataBuilder() {
    }
    public static ProcessedData toEntity(ProcessedDataDTO processedDataDTO) {
        return new ProcessedData(processedDataDTO.getId(), processedDataDTO.getTotalConsumption(), processedDataDTO.getDevice());
    }
    public static ProcessedDataDTO toPersonDTO(ProcessedData processedData) {
        return new ProcessedDataDTO(processedData.getId(), processedData.getTotalConsumption(), processedData.getDevie());
    }
}
