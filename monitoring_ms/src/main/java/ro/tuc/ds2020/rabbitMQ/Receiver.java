package ro.tuc.ds2020.rabbitMQ;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.MeasurementDTO;
import ro.tuc.ds2020.services.MeasurementsService;

import java.io.IOException;

@Service
public class Receiver {

    private final static String QUEUE_NAME = "measurements-queue";
    private static Channel channel;

    public static void receive(ConnectionFactory factory, MeasurementsService measurementsService) throws Exception {
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

                            // Now you can work with the MeasurementDTO object
                            System.out.println(" [x] Received MeasurementDTO: " + measurementDTO);
                            System.out.println("Timestamp: " + measurementDTO.getTimestamp());
                            System.out.println("Value: " + measurementDTO.getValue());
                            System.out.println("DeviceId: " + measurementDTO.getDeviceId());
                        }
                    });

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MeasurementDTO deserializeJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, MeasurementDTO.class);
    }

}
