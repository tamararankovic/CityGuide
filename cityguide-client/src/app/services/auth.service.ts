import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserCredentials } from '../model/user-credentials';
import decode from 'jwt-decode';
import { NewUser } from '../model/new-user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http : HttpClient) { }

  register(user : NewUser) : Observable<any> {
    return this.http.post(environment.baseUrl + 'auth/register', user);
  }

  logIn(user : UserCredentials) : Observable<any> {
    return this.http.post(environment.baseUrl + 'auth/logIn', user, {responseType : 'text'});
  }

  logOut() : boolean {
    if(!this.isLoggedIn()) return false;
    localStorage.removeItem('token');
    return true;
  }

  setLoggedIn(token : string) {
    localStorage.setItem('token', token);
  }

  isLoggedIn() : boolean {
    return localStorage.getItem('token') != null;
  }

  getRole() {
    if (!this.isLoggedIn()) return '';
    const token = localStorage.getItem('token');
    const tokenPayload = decode(token);
    return tokenPayload['role'];
  }
}
