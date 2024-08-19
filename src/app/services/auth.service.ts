import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8083/api/employee';
  
  constructor(private http: HttpClient) {}

  login(username: string, password: string, isAdmin: boolean): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { username, password, isAdmin });
  }

  register(data: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, data);
  }
}
