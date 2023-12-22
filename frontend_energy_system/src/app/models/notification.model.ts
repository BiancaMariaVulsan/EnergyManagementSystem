export class NotificationMsg {
    public deviceId: number;
    public message: string;

    public constructor(message: string, deviceId: number) {
        this.message = message;
        this.deviceId = deviceId;
    }
}

export class ChatNotificationMsg {
    public toPersonId: number;
    public fromPersonId: number;
    public message: string;

    public constructor(message: string, toPersonId: number, fromPersonId: number) {
        this.message = message;
        this.toPersonId = toPersonId;
        this.fromPersonId = fromPersonId;
    }

}