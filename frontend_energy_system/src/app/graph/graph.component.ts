import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
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
    this.setChartDataSet();
  }

  public lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };
  public lineChartOptions: ChartOptions<'line'> = {
    responsive: true
  };
  public lineChartLegend = true;

  ngOnChanges(changes: SimpleChanges) {
    this.labels = changes['labels'].currentValue ?? [];
    this.data = changes['data'].currentValue ?? [];
    this.setChartDataSet();
  }

  private setChartDataSet(): void {
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
}
