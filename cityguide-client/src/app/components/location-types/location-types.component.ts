import { Component, OnInit } from '@angular/core';
import { LocationType } from 'src/app/model/location-type.model';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-location-types',
  templateUrl: './location-types.component.html',
  styleUrls: ['./location-types.component.css']
})
export class LocationTypesComponent implements OnInit {

  dataSource : LocationType[] = [];
  displayedColumns: string[] = ['name', 'features', 'delete'];

  constructor(private locationService : LocationService) { }

  ngOnInit(): void {
    this.getTypes();
  }

  getTypes() {
    this.locationService.getTypes().subscribe(
      data => this.dataSource = data
    )
  }

  delete(type : LocationType) {
    this.locationService.deleteType(type.id).subscribe(
      _ => this.getTypes() 
    )
  }
}
