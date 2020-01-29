import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from './services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})

export class AppComponent implements OnInit{
  
  title = "Shoppers-Zoid";
  public notLoggedIn:Boolean;
  
  constructor(private authService:AuthenticationService, private router:Router){}
  
  ngOnInit(): void {
    console.log(this.router.url);  
    console.log(this.authService.isUserLoggedIn());
  }

  isLoggedIn(){
    return !(this.authService.isUserLoggedIn());
  }

}
