
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment'

@Injectable({
  providedIn: 'root'
})
export class SocialLoginService {

  constructor(private http: HttpClient) { }
  Login(userObj): Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    // tslint:disable-next-line: variable-name
    let post_url = environment.loginUrl;
    console.log(userObj.userEmail, 'Inside Service Email');
    return this.http.post(post_url, userObj, httpOptions);
}
Register(userObj): Observable<any> {
  const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  let post_url = `http://localhost:8080/auth/register`;
  console.log(userObj.userEmail, 'Inside Service Email');
  return this.http.post(post_url, userObj, httpOptions);
}
}
