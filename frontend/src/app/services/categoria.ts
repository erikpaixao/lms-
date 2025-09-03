import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { env } from '../core/environment';

@Injectable({
  providedIn: 'root',
})
export class CategotiaService {
  private apiUrl = env.backend_api + '/categoria';

  constructor(private http: HttpClient) {}

  getAll(page: number, count: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/all?page=${page}&count=${count}`);
  }
}
