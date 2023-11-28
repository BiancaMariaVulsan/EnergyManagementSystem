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
import java.io.*;

public class MeasurementsReader {

    private static final String CSV_FILE_PATH = "./src/main/resources/sensor.csv";

    public static void main(String[] args) {
        // Load the date from the text file
        RabbitMQ_Sender.timestamp = loadDateFromFile(args[0] + ".txt");

        MeasurementsReader csvReader = new MeasurementsReader();
        csvReader.readCsvValues(Integer.parseInt(args[0]));
    }

    private static long loadDateFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the timestamp from the file
            String timestampString = reader.readLine();

            // Parse the timestamp to create a Date object
            return Long.parseLong(timestampString);
        } catch (IOException | NumberFormatException e) {
            return System.currentTimeMillis();
        }
    }

    public void readCsvValues(int deviceId) {
        Timer timer = new Timer();
        timer.schedule(new ReadCsvTask(deviceId), 0, 10000); // Read one value per 10 second
    }

    private static class ReadCsvTask extends TimerTask {
        private final Path csvFilePath = Paths.get(CSV_FILE_PATH);
        private final int deviceId;
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

                    Thread.sleep(10000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException |
                     InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
