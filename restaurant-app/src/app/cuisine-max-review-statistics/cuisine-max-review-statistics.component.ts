import { Component, OnInit } from '@angular/core';
import { RestaurantService } from "./../services/restaurant.service";
@Component({
  selector: 'app-cuisine-max-review-statistics',
  templateUrl: './cuisine-max-review-statistics.component.html',
  styleUrls: ['./cuisine-max-review-statistics.component.css']
})
export class CuisineMaxReviewStatisticsComponent implements OnInit {
cuisineMaxReviewed:string;
cuisineReviewNr:number;
  constructor(private restaurantService: RestaurantService) { }

  ngOnInit() {
   this.restaurantService.getMaxReviewedCusine().
        subscribe(result=> {
                  this.cuisineMaxReviewed = result.cuisine;
                  this.cuisineReviewNr = result.reviews
                  });
  }
}
