import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AddProductService } from '../services/add-product.service';

export class Seller{
  productStock: number;
  productPrice: number;
  sellerId: string;
}

export class Product{
  productName: string;
}

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
   searchForm: FormGroup;
   updateForm: FormGroup;
   eMail;
   seller: Seller={
     productPrice:0,
     productStock:0,
     sellerId:''
   }
   product:Product={
     productName:'',
   }
   valueSearch=false;
   productDoesntExists=false;
   isUpdated=false;
   desc;
  constructor(private router:Router,private _formBuilder:FormBuilder, private route:ActivatedRoute,
              private addProduct: AddProductService) { } 

  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap)=> {
      this.eMail = params.get('eMail');});
      console.log(this.eMail);

      this.updateForm=this._formBuilder.group({
        price: ['', Validators.required],
        quantity: ['', Validators.required]
      });
  }

  doSearch(name){
    console.log(name);
    
   this.addProduct.searchProduct(name).subscribe(data=>{
     if(data){
       this.valueSearch=true;
       this.productDoesntExists=false;
       this.product.productName=data.productName;
       this.desc=data.productDescription;
     }},_error=>{
    this.productDoesntExists=true;
    this.valueSearch=false;
   })

  }

  doUpdate(){
    this.isUpdated=true;
    this.seller.productPrice=this.updateForm.value.price;
    this.seller.productStock=this.updateForm.value.quantity;
    this.seller.sellerId=this.eMail;
    console.log(this.product);
    this.addProduct.updateProduct(this.product,this.seller).subscribe();
  }
   goToAdd(){
     this.router.navigate(['./add-product',{eMail:this.eMail}]);
   }

}
