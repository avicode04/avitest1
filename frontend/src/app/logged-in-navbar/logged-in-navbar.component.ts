import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
// import { PaymentComponent } from '../payment/payment.component';


@Component({
  selector: 'app-logged-in-navbar',
  templateUrl: './logged-in-navbar.component.html',
  styleUrls: ['./logged-in-navbar.component.css']
})
export class LoggedInNavbarComponent implements OnInit {
 
  private productName: string;
  public notOnPayment:Boolean = true;
  
  constructor(private authService:AuthenticationService, private router:Router) { }

  ngOnInit() {
  }

  toSearch(event){
    this.productName = event.target.value;
    this.router.navigate(['./search',{name:this.productName}]);
  }

  logOut(){
    this.authService.logOut();
    this.router.navigate([''])
  }
}
