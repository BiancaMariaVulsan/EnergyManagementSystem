export class NotificationMsg {
    public deviceId: number;
    public message: string;

    public constructor(message: string, deviceId: number) {
        this.message = message;
        this.deviceId = deviceId;
    }
  }