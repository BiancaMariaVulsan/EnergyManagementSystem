package ro.tuc.ds2020.contracts;

import java.util.Date;

public class MeasurementResponse {
    double value;
    private Date timestamp;

    public MeasurementResponse(double value, Date time) {
        this.value = value;
        this.timestamp = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
