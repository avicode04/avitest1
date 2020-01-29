import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { DealsService } from '../services/deals.service';
@Component({
  selector: 'app-best-deals',
  templateUrl: './best-deals.component.html',
  styleUrls: ['./best-deals.component.css']
})
export class BestDealsComponent implements OnInit {

  public products =  {};
  public searchProductName={};
  public isCheckedMore=true;
  public isData=true;
  constructor(private route: ActivatedRoute,private _dealsService : DealsService,private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap)=> {
      this.searchProductName = params.get('name');
    });
    this._dealsService.findProduct(this.searchProductName).subscribe(data => {
      if(data) {
        console.log("#########")
        this.isData=true;
        this.products = data
      }

      console.log("*****"+this.products);
    },
    _error=>{
      console.log(_error);
      this.isData=false;
    }
);
    // console.log(this.products);
  }
  toSearch(productName){
    this.router.navigate(['./search',{name:productName}]);
  }
  goToProduct(sellerId){
    this.router.navigate(['./product-details',{sellerId:sellerId,searchProductName:this.searchProductName.toString().toLowerCase()}]);
  }
  checkMore(){
    this.isCheckedMore=!(this.isCheckedMore);
  }

}
