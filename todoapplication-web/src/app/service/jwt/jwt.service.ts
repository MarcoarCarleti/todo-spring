import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

const BASE_URL = ['http://localhost:8080/'];

@Injectable({
  providedIn: 'root',
})
export class JwtService {
  constructor(private http: HttpClient, private router: Router) {}

  register(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'signup', signRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', loginRequest);
  }

  task(email: string, done: string): Observable<any> {

    return this.http.get(
      BASE_URL + `api/tasks/done/${email}?done=${done}`,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  getTasksByCustomerEmailAndTitle(
    email: string,
    title: string
  ): Observable<any> {
    return this.http.get(
      BASE_URL + `api/tasks/customer/${email}?title=${title}`,
      {
        headers: this.createAuthorizationHeader(),
      }
    );
  }

  filterTaskEmail(email: string): Observable<any> {
    return this.http.get(BASE_URL + `api/tasks/customer?email=${email}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllTasksByDone(done: string): Observable<any> {
    return this.http.get(BASE_URL + `api/tasks/filterByDone?done=${done}`, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getAllTasks(title: string): Observable<any> {
    return this.http.get(BASE_URL + `api/tasks/filterByTitle?title=${title}`, {
      headers: this.createAuthorizationHeader(),
    });
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

  private createAuthorizationHeader() {
    const jwtToken = localStorage.getItem('jwt');

    if (jwtToken) {
      console.log(jwtToken);
      return new HttpHeaders().set('Authorization', 'Bearer ' + jwtToken);
    } else {
      this.router.navigateByUrl('/');
    }

    return undefined;
  }
}
