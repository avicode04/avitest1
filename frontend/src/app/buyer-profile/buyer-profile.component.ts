import { Component, OnInit } from '@angular/core';
import { BuyerProfileService } from '../services/buyer-profile.service';
import { Buyer } from './buyer';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-buyer-profile',
  templateUrl: './buyer-profile.component.html',
  styleUrls: ['./buyer-profile.component.css']
})
export class BuyerProfileComponent implements OnInit {


  private buyer :Buyer;
  private emailId:String;
  private editableProfile: boolean;
  private editableAddress: boolean;
  private editableOrder: boolean;
  private editablePayment: boolean;
  private editableSave: boolean;
  private editableEdit: boolean;
  private editable: boolean = false;
  private edithomeaddress : boolean = false;
  private editofficeaddress : boolean = false;
  private addressHomeSave:boolean;
  private addressHomeEdit:boolean;
  private addressOfficeSave:boolean;
  private addressOfficeEdit:boolean;
  private dateofbirth:boolean;
  private dateofbirth1:boolean;
  private order:any;


  constructor(private orderService:OrderService, 
    private buyerService: BuyerProfileService,
    private router:Router,
    private route:ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap)=>{
      this.emailId = params.get('eMail');
      console.log(this.emailId);
    });

    this.buyerService.getBuyer(this.emailId).subscribe(data => {this.buyer=data;console.log(this.buyer)});
    this.editableProfile=true;
    this.editableSave = false;
    this.editableEdit = true;
    this.addressHomeEdit = true;
    this.addressHomeSave = false;
    this.addressOfficeEdit = true;
    this.addressOfficeSave = false;
    this.dateofbirth = true;
    this.dateofbirth1 = false;

        
  }

  toggleEditableProfile(){
    this.editableProfile = true;
    this.editableAddress = false;
    this.editableOrder = false;
    this.editablePayment = false;
  }
  toggleEditableAddress(){
    this.editableProfile = false;
    this.editableAddress = true;
    this.editableOrder = false;
    this.editablePayment = false;
  }
  toggleEditableOrders(){
    this.editableProfile = false;
    this.editableAddress = false;
    this.editableOrder = true;
    this.editablePayment = false;
    this.orderService.fetchAllOrdersOnBuyerProfile(this.emailId).subscribe(data=>{
        this.order = data;
        console.log(this.order);
    });
  }
  toggleEditablePayment(){
    this.editableProfile = false;
    this.editableAddress = false;
    this.editableOrder = false;
    this.editablePayment = true;
  }

  saveProfile(gender,dob){
   this.buyer.buyerGender = gender;
   this.buyer.buyerDob = dob;
   console.log(dob);
   this.buyerService.putBuyer(this.buyer).subscribe();
   window.location.reload();
    

  }

  editProfile(){
    this.editable=!this.editable;
    this.editableSave=!this.editableSave;
    this.editableEdit=!this.editableEdit;
    this.dateofbirth=!this.dateofbirth;
    this.dateofbirth1=!this.dateofbirth1;
  }

  editHomeAddress(){
    this.edithomeaddress=!this.edithomeaddress;
    this.addressHomeEdit=!this.addressHomeEdit;
    this.addressHomeSave=!this.addressHomeSave;
  }

  saveHomeAddress(address){
    this.buyer.buyerHomeAddress=address;
    this.buyerService.putBuyer(this.buyer).subscribe();
    window.location.reload();
  }

  editOfficeAddress(){
    this.editofficeaddress=!this.editofficeaddress;
    this.addressOfficeEdit=!this.addressOfficeEdit;
    this.addressOfficeSave=!this.addressOfficeSave;
  }

  saveOfficeAddress(address){
    this.buyer.buyerOfficeAddress=address;
    this.buyerService.putBuyer(this.buyer).subscribe();
    window.location.reload();
  }

}
