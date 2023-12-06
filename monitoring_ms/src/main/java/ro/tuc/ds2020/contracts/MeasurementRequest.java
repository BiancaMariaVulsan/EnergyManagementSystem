package ro.tuc.ds2020.contracts;

import java.util.Date;

public class MeasurementRequest{
    private int deviceId;
    private Date date;

    public MeasurementRequest(int deviceId, Date date) {
        this.deviceId = deviceId;
        this.date = date;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date startDate) {
        this.date = startDate;
    }
}
