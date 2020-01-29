import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IRecommendation } from '../recommendation';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyerDashboardServiceService {
  
  private url:string="http://15.206.62.131:3000/posts";

  constructor(private http:HttpClient) { }

  getRecommendations():Observable<IRecommendation>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
    return this.http.get<IRecommendation>(this.url,httpOptions);
  }
}
