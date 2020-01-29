import { Component, OnInit } from '@angular/core';
import { SellerDashboardService } from '../services/seller-dashboard.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-seller-dashboard',
  templateUrl: './seller-dashboard.component.html',
  styleUrls: ['./seller-dashboard.component.css']
})

export class SellerDashboardComponent implements OnInit {

  // private submitted: boolean = false;
  // private editable: boolean = true;
  private init: boolean = false;

  private greeting: String = "";
  private eMail: String;

  private seller: any;

  private businessForm = new FormGroup({
    GSTIN: new FormControl(""),
    contact: new FormControl(""),
    // contact: new FormControl("", [Validators.min(1000000000), Validators.max(9999999999)]),
    sellerAddress: new FormControl(""),
  })

  constructor(private router: Router, 
    private route: ActivatedRoute, 
    private _sellerDashboardService: SellerDashboardService, 
    private _http: HttpClient) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap)=> 
      {
        this.eMail = params.get('emailId');
        console.log(this.eMail);
      }
    );
    this._sellerDashboardService.getFromDatabase(this.eMail).subscribe(response=>
      {
        this.seller = response;
      }
    );
  }

  reload()
  {
    window.location.reload();
  }

  // toggleEditable() {
  //   this.editable = !this.editable;
  //   this.greeting = "";
  // }


  updateData(){
    console.log("Seller Info before Updation: ");
    console.log(this.seller);
    console.log("Value obtained from the form: ");
    console.log(this.businessForm.value);

    this.seller.sellerGstIn = this.businessForm.value.GSTIN;
    this.seller.sellerPhone = this.businessForm.value.contact;
    this.seller.sellerAddress = this.businessForm.value.sellerAddress;
    this._sellerDashboardService.updateToDatabase(this.seller).subscribe();
    this.greeting = "Saved!";
    this.init = true;

    console.log("Seller Info after Updation: ");
    console.log(this.seller);
  }

  goToInventory(){
    this.router.navigate(['./seller-dashboard-inventory', { eMail:this.eMail }]);
  }

  // saveData(){
  //   if(this.submitted) {
  //     console.log(this.businessForm.value);
  //     console.log(this.id);
  //     this.businessForm.value.id = this.id;
  //     this._sellerDashboardService.updateToDatabase(this.businessForm.value).subscribe();
  //   } else {
  //     console.log(this.businessForm.value);
  //     this.submitted = true;
  //     this.editable = false;
  //     this._sellerDashboardService.saveToDatabase(this.businessForm.value).subscribe(
  //       this.id = this.findIdbyMail(this.businessForm.value.eMail)
  //     );
  //   }
  //   this.greeting = "Saved!";
  // }

  // findIdbyMail(email){
  //   console.log("email    " +email);
  //   var Id;
  //   this._sellerDashboardService.getFromDatabase().subscribe(
  //     response => {this.sellers = response.data;
  //       console.log(this.sellers);
  //     for(var seller of this.sellers){
  //     console.log("seller  " + seller);
  //       if(seller.eMail=email){
  //         Id = seller.id;
  //         alert(Id);
  //         break;
  //       }
  //     }
  //   });
  //   console.log("**********" + Id);
  //   return Id;
  // }

}
