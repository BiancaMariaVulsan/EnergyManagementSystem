package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.contracts.MeasurementRequest;
import ro.tuc.ds2020.contracts.MeasurementResponse;
import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.dtos.builders.ProcessedDataBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Notification;
import ro.tuc.ds2020.entities.ProcessedData;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.ProcessedDataRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;

@Service
public class ProcessedMeasurementsService {
    private HashMap<Integer, ArrayList<Double>> measurements;
    private SimpMessagingTemplate messagingTemplate;

    private ProcessedDataRepository processedDataRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public ProcessedMeasurementsService(ProcessedDataRepository processedDataRepository,
                                        DeviceRepository deviceRepository,
                                        SimpMessagingTemplate messagingTemplate) {
        this.processedDataRepository = processedDataRepository;
        this.deviceRepository = deviceRepository;
        this.messagingTemplate = messagingTemplate;
        measurements = new HashMap<>();
    }

    public void addMeasurement(MeasurementDTO measurement) {
        int deviceId = measurement.getDeviceId();

        if (measurements.containsKey(deviceId)) {
            ArrayList<Double> deviceMeasurements = measurements.get(deviceId);

            deviceMeasurements.add(measurement.getValue());

            if (deviceMeasurements.size() == 5) {
                Date endDate = measurement.getTimestamp();
                List<Double> copyOfMeasurements = new ArrayList<>(deviceMeasurements);
                ProcessedData processedData = computeHourlyConsumption(deviceId, endDate, copyOfMeasurements);
                Device device = deviceRepository.findById(processedData.getDevie().getDeviceId());
                deviceMeasurements.clear();
                if (processedData.getTotalConsumption() > device.getMaxHourlyEnergConsumption()) {
                    sendNotificationAsync(device.getUserId(), deviceId, "High energy consumption alert for device ");
                }
            }
        } else {
            ArrayList<Double> deviceMeasurements = new ArrayList<>();
            deviceMeasurements.add(measurement.getValue());
            measurements.put(deviceId, deviceMeasurements);
        }
    }

    public ProcessedData computeHourlyConsumption(int deviceId, Date endDate, List<Double> measurementsCopy) {
        int size = measurementsCopy.size();
        double firstValue = measurementsCopy.get(0);
        double lastValue = measurementsCopy.get(size-1);
        double totalConspumption = lastValue - firstValue;
        Device device = deviceRepository.findById(deviceId);
        ProcessedData processedData = new ProcessedData(totalConspumption, device, endDate);
        insert(processedData);

        return processedData;
    }

    public void insert(ProcessedData processedData) {
        processedDataRepository.save(processedData);
    }

    private CompletableFuture<Void> sendNotificationAsync(int userId, int deviceId, String message) {
        return CompletableFuture.runAsync(() -> sendNotification(userId, deviceId, message));
    }

    private void sendNotification(int userId, int deviceId, String message) {
        // Construct the notification message
        Notification notification = new Notification(message, deviceId);

        // Broadcast the notification to the WebSocket topic
        messagingTemplate.convertAndSend("/topic/notification/" + userId, notification);
    }

    public List<MeasurementResponse> getMeasurements(MeasurementRequest measurementRequest) {

        List<ProcessedData> measurements = processedDataRepository.findByDateAndDevice(measurementRequest.getDate(), new Device(measurementRequest.getDeviceId()));
        return measurements.stream().map(ProcessedDataBuilder::toResponse).collect(Collectors.toList());
    }
}
