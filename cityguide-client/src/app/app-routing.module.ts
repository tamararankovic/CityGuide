import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { LocationDetailsComponent } from './components/location-details/location-details.component';
import { LocationTypesComponent } from './components/location-types/location-types.component';
import { LocationsComponent } from './components/locations/locations.component';
import { LoginComponent } from './components/login/login.component';
import { NewLocationTypeComponent } from './components/new-location-type/new-location-type.component';
import { NewLocationComponent } from './components/new-location/new-location.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { TripPlanComponent } from './components/trip-plan/trip-plan.component';
import { RouteGuardService } from './guards/route-guard.service';

const routes: Routes = [
  {path: '', component: LandingPageComponent, canActivate: [RouteGuardService], data: { expectedRoles: []}},
  {path: 'login', component: LoginComponent,  canActivate: [RouteGuardService], data: { expectedRoles: []}},
  {path: 'register', component: RegistrationComponent,  canActivate: [RouteGuardService], data: { expectedRoles: []}},
  {path: 'locations/details/:id', component: LocationDetailsComponent, canActivate: [RouteGuardService], data: { expectedRoles: ['BASIC_USER', "ADMIN"]}},
  {path: 'locations/new', component: NewLocationComponent, canActivate: [RouteGuardService], data: { expectedRoles: ["ADMIN"]}},
  {path: 'locations/promoted', component: LocationsComponent, canActivate: [RouteGuardService], data: { expectedRoles: ["BASIC_USER", "ADMIN"]}},
  {path: 'locations', component: LocationsComponent, canActivate: [RouteGuardService], data: { expectedRoles: ["BASIC_USER", "ADMIN"]}},
  {path: 'trip', component: TripPlanComponent, canActivate: [RouteGuardService], data: { expectedRoles: ["BASIC_USER"]}},
  {path: 'type', component: LocationTypesComponent, canActivate: [RouteGuardService], data: { expectedRoles: ["ADMIN"]}},
  {path: 'type/new', component: NewLocationTypeComponent, canActivate: [RouteGuardService], data: { expectedRoles: ["ADMIN"]}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
