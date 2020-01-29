import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-trending-deals',
  templateUrl: './trending-deals.component.html',
  styleUrls: ['./trending-deals.component.css']
})

export class TrendingDealsComponent implements OnInit {


  public products= [];
  constructor(private _productService: ProductService) { }

  ngOnInit() {
  this._productService.getProducts().subscribe(data => {this.products=data; console.log(this.products)});
  }

  
}
