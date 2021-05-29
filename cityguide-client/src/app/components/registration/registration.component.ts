import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NewUser } from 'src/app/model/new-user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  namee : string = '';
  surname : string = '';
  email : string = '';
  password : string = '';

  constructor(private authService : AuthService, private router : Router, private snackBar : MatSnackBar) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.register();
  }

  register() {
    var user : NewUser = new NewUser(this.namee, this.surname, this.email, this.password);
    this.authService.register(user).subscribe(
      _ => { this.displayMessage('Registration successful!'); this.router.navigate(['./']) },
      (error) => this.displayMessage(error.error.message)
    )
  }

  displayMessage(message : string) {
    this.snackBar.open(message, "Okay", {duration : 5000});
  }
}
