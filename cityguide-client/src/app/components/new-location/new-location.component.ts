import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LocationType } from 'src/app/model/location-type.model';
import { NewLocation } from 'src/app/model/new-location.model';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-new-location',
  templateUrl: './new-location.component.html',
  styleUrls: ['./new-location.component.css']
})
export class NewLocationComponent implements OnInit {

  namee = '';
  address = '';
  description = '';

  types : LocationType[] = [];

  constructor(private locationService : LocationService, private router : Router, private snackBar : MatSnackBar) { }

  ngOnInit(): void {
    this.getTypes();
  }

  getTypes() {
    this.locationService.getTypes().subscribe(
      types => this.types = types,
      error => this.displayMessage(error.error.message)
    )
  }

  add(typeId : number) {
    const location = new NewLocation(typeId, this.namee, this.address, this.description);
    this.locationService.addLocation(location).subscribe(
      _ => this.router.navigate(['./locations']),
      error => this.displayMessage(error.error.message)
    )
  }

  displayMessage(message : string) {
    this.snackBar.open(message, "Okay", {duration : 5000});
  }
}
