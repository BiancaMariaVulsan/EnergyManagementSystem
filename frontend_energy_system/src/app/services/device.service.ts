import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from '../models/device.model';
import { environment } from 'src/environments/environment';

@Injectable ()
export class DeviceService {

    private devices: Device[]

    constructor(private http: HttpClient) {this.http.get<Device[]>(environment.deviceApiUrl + 'device').subscribe(p => this.devices = p);}

    createDevice(newDevice: Device): Observable<boolean> {
        return this.http.post<boolean>(environment.deviceApiUrl + 'device', newDevice);
    }

    getDevices() {
        return this.http.get<Device[]>(environment.deviceApiUrl + 'device');
    }

    getDevicesByUser(person_id: any) {
        return this.http.get<Device[]>(environment.deviceApiUrl + 'device/' + person_id);
    }

    findDevice(id: number): Device {
        // Suppose a product with this id exists
        return this.devices.filter(p => p.id === id).at(0)
    }

    updateDevice(newProduct: Device): Observable<boolean> {
        return this.http.put<boolean>(environment.deviceApiUrl + 'device/' + newProduct.id, newProduct)
    }

    deleteDevice(id: number): Observable<boolean> {
        return this.http.delete<boolean>(environment.deviceApiUrl + 'device/' + id.toString())
    }
}