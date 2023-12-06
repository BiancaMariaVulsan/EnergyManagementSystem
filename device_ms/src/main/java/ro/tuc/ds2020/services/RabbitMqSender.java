package ro.tuc.ds2020.services;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ro.tuc.ds2020.entities.DeviceRabbit;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class RabbitMqSender {
    public static void send(DeviceRabbit message) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");

        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
//            channel.queueDeclare("devices-queue", false, false, false, null);

            // Convert Measurement object to JSON
            Gson gson = new Gson();
            String messageJson = gson.toJson(message);

            // Publish the byte array
            channel.basicPublish("", "devices-queue", null, messageJson.getBytes());
            System.out.println(" [x] Sent device: " + message.getDeviceId());
        } catch (Exception e) {
            System.out.println(" [x] Error sending device: " + message.getDeviceId());
        }
    }
}
