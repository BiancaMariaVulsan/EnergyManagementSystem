import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isAdmin: boolean = localStorage.getItem("eshop-usertype") === 'Admin';

  constructor() { }
  slideConfig = {"slidesToShow": 1, "slidesToScroll": 1};

  ngOnInit(): void {
  }

}
