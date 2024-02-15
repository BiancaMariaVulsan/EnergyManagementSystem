package ro.tuc.ds2020.rabbitMQ;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.services.MeasurementsService;
import ro.tuc.ds2020.services.ProcessedMeasurementsService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
@RabbitListener(queues = "measurements-queue")
public class Receiver {

    private final static String QUEUE_NAME = "measurements-queue";
    private static Channel channel;

    @Autowired
    MeasurementsService measurementsService;
    @Autowired
    ProcessedMeasurementsService processedMeasurementsService;

    @RabbitHandler
    public void receive(byte[] body) throws UnsupportedEncodingException {
        String jsonMessage = new String(body, "UTF-8");

        // Deserialize the JSON message to MeasurementDTO
        MeasurementDTO measurementDTO = deserializeJson(jsonMessage);

        measurementsService.insert(measurementDTO);
        processedMeasurementsService.addMeasurement(measurementDTO);

        // Now you can work with the MeasurementDTO object
        System.out.println(" [x] Received MeasurementDTO: " + measurementDTO);
        System.out.println("Timestamp: " + measurementDTO.getTimestamp());
        System.out.println("Value: " + measurementDTO.getValue());
        System.out.println("DeviceId: " + measurementDTO.getDeviceId());
    }

    public static void receive(ConnectionFactory factory, MeasurementsService measurementsService, ProcessedMeasurementsService processedMeasurementsService) throws Exception {
        try (Connection connection = factory.newConnection()) {
            channel = connection.createChannel();
            // channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicConsume(QUEUE_NAME, true, "consumerTag",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body) throws IOException {

                            String jsonMessage = new String(body, "UTF-8");

                            // Deserialize the JSON message to MeasurementDTO
                            MeasurementDTO measurementDTO = deserializeJson(jsonMessage);

                            measurementsService.insert(measurementDTO);
                            processedMeasurementsService.addMeasurement(measurementDTO);

                            // Now you can work with the MeasurementDTO object
                            System.out.println(" [x] Received MeasurementDTO: " + measurementDTO);
                            System.out.println("Timestamp: " + measurementDTO.getTimestamp());
                            System.out.println("Value: " + measurementDTO.getValue());
                            System.out.println("DeviceId: " + measurementDTO.getDeviceId());
                        }
                    });

            System.out.println(" [*] Waiting for messages from device simulator. To exit press CTRL+C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MeasurementDTO deserializeJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, MeasurementDTO.class);
    }

}
