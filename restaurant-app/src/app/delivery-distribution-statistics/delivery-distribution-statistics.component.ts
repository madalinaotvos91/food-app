import { Component, OnInit } from '@angular/core';
import { RestaurantService } from "./../services/restaurant.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-delivery-distribution-statistics',
  templateUrl: './delivery-distribution-statistics.component.html',
  styleUrls: ['./delivery-distribution-statistics.component.css']
})
export class DeliveryDistributionStatisticsComponent implements OnInit {
zipCodes : Observable<string[]>;
deliveryDistribution:string[];
totalNumberOfZipCodes: number = 0;
deliveryCountByZipCode:number = 0;
  constructor(private restaurantService: RestaurantService) { }

  ngOnInit() {
    this.restaurantService.getZipCodeList().subscribe(response => this.zipCodes = response);
    this.restaurantService.getNrOfRestaurantsDelivering().subscribe(response => this.totalNumberOfZipCodes = response);
  }

 getDistribution(zipCode){
 this.restaurantService.getDeliveryDistribution(zipCode).subscribe(response => this.deliveryDistribution = response);
 this.restaurantService.getCountRestaurantsDelivering(zipCode).subscribe(response => this.deliveryCountByZipCode = response);
}


}
