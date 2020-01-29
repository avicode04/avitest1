import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment'

const httpOptions = {
  headers: new HttpHeaders({'Content-Type':'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class DealsService {

  private productsUrl: string;
 
  constructor(private http: HttpClient) {
    this.productsUrl = environment.dealsUrl;
  }
 
  public findAll(): any {
    console.log("hello from service");
    console.log(this.http.get(this.productsUrl));
    return this.http.get(this.productsUrl);

  }
 
  public save(product: any) {
    return this.http.post(this.productsUrl, product);
  }

  public findProduct(productName) {
    var str=productName.toString().toLowerCase();
    console.log(environment.findSellerByIdUrl+`${productName}`);
    return this.http.get(environment.findSellerByIdUrl+`${str}`);
  }

  public findProductByName(productName){
    console.log(environment.findProductByNameUrl+`name/${productName}`);
    return this.http.get(environment.findProductByNameUrl+`name/${productName}`);
  }

  public findSellerById(productName,sellerId){
    console.log(environment.findSellerByIdUrl+`${productName}/${sellerId}/`)
    return this.http.get(environment.findSellerByIdUrl+`${productName}/${sellerId}/`);
  }
}
