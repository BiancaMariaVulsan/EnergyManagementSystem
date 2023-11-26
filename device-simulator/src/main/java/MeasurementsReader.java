import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

public class MeasurementsReader {

    private static final String CSV_FILE_PATH = "./src/main/resources/sensor.csv";

    public static void main(String[] args) {
        MeasurementsReader csvReader = new MeasurementsReader();
        csvReader.readCsvValues(Integer.parseInt(args[0]));
    }

    public void readCsvValues(int deviceId) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ReadCsvTask(deviceId), 0, 1000); // Read one value per second

        // Stop after 10 seconds
         try {
             Thread.sleep(10000);
             timer.cancel();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
    }

    private class ReadCsvTask extends TimerTask {
        private Path csvFilePath = Paths.get(CSV_FILE_PATH);
        private int deviceId;
        public ReadCsvTask(int deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath.toFile()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Assuming the values are separated by commas (CSV)
                    String[] values = line.split(",");
                    // Assuming the double value is in the first column
                    double doubleValue = Double.parseDouble(values[0]);

                    // Do something with the double value, e.g., print it
                    System.out.println("Read value: " + doubleValue);

                    // Send the double value to the RabbitMQ queue
                    RabbitMQ_Sender.send(doubleValue, deviceId);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
