import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(public authService: AuthService, public router: Router) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles : string[] = route.data.expectedRoles;    
    if(!expectedRoles.includes(this.authService.getRole()) && this.authService.isLoggedIn()) {
      this.router.navigate(['./locations']);
      return false;
    }
    if(!this.authService.isLoggedIn() && expectedRoles.length > 0) {
      this.router.navigate(['./']);
      return false;
    }
    return true;
  }
}
