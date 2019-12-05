import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { RequestOptions } from '@angular/http';
import { Restaurant } from "./../model/restaurant.model";
@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

//URL for retrieving the certificate list
private baseUrl = 'http://localhost:8080/api';
private region = 'https://www.thuisbezorgd.nl/en/order-takeaway-amsterdam-stadsdeel-binnenstad-1011';
  constructor(private http: HttpClient) { }
  getRestaurantList() : Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   return this.http.get(`${this.baseUrl.concat('/restaurant-list')}`,{params: params});
 }
   getZipCodeList() : Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   return this.http.get(`${this.baseUrl.concat('/zip-code-list')}`,{params: params});
 }

 getDeliveryDistribution(zipCode):Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   params = params.append("zipCode",zipCode);
   return this.http.get(`${this.baseUrl.concat('/delivery-cost-distribution')}`,{params: params});

 }
  getDeliveryTimes():Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   return this.http.get(`${this.baseUrl.concat('/delivery-statistics')}`,{params: params});
 }

 getMaxReviewedCusine():Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   return this.http.get(`${this.baseUrl.concat('/cuisine-max-review')}`,{params: params});
 }

  getNrOfRestaurantsWithWebsites():Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   return this.http.get(`${this.baseUrl.concat('/restaurants-with-website')}`,{params: params});
 }

  getNrOfRestaurantsDelivering():Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   return this.http.get(`${this.baseUrl.concat('/total-restaurants-delivering')}`,{params: params});
 }

   getCountRestaurantsDelivering(zipCode):Observable<any>{
   let params = new HttpParams();
   params = params.append("region",this.region);
   params = params.append("zipCode",zipCode);
   return this.http.get(`${this.baseUrl.concat('/total-delivery-areas-by-zipCode')}`,{params: params});
 }

}
