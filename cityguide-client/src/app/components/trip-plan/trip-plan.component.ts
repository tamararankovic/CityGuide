import { Component, OnInit } from '@angular/core';
import { MatListOption } from '@angular/material/list';
import { Router } from '@angular/router';
import { CityLocation } from 'src/app/model/city-location.model';
import { LocationType } from 'src/app/model/location-type.model';
import { TripPlanRequest } from 'src/app/model/trip-plan-request.model';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-trip-plan',
  templateUrl: './trip-plan.component.html',
  styleUrls: ['./trip-plan.component.css']
})
export class TripPlanComponent implements OnInit {

  types : LocationType[] = [];
  days : number = 1;
  showResults = false;
  locations : CityLocation[] = [];

  constructor(private locationService : LocationService, private router : Router) { }

  ngOnInit(): void {
    this.getTypes();
  }

  getTypes() {
    this.locationService.getTypes().subscribe(
      data => this.types = data
    )
  }

  planTrip(ids : MatListOption[]) {
    if(this.days <= 0) 
      return;
    const idValues : number[] = ids.map(id => id.value);
    const request = new TripPlanRequest(idValues, this.days);
    this.locationService.planTrip(request).subscribe(
      (data) => { this.showResults = true; this.locations = data.locations; }
    )
  }

  showDetails(id : string) {
    this.router.navigate(['./locations/details/' + id]);
  }
}
