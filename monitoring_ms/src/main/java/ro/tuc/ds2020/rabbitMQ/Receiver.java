package ro.tuc.ds2020.rabbitMQ;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class Receiver {

    private final static String QUEUE_NAME = "measurements-queue";
    private static Channel channel;

    public Receiver() {
        try{
            receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");

        try (Connection connection = factory.newConnection()) {
            channel = connection.createChannel();
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicConsume(QUEUE_NAME, true, "consumerTag",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body) throws IOException {
                            String message = new String(body, "UTF-8");
                            System.out.println(" [x] Received '" + message + "'");
                        }
                    });
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
