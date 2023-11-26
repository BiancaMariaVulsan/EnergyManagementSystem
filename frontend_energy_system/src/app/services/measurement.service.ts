import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Measurement } from '../models/measurement.model';
import { MeasurementRequest } from '../models/measurement-request.model';

@Injectable()
export class DeviceService {

    constructor(private http: HttpClient) {}

    getHistoricalData(date: Date, deviceId: number): Observable<Measurement[]> {
        const measurementRequest = new MeasurementRequest(date, deviceId);
        return this.http.post<Measurement[]>(environment.deviceApiUrl + 'measurement', measurementRequest);
    }
}
