import { Component, OnInit } from '@angular/core';
import { Restaurant } from "./../model/restaurant.model";
import { RestaurantService } from "./../services/restaurant.service";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-restaurant-table',
  templateUrl: './restaurant-table.component.html',
  styleUrls: ['./restaurant-table.component.css']
})
export class RestaurantTableComponent implements OnInit {
restaurantList: Observable<Restaurant[]>;

constructor(private restaurantService: RestaurantService) { }

  ngOnInit() {
   this.restaurantList = this.restaurantService.getRestaurantList();
 }
}
