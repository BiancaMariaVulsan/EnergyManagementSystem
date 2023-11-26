package ro.tuc.ds2020.dtos;

public class MessageDTO {
    private DeviceDTO deviceDTO;
    private String operation;

    public MessageDTO(DeviceDTO deviceDTO, String operation) {
        this.deviceDTO = deviceDTO;
        this.operation = operation;
    }

    public MessageDTO() {
    }

    public DeviceDTO getDeviceDTO() {
        return deviceDTO;
    }

    public void setDeviceDTO(DeviceDTO deviceDTO) {
        this.deviceDTO = deviceDTO;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
