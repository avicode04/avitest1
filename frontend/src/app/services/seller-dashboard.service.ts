import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { environment } from 'src/environments/environment'

@Injectable({
  providedIn: 'root'
})

export class SellerDashboardService {

  constructor(private _http: HttpClient) { }

  getFromDatabase (sellerEmail):Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })};
    return this._http.get<any>(environment.getFromDatabaseUrl+`${sellerEmail}`,httpOptions);
  }

  // saveToDatabase (bussinessData):Observable<any> {
  //   return this._http.post<any>('http://localhost:3000/posts/',bussinessData);
  // }

  updateToDatabase(seller):Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })};
    return this._http.put<any>(environment.updateToDatabaseUrl, seller,httpOptions);
  }


}
