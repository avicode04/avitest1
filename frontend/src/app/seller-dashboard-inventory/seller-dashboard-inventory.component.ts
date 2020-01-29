import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { SellerDashboardService } from '../services/seller-dashboard.service';


export interface SellerInventory {
  productName: string;
  productImage: string;
  productDescription: string;
  productCategory: string;
  productSubcategory: string;
}
const INVENTORY_DATA: SellerInventory[] = [
  {productName: 'Deepak Tiwary', productImage: 'img1', productDescription: 'desc1', productCategory: 'cat1', productSubcategory: 'subCat1'},
  {productName: 'Deepak Tiwary', productImage: 'img2', productDescription: 'desc2', productCategory: 'cat2', productSubcategory: 'subCat2'},
  {productName: 'Deepak Tiwary', productImage: 'img3', productDescription: 'desc3', productCategory: 'cat3', productSubcategory: 'subCat3'},
  {productName: 'Deepak Tiwary', productImage: 'img4', productDescription: 'desc4', productCategory: 'cat4', productSubcategory: 'subCat4'},
  {productName: 'Deepak Tiwary', productImage: 'img5', productDescription: 'desc5', productCategory: 'cat5', productSubcategory: 'subCat5'},
  {productName: 'Deepak Tiwary', productImage: 'img6', productDescription: 'desc6', productCategory: 'cat6', productSubcategory: 'subCat6'},
  {productName: 'Deepak Tiwary', productImage: 'img7', productDescription: 'desc7', productCategory: 'cat7', productSubcategory: 'subCat7'},
];


@Component({
  selector: 'app-seller-dashboard-inventory',
  templateUrl: './seller-dashboard-inventory.component.html',
  styleUrls: ['./seller-dashboard-inventory.component.css']
})

export class SellerDashboardInventoryComponent implements OnInit {

  private eMail: String;
  private seller;
  private InStock: number;

  constructor(private router: Router, private route: ActivatedRoute, private _sellerDashboardService: SellerDashboardService) { }

  ngOnInit() { 
    this.route.paramMap.subscribe((params:ParamMap)=> {
      this.eMail = params.get('eMail');

      console.log(this.eMail);
    });

    this._sellerDashboardService.getFromDatabase(this.eMail).subscribe(response => { 
      this.seller = response
      // this.InStock = this.seller.sellerProducts.length 
    });

    console.log(this.seller);
  }

  displayedColumns: string[] = ['productName', 'productImage', 'productDescription', 'productCategory', 'productSubcategory','editProduct'];
  dataSource = INVENTORY_DATA;
  // dataSource = this.seller.sellerProducts;

  addProduct(){
    this.router.navigate(['./update-product', { eMail:this.eMail }]);
  }

  goToSellerDashboard(){
    this.router.navigate(['./seller-dashboard', { eMail:this.eMail }]);
  }
  
  editProduct(prName){
    this.router.navigate(['./edit-product', { eMail:this.eMail,prName:prName }]);
  }
  // updateProduct(){
  //   this.router.navigate(['./', { eMail:this.eMail }]);
  // }

}