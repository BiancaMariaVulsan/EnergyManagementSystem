import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ_Sender {

    public static void send(double value) throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");

        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            // channel.queueDeclare("measurements-queue", false, false, false, null);

            // Convert double to byte array
            byte[] valueBytes = Double.toString(value).getBytes();

            // Publish the byte array
            channel.basicPublish("", "measurements-queue", null, valueBytes);
            System.out.println(" [x] Sent value: " + value);
        }
    }
}
