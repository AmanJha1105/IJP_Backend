import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Opening } from '../../components/opening/opening.model';

@Injectable({
  providedIn: 'root'
})
export class OpeningService {
  private apiUrl = 'http://localhost:8086/api/openings';

  constructor(private http: HttpClient) {}

  getOpenings(): Observable<Opening[]> {
    return this.http.get<Opening[]>(`${this.apiUrl}/all`);
  }

  getOpeningById(id: number): Observable<Opening> {
    return this.http.get<Opening>(`${this.apiUrl}/${id}`);
  }

  addOpening(opening: Opening): Observable<Opening> {
    console.log("control reaches here");
    console.log(opening);
    
    return this.http.post<Opening>(`${this.apiUrl}/add`, opening);
  }

  updateOpening(id: number, opening: Opening): Observable<Opening> {
    return this.http.put<Opening>(`${this.apiUrl}/update/${id}`, opening);
  }

  deleteOpening(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
