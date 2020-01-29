import { Component, OnInit } from '@angular/core';
import { ProductDetailsService } from '../services/product-details.service';
import { DealsService } from '../services/deals.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { SellerDashboardService } from '../services/seller-dashboard.service';
import { IncartProducts } from '../incart-products/incartProducts';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  private product:any;
  private seller:any;
  private searchProductName: string;
  private sellerId: string;

  private incartProduct: IncartProducts = new IncartProducts();
  private showSuccessMsg: boolean;
  private total = 0;

  constructor(private router: Router, 
    private _sellerDashboardService: SellerDashboardService, 
    private route: ActivatedRoute, 
    private _dealsService : DealsService, 
    private _productDetailsService: ProductDetailsService) { }

  ngOnInit() {
    // this._productDetailsService.getProductInfo().subscribe(response=>
    //   { 
    //     this.product = response;
    //     console.log(this.product);
    //   }
    // );
    this._productDetailsService.getInProductList();

    this.route.paramMap.subscribe((params:ParamMap)=> 
      {
        this.sellerId = params.get('sellerId');
        this.searchProductName = params.get('searchProductName');
        console.log(this.sellerId);
        console.log(this.searchProductName);
      }
    );
    
    this._dealsService.findProductByName(this.searchProductName).subscribe(response=>
      {
        this.product = response;
        console.log(this.product);
      }  
    );
    this._dealsService.findSellerById(this.searchProductName,this.sellerId).subscribe(response=>
      {
        this.seller = response;
        console.log(this.seller);   
      }  
    );
  }

  addToCart(){
    // this._productDetailsService.addProductToCart(this.product).subscribe();
    console.log("Inside addToCart() : ");
    console.log(this.product);

    this.incartProduct.productBrandName = this.product.productBrandName;
    this.incartProduct.productDescription = this.product.productDescription;
    this.incartProduct.productPrice = this.seller.productPrice;
    this.incartProduct.productImage = this.product.productImage;
    this.incartProduct.productQuantityIncart = 1;
    this.incartProduct.productName = this.product.productName;
    this.incartProduct.productId = this.product.productId;
    this.incartProduct.inCartTotal = 0;
    this.incartProduct.userEmail=localStorage.getItem('emailId');
    this._productDetailsService.insertInProductList(this.product, this.seller, this.incartProduct);
    this.showSuccessMsg = true;

    setTimeout(() => this.showSuccessMsg = false, 3000);
    // this.router.navigate(['./app-incart-products', { product:this.product }]);
  }

  addToWishlist(){
    // this._productDetailsService.addProductToWishlist(this.product).subscribe();
  }

}
