package ro.tuc.ds2020.services;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.rabbitMQ.QueueConfig;
import ro.tuc.ds2020.rabbitMQ.Receiver;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class RabbitMqService implements ApplicationListener<ContextRefreshedEvent> {

    Receiver receiver = new Receiver();
    QueueConfig queueConfig = new QueueConfig();
    void init() throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        queueConfig.setFactory();
        readMessagesFromMonitoringQueue("measurements-queue");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                init();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void readMessagesFromMonitoringQueue(String queueName) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                try {
                    receiver.receive(queueConfig.getFactory());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(repeatedTask, 0, 100);
    }
}
