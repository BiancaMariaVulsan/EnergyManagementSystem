import { Component, OnInit } from '@angular/core';
import { DeviceService } from '../services/device.service';
import { ToastrService } from 'ngx-toastr';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-measurements-chart',
  templateUrl: './measurements-chart.component.html',
  styleUrls: ['./measurements-chart.component.css']
})
export class MeasurementsChartComponent implements OnInit {
  public labels: string[] = [];
  public measurements: number[] = [];

  constructor(private deviceService: DeviceService, private toastr: ToastrService, private datePipe: DatePipe) { }

  ngOnInit(): void {
    const crtDate = new Date();
    this.deviceService.getHistoricalData(crtDate, 1).subscribe(result => {
      this.labels = result.map(r => this.datePipe.transform(r.timestamp, 'yyyy-MM-dd HH:mm:ss'));
      this.measurements = result.map(r => r.value);
    }, error => {
      console.log(JSON.stringify(error));
      this.toastr.error("Could not fetch measurements, please try again later!");
    });
  }

}
