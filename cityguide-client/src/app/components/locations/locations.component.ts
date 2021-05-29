import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Rating } from 'src/app/model/rating.model';
import { CityLocation } from 'src/app/model/city-location.model';
import { AuthService } from 'src/app/services/auth.service';
import { LocationService } from 'src/app/services/location.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {

  public locations : CityLocation[] = [];
  public title : string = '';
  constructor(private locationService : LocationService, private authService : AuthService, private snackBar : MatSnackBar, private router : Router) { }

  ngOnInit(): void {
    if(this.router.url.endsWith('promoted')) {
      this.title = 'Promoted locations';
      this.getPromotedLocations();
    } else {
      this.title = 'Locations';
      this.getLocations();
    }
  }

  getLocations() {
    this.locationService.getAll().subscribe(
      (data) => this.locations = data,
      (error) => this.displayMessage(error.error.message)
    )
  }

  getPromotedLocations() {
    this.locationService.getPromoted().subscribe(
      (data) => this.locations = data,
      (error) => this.displayMessage(error.error.message)
    )
  }

  like(location : CityLocation) {
    const rating : Rating = new Rating(location.id, true);
    this.locationService.postRating(rating).subscribe(
      _ => { if(location.rated) {location.dislikes -= 1; } location.rated = true; location.liked = true; location.likes += 1; },
      (error) => this.displayMessage(error.error.message)
    )
  }

  dislike(location : CityLocation) {
    const rating : Rating = new Rating(location.id, false);
    this.locationService.postRating(rating).subscribe(
      _ => { if(location.rated) {location.likes -= 1; } location.rated = true; location.liked = false; location.dislikes += 1; },
      (error) => this.displayMessage(error.error.message)
    )
  }

  delete(location : CityLocation) {
    this.locationService.delete(location.id).subscribe(
      _ => { this.locations = this.locations.filter(l => l.id != location.id); },
      (error) => this.displayMessage(error.error.message)
    )
  }

  showDetails(id : string) {
    this.router.navigate(['./locations/details/' + id]);
  }

  displayMessage(message : string) {
    this.snackBar.open(message, "Okay", {duration : 5000});
  }
}
