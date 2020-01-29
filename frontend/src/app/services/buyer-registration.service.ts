import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Buyer1 } from '../buyer-registration/buyer';
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
export class BuyerRegistrationService {

  private add_url=environment.buyerAddUrl;
  constructor(private http:HttpClient) { }

  add(buyer:Buyer1):Observable<any>{
    return this.http.post<any>(this.add_url,buyer,httpOptions);
  }
}
