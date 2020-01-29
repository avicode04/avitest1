import { Component, OnInit, Directive } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})

export class LoginPageComponent implements OnInit {

  public emailId = '';
  public password = '';
  private invalidLogin = true;
  public decodedToken;
  public notRegistered=false;

  private loginForm = new FormGroup({
    Email: new FormControl("", [Validators.required, Validators.email]),
    Password: new FormControl("", [Validators.required, Validators.minLength(8), Validators.maxLength(16)]),
  })

  constructor(private router: Router, private loginservice: AuthenticationService) { }

  ngOnInit() { }

  checkLogin(){
    // console.log(this.loginForm.value);
    this.emailId = this.loginForm.value.Email;
    this.password = this.loginForm.value.Password;

    (this.loginservice.authenticate(this.emailId, this.password).subscribe(
           
      data => {
        console.log(data);        
        if(data){ 
          this.decodedToken = this.checkToken(JSON.stringify(data));
          localStorage.setItem('token',data.token);
          localStorage.setItem('emailId',this.decodedToken.sub)
          console.log(this.decodedToken);
          if(this.decodedToken.role === "buyer"){
            this.router.navigate(['./buyer-dashboard', {emailId:this.emailId}]);
          }
          if(this.decodedToken.role === "seller"){
            console.log(this.emailId);
            this.router.navigate(['./seller-dashboard', {emailId:this.emailId}]);
          }
        }
      },
      error=>{
        this.notRegistered=true;
      }
    ));
  }

  checkToken(tokenStr) {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(tokenStr);
    return decodedToken;
  }

}
