import { Component, OnInit } from '@angular/core';
import { RestaurantService } from "./../services/restaurant.service";
@Component({
  selector: 'app-restaurants-website-statistics',
  templateUrl: './restaurants-website-statistics.component.html',
  styleUrls: ['./restaurants-website-statistics.component.css']
})
export class RestaurantsWebsiteStatisticsComponent implements OnInit {
totalWebsitesNr:number = 0;
  constructor(private restaurantService: RestaurantService) { }

  ngOnInit() {
  this.restaurantService.getNrOfRestaurantsWithWebsites().
        subscribe(result=> {
                  this.totalWebsitesNr = result;
                  });
  }

}
