import { Component, OnInit } from '@angular/core';
import { SellerRegistrationService } from '../services/seller-registration.service';
import { Seller1 } from './seller';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SocialLoginService } from '../services/social-login.service';
import { AuthenticationService } from '../services/authentication.service';
import { AuthService, SocialUser } from 'angularx-social-login';
import { FacebookLoginProvider, GoogleLoginProvider } from 'angularx-social-login';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-seller-registration',
  templateUrl: './seller-registration.component.html',
  styleUrls: ['./seller-registration.component.css']
})

export class SellerRegistrationComponent implements OnInit {

  error: any = '';
  seller: Seller1 = new Seller1();
  emailId;
  loginType;
  password = 'random';
  private responseObj;
  private user: SocialUser;
  private loggedIn: boolean;

  private registrationForm = new FormGroup({
    Email: new FormControl("", [Validators.required, Validators.email]),
    Name: new FormControl("", Validators.required),
    Phone: new FormControl("", [Validators.required, Validators.min(1000000000), Validators.max(9999999999)]),
    Password: new FormControl("", [Validators.required, Validators.minLength(8), Validators.maxLength(16)]),
  })

  // tslint:disable-next-line: max-line-length
  constructor(private sellerService : SellerRegistrationService, private socialLogin: SocialLoginService, private authService: AuthService, private authenticationService: AuthenticationService, private router:Router) { }

  ngOnInit() { }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.emailId = this.user.email;
      this.loginType = 'google';
      console.log(this.user);
      console.log(this.user);
      this.loggedIn = (user != null);
      this.router.navigate(['/seller-dashboard', { emailId: this.emailId}]);
      this.seller.sellerEmail = this.user.email;
      this.seller.sellerName = this.user.name;
      this.sellerService.add(this.seller).subscribe();
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
    this.seller.sellerEmail = this.user.email;
    this.seller.sellerName = this.user.name;
    console.log(this.seller);
    this.sellerService.add(this.seller).subscribe();
    this.router.navigate(['/seller-dashboard', { emailId: this.emailId}]);
});
}

  addSeller() {
    // console.log(email1,name1,phone1,password1);
    this.seller.sellerEmail = this.registrationForm.value.Email;
    this.seller.sellerName = this.registrationForm.value.Name;
    this.seller.sellerPhone = this.registrationForm.value.Phone;
    this.seller.password = this.registrationForm.value.Password;
    console.log(this.seller);

    this.sellerService.add(this.seller).subscribe();
  }

}
