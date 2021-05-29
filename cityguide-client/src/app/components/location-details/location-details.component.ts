import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CityLocation } from 'src/app/model/city-location.model';
import { PageVisit } from 'src/app/model/page-visit.model';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-location-details',
  templateUrl: './location-details.component.html',
  styleUrls: ['./location-details.component.css']
})
export class LocationDetailsComponent implements OnInit, OnDestroy {

  minutes : number = 0;
  location : CityLocation = new CityLocation(0, '', '', '', '', 0, 0, 0, false, false);
  date : Date;

  constructor(private locationService : LocationService, private router : Router, private route : ActivatedRoute, private snackBar : MatSnackBar) { }

  ngOnInit(): void {
    var id : string = this.route.snapshot.paramMap.get('id');
    if (Number(id) != NaN) {
      this.get(Number(id));
      this.date = new Date();
    } else {
      this.router.navigate(['./locations']);
    }
  }

  get(id : number) {
    this.locationService.get(id).subscribe(
      data => this.location = data,
      error => this.displayMessage(error.error.message)
    )
  }

  sendTimeEstimation() {
    if(this.minutes < 0)
      return;
    const id : string = this.route.snapshot.paramMap.get('id')
    this.locationService.sendTimeEstimation(this.minutes, id).subscribe(
      _ => this.displayMessage('Time sent!')
    )
  }

  ngOnDestroy(): void {
    if(this.date != undefined && this.date != null) {
      const visit = new PageVisit(Number(this.route.snapshot.paramMap.get('id')), this.date, new Date().getTime() - this.date.getTime());
      this.locationService.sendVisit(visit).subscribe();
    }
  }

  displayMessage(message : string) {
    this.snackBar.open(message, "Okay", {duration : 5000});
  }
}
