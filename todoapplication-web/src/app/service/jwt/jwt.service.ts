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
    const costumerEmail = localStorage.getItem('email');

    return this.http.get(BASE_URL + `api/tasks/${costumerEmail}`, {
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

  deleteTask(taskId: number): Observable<any> {
    console.log(taskId);
    return this.http.delete(BASE_URL + `api/tasks/${taskId}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  updateTask(taskId: number, body: any): Observable<any> {
    console.log(taskId);
    return this.http.put(BASE_URL + `api/tasks/${taskId}`, body, {
      headers: this.createAuthorizationHeader(),
    });
  }

  addTask(taskBody: any): Observable<any> {
    return this.http.post(BASE_URL + 'api/tasks/', taskBody, {
      headers: this.createAuthorizationHeader(),
    });
  }
}