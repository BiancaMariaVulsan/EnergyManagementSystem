import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ_Sender {

    public static void main(String[] args) throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");

        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
//            channel.queueDeclare("measurements-queue", false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", "measurements-queue", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
