import { Component, OnInit } from '@angular/core';
import { BuyerDashboardServiceService } from '../services/buyer-dashboard-service.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-buyer-dashboard',
  templateUrl: './buyer-dashboard.component.html',
  styleUrls: ['./buyer-dashboard.component.css']
})
export class BuyerDashboardComponent implements OnInit {
  public recomm=<any>[];
  private eMail: string;

  constructor(private router: Router, private route:ActivatedRoute, private _recommendationService:BuyerDashboardServiceService) { }

  ngOnInit() {
    // this._recommendationService.getRecommendations().subscribe(data => 
    //   {
    //     this.recomm=data;
    //     console.log(this.recomm);
    //   }
    // );
    this.route.paramMap.subscribe((params:ParamMap)=> {
      this.eMail = params.get('emailId');

      console.log(this.eMail);

    });
  }

  gotToBuyerProfile() {
    this.router.navigate(['./buyer-profile', { eMail:this.eMail }]);
  }

}
