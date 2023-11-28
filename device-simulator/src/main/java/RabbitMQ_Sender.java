import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ_Sender {

    private static final long tenMin = 600000; // 10 min
    public static long crtOffset = 0;
    public static long timestamp;

    public static void send(double value, int id) throws IOException, TimeoutException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://torcoaxu:bphipUMqDPfi6qPCsFrX6zi8FZe6fVRV@hog.rmq5.cloudamqp.com/torcoaxu");

        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            // channel.queueDeclare("measurements-queue", false, false, false, null);

            // Create a JSON message
            Measurement measurement = new Measurement();
            measurement.setTimestamp(computeTime(id));
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

    public static long computeTime(int id) {
        crtOffset += tenMin;
        // Save the current date to a text file
        saveDateToFile(id + ".txt", timestamp + crtOffset);
        return timestamp + crtOffset;
    }

    private static void saveDateToFile(String fileName, long date) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
