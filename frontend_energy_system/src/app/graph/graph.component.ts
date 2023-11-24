import { Component, Input, OnInit } from '@angular/core';
import { ChartConfiguration, ChartOptions } from 'chart.js';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  constructor() { }

  @Input() public labels: string[] = [];
  @Input() public data: number[] = [];
  @Input() public title: string = "";

  ngOnInit() {
    this.lineChartData = {
      labels: this.labels,
      datasets: [
        {
          data: this.data,
          label: this.title,
          fill: true,
          tension: 0.5,
          borderColor: 'black',
          backgroundColor: 'rgba(115,186,155,0.75)'
        }
      ]
    }
  }

  public lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };
  public lineChartOptions: ChartOptions<'line'> = {
    responsive: true
  };
  public lineChartLegend = true;

}
