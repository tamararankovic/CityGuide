import { Component, OnInit } from '@angular/core';
import { MatListOption } from '@angular/material/list';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NewType } from 'src/app/model/new-type.model';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-new-location-type',
  templateUrl: './new-location-type.component.html',
  styleUrls: ['./new-location-type.component.css']
})
export class NewLocationTypeComponent implements OnInit {

  namee : string = '';
  features : string[] = [];

  constructor(private locationService : LocationService, private router : Router, private snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this.getFeatures();
  }

  getFeatures() {
    this.locationService.getFeatures().subscribe(
      data => this.features = data
    )
  }

  add(features : MatListOption[]) {
    if (this.namee.length == 0)
      return;
    const feat : string[] = features.map(f => f.value);
    const type : NewType = new NewType(this.namee, feat);
    this.locationService.addType(type).subscribe(
      _ => this.router.navigate(['./type']),
      error => this.displayMessage(error.error.message)
    )
  }

  displayMessage(message : string) {
    this.snackBar.open(message, "Okay", {duration : 5000});
  }
}
