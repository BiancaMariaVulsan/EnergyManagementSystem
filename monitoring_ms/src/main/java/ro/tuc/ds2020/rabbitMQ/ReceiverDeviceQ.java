package ro.tuc.ds2020.rabbitMQ;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.services.DeviceService;

import java.io.IOException;

@Service
public class ReceiverDeviceQ {
    private final static String QUEUE_NAME = "devices-queue";
    private static Channel channel;

    public static void receive(ConnectionFactory factory, DeviceService deviceService) throws Exception {
        try (Connection connection = factory.newConnection()) {
            channel = connection.createChannel();
//             channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicConsume(QUEUE_NAME, true, "consumerTag",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body) throws IOException {

                            String jsonMessage = new String(body, "UTF-8");

                            // Deserialize the JSON message to MeasurementDTO
                            DeviceDTO deviceDTO = deserializeJson(jsonMessage);

                            if(deviceDTO.getOperation().equals("insert")) {
                                deviceService.insert(deviceDTO);
                            }
                            else if(deviceDTO.getOperation().equals("update")) {
                                deviceService.update(deviceDTO);
                            }
                            else if(deviceDTO.getOperation().equals("delete")) {
                                deviceService.delete(deviceDTO.getDeviceId());
                            }

                            // Now you can work with the MeasurementDTO object
                            System.out.println(" [x] Received MessageDTO: " + deviceDTO);
                        }
                    });

            System.out.println(" [*] Waiting for messages from deveice ms. To exit press CTRL+C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DeviceDTO deserializeJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, DeviceDTO.class);
    }
}
