import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { AddProductService } from '../services/add-product.service';

@Component({
  selector: 'app-seller-edit-product',
  templateUrl: './seller-edit-product.component.html',
  styleUrls: ['./seller-edit-product.component.css']
})
export class SellerEditProductComponent implements OnInit {
  private eMail:string;
  private productName:string;
  private desc: string;

  constructor(private route:ActivatedRoute, private addProd:AddProductService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap)=> {
      this.eMail = params.get('eMail');
      this.productName=params.get('prName');
   });
   this.addProd.searchProduct(this.productName).subscribe(data=>{
     if(data){
       this.desc=data.productDescription;
     }
   });
 }
 

}
