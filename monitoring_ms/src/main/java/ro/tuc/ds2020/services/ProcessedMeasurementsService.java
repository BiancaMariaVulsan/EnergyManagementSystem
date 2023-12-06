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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProcessedMeasurementsService {
    private static HashMap<Integer, ArrayList<Double>> measurements;
    private SimpMessagingTemplate messagingTemplate;

    private ProcessedDataRepository processedDataRepository;
    private DeviceRepository deviceRepository;

    private static HashMap<Integer, Date> startDateTable;

    @Autowired
    public ProcessedMeasurementsService(ProcessedDataRepository processedDataRepository,
                                        DeviceRepository deviceRepository,
                                        SimpMessagingTemplate messagingTemplate) {
        this.processedDataRepository = processedDataRepository;
        this.deviceRepository = deviceRepository;
        this.messagingTemplate = messagingTemplate;
        measurements = new HashMap<>();
        startDateTable = new HashMap<>();
    }

    public void addMeasurement(MeasurementDTO measurement) {
        int deviceId = measurement.getDeviceId();

        if (!measurements.containsKey(deviceId)) {
            ArrayList<Double> deviceMeasurements = new ArrayList<>();
            measurements.put(deviceId, deviceMeasurements);
        }

        ArrayList<Double> deviceMeasurements = measurements.get(deviceId);
        Date measurementHour = new Date(measurement.getTimestamp());

        Date startDate = startDateTable.getOrDefault(deviceId, measurementHour);

        if (deviceMeasurements.size() == 0) {
            startDate = measurementHour;
        }

        if (getHourFromTimestamp(measurementHour) != getHourFromTimestamp(startDate)) {
            // compute the hourly consumption
            List<Double> copyOfMeasurements = new ArrayList<>(deviceMeasurements);
            ProcessedData processedData = computeHourlyConsumption(deviceId, startDate, copyOfMeasurements);

            // start the measurement for the next hour
            deviceMeasurements.clear();
            startDate = measurementHour;
            deviceMeasurements.add(measurement.getValue());

            // send notification
            Device device = deviceRepository.findById(processedData.getDevie().getDeviceId());
            if (processedData.getTotalConsumption() > device.getMaxHourlyEnergConsumption()) {
                sendNotification(device.getUserId(), deviceId, "High energy consumption alert for device ");
            }

        } else {
            // Avoid negative values when reconnecting the device
            int lastIndex = deviceMeasurements.size() - 1;
            if(lastIndex >=0 && measurement.getValue() < deviceMeasurements.get(lastIndex)) {
                deviceMeasurements.clear();
            }
            deviceMeasurements.add(measurement.getValue());
        }

        startDateTable.put(measurement.getDeviceId(), startDate);
    }

    private static int getHourFromTimestamp(Date timestamp) {
        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");

        // Format the timestamp to extract the hour
        String hourString = hourFormat.format(timestamp);

        // Convert the hour string to an integer
        return Integer.parseInt(hourString);
    }

    public ProcessedData computeHourlyConsumption(int deviceId, Date endDate, List<Double> measurementsCopy) {
        int size = measurementsCopy.size();
        double firstValue = measurementsCopy.get(0);
        double lastValue = measurementsCopy.get(size-1);
        double totalConsumption = lastValue - firstValue;
        Device device = deviceRepository.findById(deviceId);
        ProcessedData processedData = new ProcessedData(totalConsumption, device, endDate);
        insert(processedData);

        return processedData;
    }

    public void insert(ProcessedData processedData) {
        processedDataRepository.save(processedData);
    }

    private void sendNotification(int userId, int deviceId, String message) {
        // Construct the notification message
        Notification notification = new Notification(message, deviceId);

        // Broadcast the notification to the WebSocket topic
        messagingTemplate.convertAndSend("/topic/notification/" + userId, notification);
    }

    public List<MeasurementResponse> getMeasurements(MeasurementRequest measurementRequest) {
        List<ProcessedData> measurements = processedDataRepository.findByDevice(new Device(measurementRequest.getDeviceId()));
        List<ProcessedData> filteredData = measurements.stream()
                .filter(d -> DateUtils.truncateDate(d.getDate()).equals(DateUtils.truncateDate(measurementRequest.getDate()))).toList();

        return filteredData.stream().map(ProcessedDataBuilder::toResponse).collect(Collectors.toList());
    }
}
