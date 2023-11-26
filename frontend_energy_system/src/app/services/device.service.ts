import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from '../models/device.model';
import { environment } from 'src/environments/environment';
import { Measurement } from '../models/measurement.model';

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

    getHistoricalData(date: Date, deviceId: number): Observable<Measurement[]> {
      // TODO: call the API
      let measurements = [];
      let crtValue = 20;

      for (let i = 0; i < 60; i++) {
        let measurement = new Measurement();
        measurement.timestamp = new Date(date.getFullYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes() + i);
        measurement.value = crtValue + (Math.random() * 5);
        measurements.push(measurement);
      }

      return new Observable(observer => {
        observer.next(measurements);
      })
    }
}
