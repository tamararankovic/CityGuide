import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserCredentials } from 'src/app/model/user-credentials';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //pattern = ".+@.+

  email : string = '';
  password : string = '';

  constructor(private authService : AuthService, private snackBar : MatSnackBar, private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.logIn();
  }

  logIn() {
    var user : UserCredentials = new UserCredentials(this.email, this.password);
    this.authService.logIn(user).subscribe(
      (token) => { this.authService.setLoggedIn(token); this.router.navigate(['./locations']) },
      (error) => this.displayMessage(error.error.message)
    )
  }

  displayMessage(message : string) {
    this.snackBar.open(message, "Okay", {duration : 5000});
  }
}
