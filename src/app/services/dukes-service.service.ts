import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface Candidato {
  lista: string;
  nombre: string;
  edad: string;
  ciudad: string;
  u_titulo_reg: string;
  actividad: string;
  Partido: string;
  Binomio: string;
}

@Injectable({
  providedIn: 'root'
})
export class DukesServiceService {
  private baseUrl = 'http://localhost:8080/PruebaS/api/Candidato';

  constructor(private http: HttpClient) {}

  getClients(): Observable<Candidato[]> {
    return this.http.get<Candidato[]>(this.baseUrl)
      .pipe(
        catchError(error => {
          console.error('Error fetching clients:', error);
          return throwError(() => new Error('Failed to fetch clients data'));
        })
      );
  }
}