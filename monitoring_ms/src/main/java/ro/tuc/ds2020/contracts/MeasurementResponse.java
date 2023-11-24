package ro.tuc.ds2020.contracts;

import javax.media.Time;
import java.util.Date;

public class MeasurementResponse {
    double value;
    private Time time;

    public MeasurementResponse(double value, Time time) {
        this.value = value;
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
