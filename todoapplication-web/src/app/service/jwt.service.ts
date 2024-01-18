import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = ['http://localhost:8080/'];

@Injectable({
  providedIn: 'root',
})
export class JwtService {
  constructor(private http: HttpClient) {}

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'signup', signRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', loginRequest);
  }

  task(): Observable<any> {
    return this.http.get(BASE_URL + 'api/tasks', {
      headers: this.createAuthorizationHeader(),
    });
  }

  private createAuthorizationHeader() {
    const jwtToken = localStorage.getItem('jwt');

    if (jwtToken) {
      console.log(jwtToken);
      return new HttpHeaders().set('Authorization', 'Bearer ' + jwtToken);
    } else {
      alert('jwt not found');
    }

    return undefined;
  }
}
