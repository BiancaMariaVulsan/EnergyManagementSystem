package ro.tuc.ds2020.rabbitMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.rabbitMQ.QueueConfig;
import ro.tuc.ds2020.rabbitMQ.Receiver;
import ro.tuc.ds2020.services.MeasurementsService;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class RabbitMqConfig implements ApplicationListener<ContextRefreshedEvent> {

    Receiver receiver = new Receiver();
    QueueConfig queueConfig = new QueueConfig();

    MeasurementsService measurementsService;

    @Autowired
    public RabbitMqConfig(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

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
                    receiver.receive(queueConfig.getFactory(), measurementsService);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(repeatedTask, 0, 100);
    }
}
