import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class RabbitMQ_Sender {

    public static void send(double value, int id) throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");

        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            // channel.queueDeclare("measurements-queue", false, false, false, null);

            // Create a JSON message
            Measurement measurement = new Measurement();
            measurement.setTimestamp(new Date());
            measurement.setDeviceId(id);
            measurement.setValue(value);

            // Convert Measurement object to JSON
            Gson gson = new Gson();
            String message = gson.toJson(measurement);

            // Publish the byte array
            channel.basicPublish("", "measurements-queue", null, message.getBytes());
            System.out.println(" [x] Sent value: " + value);
        } catch (Exception e) {
            System.out.println(" [x] Error sending value: " + value);
        }
    }
}
