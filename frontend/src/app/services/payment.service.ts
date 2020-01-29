import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PayCustomer } from '../payment/payCustomer';
import { Charge } from '../payment/charge';

@Injectable({
  providedIn: 'root'
})

export class PaymentService {

  private addStripeCust_url="https://shopperzoid.stackroute.io:8443/paymentService/api/v1/payment/customer";
  private addCustTodatabase_url="https://shopperzoid.stackroute.io:8443/paymentService/api/v1/payment/addCustomer";
  private getCustDetail_url="https://shopperzoid.stackroute.io:8443/paymentService/api/v1/payment/getcus/";
  private addCardToStripe_url="https://shopperzoid.stackroute.io:8443/paymentService/api/v1/payment/card";
  private charge_url="https://shopperzoid.stackroute.io:8443/paymentService/api/v1/payment/charge";
  private deleteCard_url="https://shopperzoid.stackroute.io:8443/paymentService/api/v1/payment/card";
 
  constructor(private http : HttpClient) { }

  addStripeCust(name,email):Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'name':  name,
        'email': email,
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    }
    return this.http.post<any>(this.addStripeCust_url,"", httpOptions);
  }

  addCustToDatabase(customer:PayCustomer):Observable<PayCustomer>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')        
      })    
    }
    return this.http.post<PayCustomer>(this.addCustTodatabase_url,customer,httpOptions)
  }

  getCustID(custEmail:String):Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')        
      })    
    }
    return this.http.get<any>(this.getCustDetail_url+`${custEmail}`,httpOptions);
  }

  addCardToStripe(customerId,number,exp_month,exp_year):Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'customerId': customerId,
        'number': number,
        'exp_month':exp_month,
        'exp_year':exp_year,
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    }
    return this.http.post<any>(this.addCardToStripe_url,"",httpOptions);
  }

  charge(customerId,amount):Observable<Charge>{
    const httpOptions = {
      headers: new HttpHeaders({
        'customer': customerId,
        'amount': amount,
        'Authorization': 'Bearer ' + localStorage.getItem('token')        
      })    
    }
    return this.http.post<Charge>(this.charge_url,"",httpOptions);
  }

  deleteCard(customerId,cardId):Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'customerId': customerId,
        'cardId': cardId,
        'Authorization': 'Bearer ' + localStorage.getItem('token')        
      })    
    }
    return this.http.delete<any>(this.deleteCard_url,httpOptions);
  }

}
