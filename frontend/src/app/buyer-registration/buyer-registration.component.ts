import { Component, OnInit } from '@angular/core';
import { Buyer1 } from './buyer';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { BuyerRegistrationService } from '../services/buyer-registration.service';
import { AuthenticationService } from '../services/authentication.service';
import { SocialLoginService } from '../services/social-login.service';
import { AuthService, SocialUser } from 'angularx-social-login';
import { FacebookLoginProvider, GoogleLoginProvider } from 'angularx-social-login';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-buyer-registration',
  templateUrl: './buyer-registration.component.html',
  styleUrls: ['./buyer-registration.component.css']
})
export class BuyerRegistrationComponent implements OnInit {
  error: any = '';
  emailId;
  loginType;
  password = 'random';
  private responseObj;
  private user: SocialUser;
  private loggedIn: boolean;

  private buyer: Buyer1 = new Buyer1();
  private registrationForm = new FormGroup({
    Email: new FormControl("", [Validators.required, Validators.email]),
    Name: new FormControl("", Validators.required),
    Phone: new FormControl("", [Validators.required, Validators.min(1000000000), Validators.max(9999999999)]),
    Password: new FormControl("", [Validators.required, Validators.minLength(8), Validators.maxLength(16)]),
  })

  // tslint:disable-next-line: max-line-length
  constructor(private buyerService: BuyerRegistrationService, private socialLogin: SocialLoginService, private authService: AuthService, private authenticationService: AuthenticationService, private router: Router) { }


  ngOnInit() {
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.emailId = this.user.email;
      this.loginType = 'google';
      console.log(this.user);
      console.log(this.user);
      this.loggedIn = (user != null);
      this.router.navigate(['/buyer-dashboard', { emailId: this.emailId}]);
      this.buyer.buyerEmail = this.user.email;
      this.buyer.buyerName = this.user.name;
      this.buyerService.add(this.buyer).subscribe();
  });
}

signInWithFB(): void {
  this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
  this.authService.authState.subscribe((user) => {
    this.user = user;
    console.log(this.user);
    this.emailId = this.user.email;
    this.loginType = 'facebook';
    console.log(this.user);
    this.loggedIn = (user != null);
    this.authenticationService.login(this.emailId, this.password, this.loginType);
    this.router.navigate(['/buyer-dashboard', { emailId: this.emailId}]);
    this.buyer.buyerEmail = this.user.email;
    this.buyer.buyerName = this.user.name;
    this.buyerService.add(this.buyer).subscribe();
});
}

  addBuyer(email,name,phone,password) {
    // console.log(email1,name1,phone1,password1);
  //   this.buyer.buyerEmail = this.registrationForm.value.Email;
  //   this.buyer.buyerName = this.registrationForm.value.Name;
  //  this.buyer.buyerPhone = this.registrationForm.value.Phone;
  //   this.buyer.password = this.registrationForm.value.Password;
  this.buyer.buyerEmail = email;
  this.buyer.buyerName = name;
  this.buyer.buyerPhone = phone;
  this.buyer.password = password;
  console.log(this.buyer);

  this.buyerService.add(this.buyer).subscribe();
  }
}
