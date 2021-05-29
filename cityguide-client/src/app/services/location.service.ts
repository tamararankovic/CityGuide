import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from 'process';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CityLocation } from '../model/city-location.model';
import { LocationType } from '../model/location-type.model';
import { NewLocation } from '../model/new-location.model';
import { NewType } from '../model/new-type.model';
import { PageVisit } from '../model/page-visit.model';
import { Rating } from '../model/rating.model';
import { TripPlanRequest } from '../model/trip-plan-request.model';
import { TripPlan } from '../model/trip-plan.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http : HttpClient, private authService : AuthService) { }

  getAll() : Observable<CityLocation[]> {
    const role = this.authService.getRole();
    if(role == 'BASIC_USER') {
      return this.http.get<CityLocation[]>(environment.baseUrl + 'location/user');
    } else {
      return this.http.get<CityLocation[]>(environment.baseUrl + 'location/admin');
    }
  }

  getPromoted() : Observable<CityLocation[]> {
    const role = this.authService.getRole();
    if(role == 'BASIC_USER') {
      return this.http.get<CityLocation[]>(environment.baseUrl + 'location/promoted/user');
    } else {
      return this.http.get<CityLocation[]>(environment.baseUrl + 'location/promoted/admin');
    }
  }

  get(locationId : number) : Observable<CityLocation> {
    const role = this.authService.getRole();
    if(role == 'BASIC_USER') {
      return this.http.get<CityLocation>(environment.baseUrl + 'location/user/' + locationId);
    } else {
      return this.http.get<CityLocation>(environment.baseUrl + 'location/admin/' + locationId);
    }
  }

  delete(locationId : number) : Observable<any> {
    return this.http.delete(environment.baseUrl + 'location/' + locationId);
  }

  postRating(rating : Rating) : Observable<any> {
    return this.http.post<Rating>(environment.baseUrl + 'rating', rating);
  }

  getTypes() : Observable<LocationType[]> {
    return this.http.get<LocationType[]>(environment.baseUrl + 'type');
  }

  planTrip(request : TripPlanRequest) : Observable<TripPlan> {
    return this.http.post<TripPlan>(environment.baseUrl + 'trip', request);
  }

  deleteType(id : number) : Observable<any> {
    return this.http.delete(environment.baseUrl + 'type/' + id);
  }

  getFeatures() : Observable<string[]> {
    return this.http.get<string[]>(environment.baseUrl + 'type/features');
  }

  addType(type : NewType) : Observable<any> {
    return this.http.post(environment.baseUrl + 'type', type);
  }

  addLocation(location : NewLocation) : Observable<any> {
    return this.http.post(environment.baseUrl + 'location', location);
  }

  sendTimeEstimation(time : number, id : string) : Observable<any> {
    return this.http.post(environment.baseUrl + 'location/time/' + id + '/' + time, null);
  }

  sendVisit(visit : PageVisit) {
    return this.http.post(environment.baseUrl + 'location/visited', visit);
  }
 }
