import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Iproducts } from '../products';


@Injectable({
  providedIn: 'root'
})

export class ProductService {

private _url: string="/assets/data/product.json";

  constructor(private http: HttpClient) { }
  getProducts():Observable<Iproducts[]> {
    return this.http.get<Iproducts[]>(this._url);
  }
  
}

