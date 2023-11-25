package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Notification;
import ro.tuc.ds2020.entities.ProcessedData;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.ProcessedDataRepository;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ProcessedMeasurementsService {
    HashMap<Integer, ArrayList<Double>> measurements;
    ProcessedDataRepository processedDataRepository;
    private SimpMessagingTemplate messagingTemplate;
    DeviceRepository deviceRepository;

    @Autowired
    public ProcessedMeasurementsService(ProcessedDataRepository processedDataRepository, DeviceRepository deviceRepository) {
        this.processedDataRepository = processedDataRepository;
        this.deviceRepository = deviceRepository;
        measurements = new HashMap<>();
    }

    public void addMeasurement(MeasurementDTO measurement) {
        int deviceId = measurement.getDeviceId();

        if (measurements.containsKey(deviceId)) {
            ArrayList<Double> deviceMeasurements = measurements.get(deviceId);

            deviceMeasurements.add(measurement.getValue());

            if (deviceMeasurements.size() > 60) {
                deviceMeasurements.remove(0);
            }

            if (deviceMeasurements.size() == 60) {
                computeHourlyConsumption(deviceId);
            }

        } else {
            ArrayList<Double> deviceMeasurements = new ArrayList<>();
            deviceMeasurements.add(measurement.getValue());
            measurements.put(deviceId, deviceMeasurements);
        }
    }

    public void computeHourlyConsumption(int deviceId) {
        double totalConspumption = 0;
        for (Double value: measurements.get(deviceId)) {
            totalConspumption += value;
        }
        totalConspumption /= measurements.get(deviceId).size();
        Device device = deviceRepository.findById(deviceId);
        ProcessedData processedData = new ProcessedData(totalConspumption, device);
        insert(processedData);

        if (totalConspumption > device.getMaxHourlyEnergConsumption()) {
            sendNotification(deviceId, "High energy consumption alert!");
        }
    }

    public void insert(ProcessedData processedData) {
        processedDataRepository.save(processedData);
    }

    private void sendNotification(int deviceId, String message) {
        // Construct the notification message
        Notification notification = new Notification(message);

        // Broadcast the notification to the WebSocket topic
        messagingTemplate.convertAndSend("/topic/notification", notification);
    }
}
