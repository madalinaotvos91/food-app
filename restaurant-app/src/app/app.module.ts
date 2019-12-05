import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import { RestaurantTableComponent } from './restaurant-table/restaurant-table.component';
import { DeliveryDistributionStatisticsComponent } from './delivery-distribution-statistics/delivery-distribution-statistics.component';
import { DeliveryTimeStatisticsComponent } from './delivery-time-statistics/delivery-time-statistics.component';
import { CuisineMaxReviewStatisticsComponent } from './cuisine-max-review-statistics/cuisine-max-review-statistics.component';
import { RestaurantsWebsiteStatisticsComponent } from './restaurants-website-statistics/restaurants-website-statistics.component';

@NgModule({
  declarations: [
    AppComponent,
    RestaurantTableComponent,
    DeliveryDistributionStatisticsComponent,
    DeliveryTimeStatisticsComponent,
    CuisineMaxReviewStatisticsComponent,
    RestaurantsWebsiteStatisticsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
