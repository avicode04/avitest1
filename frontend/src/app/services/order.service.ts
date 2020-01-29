import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface Product {
  productName: string;
  productDescription: string;
  productPrice: any; //what type ?
  productImage: string;
  sellerEmail: string;
  productQty: number;
}

export interface Order {
  rating: any; //what type ??
  products: Product[];
  buyerPhone: any;
  buyerHomeAddress: string;
  buyerOfficeAddress: string;
  status: string;
}

const buyer_Orders: Order [] = [
  // {productName: 'Deepak Tiwary', productImage: 'img1', productDescription: 'desc1', productCategory: 'cat1', productSubcategory: 'subCat1'},
];


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private _http: HttpClient) { }

  saveToOrdersOnBuyerProfile(order): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };

    return this._http.post(environment.saveToOrdersOnBuyerProfileUrl, JSON.stringify(order), httpOptions);
  }

  fetchAllOrdersOnBuyerProfile(buyerEmail): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };

    return this._http.get<any>(environment.saveToOrdersOnBuyerProfileUrl+`/buyerEmail/${buyerEmail}`, httpOptions);
  }

}
