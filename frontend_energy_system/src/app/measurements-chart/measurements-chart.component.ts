import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MeasurementService } from '../services/measurement.service';

@Component({
  selector: 'app-measurements-chart',
  templateUrl: './measurements-chart.component.html',
  styleUrls: ['./measurements-chart.component.css']
})
export class MeasurementsChartComponent implements OnInit {
  public labels: string[] = [];
  public measurements: number[] = [];
  public monitorDay: Date = new Date();
  public monitorDayFormatted: string = this.datePipe.transform(this.monitorDay, 'yyyy-MM-dd') ?? '';
  public deviceId: number = 0;

  constructor(private measurementService: MeasurementService, private toastr: ToastrService, private datePipe: DatePipe, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.deviceId = Number.parseInt(this.route.snapshot.paramMap.get('id') ?? '0');
      this.refreshChart();
    });
  }

  public onMonitorDayChange() {
    this.monitorDay = new Date(this.monitorDayFormatted);
    this.refreshChart();
  }

  private refreshChart(): void {
    this.measurementService.getHistoricalData(this.monitorDay, this.deviceId).subscribe(result => {
      this.labels = result.map(r => this.datePipe.transform(r.timestamp, 'yyyy-MM-dd HH:mm:ss'));
      this.measurements = result.map(r => r.value);
    }, error => {
      console.log(JSON.stringify(error));
      this.toastr.error("Could not fetch measurements, please try again later!");
    });
  }
}
