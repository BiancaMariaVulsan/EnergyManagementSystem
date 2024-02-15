export class Device {
    constructor() {
        this.id = 0;
     }

    public id: number;
    public description: string;
    public maxHourlyEnergConsumption: number;
    public personId?: number;
    public address: string;
}