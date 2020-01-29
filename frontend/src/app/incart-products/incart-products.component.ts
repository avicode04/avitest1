import { Component, OnInit } from '@angular/core';
import { ProductDetailsService } from '../services/product-details.service';
import { IncartProducts } from './incartProducts';
import { summaryFileName } from '@angular/compiler/src/aot/util';
import { ActivatedRoute, Router} from '@angular/router';

export class Product {
  productName: string;
  productDescription: string;
  productPrice: any; //what type ?
  productImage: string;
  sellerEmail: string;
  productQty: number;
}

export class Order {
  rating: any; //what type ??
  products: Product[];
  buyerPhone: any;
  buyerHomeAddress: string;
  buyerOfficeAddress: string;
  status: string;
}

@Component({
  selector: 'app-incart-products',
  templateUrl: './incart-products.component.html',
  styleUrls: ['./incart-products.component.css']
})
export class IncartProductsComponent implements OnInit {

  private order:Order = new Order();
  private product:Product = new Product();
  // incartProductArray = [];
  private incartProductArray:IncartProducts[];
  productQty = 1;
  productTotal = 0;
  totalAmount = 0;
  
  private i = 1;
  private total= [] ;
  private sum :Number=0;

  private email: string;
  private name: string="user";

  constructor(private router: ActivatedRoute, 
    private _productDetailsService: ProductDetailsService,
    private route: Router) { }

  ngOnInit() {
    this.email = localStorage.getItem('emailId');
    //this._productDetailsService.insertInProductList("dasd", "asdas", "asda")
    console.log("local storage email is: " + this.email);

    this._productDetailsService.getInProductList().subscribe(
      list =>{
        this.incartProductArray = list.map(item =>{ 
          return {
            $key:item.key, ...item.payload.val()
        };
      });
    });
    //  console.log(this.incartProductArray);
    //  for(var cart of this.incartProductArray){
    //    this.totalAmount+=cart.productPrice;
    //  }
     //console.log(this.totalAmount);
     console.log(this.incartProductArray);
  }

  increase(incart){
    console.log("In function increase()");

    if(incart.productQuantityIncart <5){
      (incart.productQuantityIncart)++;
      // this.total=this.total+incart.productPrice;
      this._productDetailsService.updateIncartProduct(incart);
    }
  }

  isLogin(){
    let user = localStorage.getItem('emailId');
    console.log(user);
    
    if(user == null){
      this.route.navigate['/login-page'];
    }
  }

  addPrice(incart){
    this.total.push(incart.productPrice*incart.productQuantityIncart);
    console.log(this.total);
   return incart.productPrice*incart.productQuantityIncart;
  }
  
  serialNumber(){
    
    return this.i++;
  }
  
  getTotal():Number{
     this.sum=0;
    if(this.total.length!=0){
      this.total.forEach(element => {
        this.sum = this.sum + element;
      });
      this.total = [] ;console.log(this.sum);
      return this.sum;
      
    }
    else{return 0;}
  }

  decrease(incart){
    console.log("In function decrease()");
    if(incart.productQuantityIncart >1){
      incart.productQuantityIncart--;
      // this.total=(this.total)-incart.productPrice;
      this._productDetailsService.updateIncartProduct(incart);
    }

  }

  onDelete($key){
    console.log("delete incart",$key);
    this._productDetailsService.deleteIncartProduct($key);
  }

  goToPayment() {
    //this.product.productName=this.incartProductArray.
    //this.order.products.push()
    this.route.navigate(['/payment', { email: this.email, name:this.name,amount: this.sum }]);
  }

}
