import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Seller1 } from '../seller-registration/seller';
import { environment } from 'src/environments/environment'


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class SellerRegistrationService {

  private add_url = environment.updateToDatabaseUrl;
  
  constructor(private http: HttpClient) { }

  add(seller: Seller1): Observable<any>{
    return this.http.post<any>(this.add_url,seller,httpOptions);

  }
}
