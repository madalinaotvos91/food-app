import { Component, OnInit } from '@angular/core';
import { RestaurantService } from "./../services/restaurant.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-delivery-time-statistics',
  templateUrl: './delivery-time-statistics.component.html',
  styleUrls: ['./delivery-time-statistics.component.css']
})
export class DeliveryTimeStatisticsComponent implements OnInit {
minDeliveryTime:number=0;
avgDeliveryTime:number=0;
maxDeliveryTime:number=0;

  constructor(private restaurantService: RestaurantService) { }

  ngOnInit() {
    this.restaurantService.getDeliveryTimes().
                    subscribe(response => {
                    this.minDeliveryTime = response.minDeliveryTime;
                    this.avgDeliveryTime = response.avgDeliveryTime;
                    this.maxDeliveryTime = response.maxDeliveryTime;
                    });

  }

}
