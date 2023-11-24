package ro.tuc.ds2020.rabbitMQ;

import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class QueueConfig {

    private ConnectionFactory factory = new ConnectionFactory();

    public void setFactory() throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");
    }

    public ConnectionFactory getFactory() {
        return factory;
    }
}
