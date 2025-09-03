import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { env } from '../core/environment';
import { Curso } from '../core/models/curso.model';
import { TokenService } from '../core/services/token-service';

@Injectable({
  providedIn: 'root',
})
export class CursoService {
  private apiUrl = env.backend_api + '/curso';

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  getAll(page: number, count: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/all?page=${page}&count=${count}`);
  }

  getTodosMatriculados(page: number, count: number): Observable<any> {
    const userId = this.tokenService.getUser();
    return this.http.get<any>(
      `${this.apiUrl}/matricula/estudante/${userId}?page=${page}&count=${count}`
    );
  }

  matricular(cursoId: number): Observable<void> {
    const usuarioId = this.tokenService.getUser();
    return this.http.post<void>(`${this.apiUrl}/matricula`, {
      cursoId,
      usuarioId,
    });
  }

  getById(id: string): Observable<Curso> {
    return this.http.get<Curso>(`${this.apiUrl}/${id}`);
  }

  create(event: Partial<Curso>): Observable<Curso> {
    return this.http.post<Curso>(this.apiUrl, event);
  }

  update(id: number, event: Partial<Curso>): Observable<Curso> {
    return this.http.put<Curso>(`${this.apiUrl}/${id}`, event);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
