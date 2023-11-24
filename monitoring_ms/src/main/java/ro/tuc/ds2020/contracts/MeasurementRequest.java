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

    public Date getStartDate() {
        return date;
    }

    public void setStartDate(Date startDate) {
        this.date = startDate;
    }
}
