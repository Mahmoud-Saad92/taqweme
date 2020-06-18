import { Injectable } from '@angular/core';
import { UserLogin } from 'src/app/authentication/login/domian/user.login.model';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BaseHttpService {

  private loginUrl = 'oauth/token';
  private baseUrl = 'http://localhost:8080/taqweme/api/user';

  constructor(private http: HttpClient) { }

  login(user: UserLogin): Observable<any> {

    const body = new HttpParams()
      .set('username', user.getUsername())
      .set('password', user.getPassword())
      .set('grant_type', 'password');

    const headers = {
      'Authorization': 'Basic ' + btoa('taqweme-client:taqweme-secret'),
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post(`${this.loginUrl}`, body.toString(), { headers }) as Observable<any>;
  }
}
