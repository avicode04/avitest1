import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../services/payment.service';
import { PayCustomer } from './payCustomer';
import { StripeCustomer } from './stripeCustomer';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Charge } from './charge';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit { 

  private custData:StripeCustomer = new StripeCustomer();
  private customer:PayCustomer= new PayCustomer();
  private customerDetail:PayCustomer = new PayCustomer();
  private chargeObject:Charge = new Charge();
  // private order:Order = new Order();
  // private product:Product = new Product();
  private email:String;
  private name:String;
  private custStripeId:String;
  private amount:String;
  private cardId:String;
  private paymentStatus:String;
  private paymentSuccess:Boolean = false;
  private payment:Boolean = true;
  private balance_transaction:String;
  private receipt_url:String;
  public notOnPayment:Boolean=false;
  
   
  


  constructor(private orderService:OrderService,private service: PaymentService,private route:ActivatedRoute,private router:Router) {
   }

  ngOnInit() {
      this.notOnPayment=false;
      this.route.paramMap.subscribe((params:ParamMap)=>{
        this.email = params.get("email"); 
        this.name = params.get("name");
        this.amount = params.get("amount");
        console.log(this.email,this.name,this.amount);
      })
      this.service.addStripeCust(this.name,this.email).subscribe(data => {this.custData = data;console.log(this.custData);
      this.customer.cusEmail=this.custData.email;
      this.customer.cusId=this.custData.id;
      this.customer.cusName=this.custData.name;
      console.log(this.customer);
      this.service.addCustToDatabase(this.customer).subscribe(data =>{console.log(data)});
    });
    
  }

  addCard(number,exp_month,exp_year,cvc){
    this.service.getCustID(this.email).subscribe(data =>{
      this.customerDetail = data;
      this.custStripeId=this.customerDetail.cusId;
      this.service.addCardToStripe(this.custStripeId,number,exp_month,exp_year).subscribe(data=>{
        console.log(data);
        this.service.charge(this.custStripeId,this.amount).subscribe(data =>{
          this.chargeObject=data;
          this.paymentStatus=this.chargeObject.status;
          this.cardId=this.chargeObject.payment_method;
          this.balance_transaction=this.chargeObject.balance_transaction;
          this.receipt_url=this.chargeObject.receipt_url;
          console.log(this.paymentStatus,this.balance_transaction,this.receipt_url);
          console.log(this.chargeObject);                   
          console.log(this.custStripeId,this.cardId)      
          this.service.deleteCard(this.custStripeId,this.cardId).subscribe(data=>{
            console.log(data);
            if(this.paymentStatus=="succeeded"){
              this.paymentSuccess=!this.paymentSuccess;
              this.payment=!this.payment;
              
              }

          });
        });
      });    
    });    
  }

  goToProfile(){
    this.router.navigate(['/buyer-profile',{recUrl:this.receipt_url,balTran:this.balance_transaction,eMail:this.email}]);
  }

  goToIncart(){
    this.router.navigate(['']);
  }
  
}

