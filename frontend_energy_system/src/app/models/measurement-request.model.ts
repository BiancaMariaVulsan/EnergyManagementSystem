export class MeasurementRequest {
    public date: Date;
    public deviceId: number;

    public constructor(date: Date, deviceId: number) {
        this.date = date;
        this.deviceId = deviceId;
    }
  }
  