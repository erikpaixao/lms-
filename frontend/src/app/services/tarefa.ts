import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { env } from '../core/environment';
import { TokenService } from './../core/services/token-service';

@Injectable({
  providedIn: 'root',
})
export class TarefaService {
  private apiUrl = env.backend_api + '/tarefa';

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  getAllByUsuarioECurso(
    cursoId: number,
    page: number,
    count: number
  ): Observable<any> {
    const userId = this.tokenService.getUser();
    return this.http.get<any>(
      `${this.apiUrl}/estudante/${userId}/curso/${cursoId}?page=${page}&count=${count}`
    );
  }

  create(registro: any): Observable<any> {
    const userId = this.tokenService.getUser();
    registro.idUsuario = userId;
    return this.http.post<any>(`${this.apiUrl}/criar`, registro);
  }

  incrementar(registro: any): Observable<any> {
    const userId = this.tokenService.getUser();
    registro.idUsuario = userId;
    return this.http.post<any>(`${this.apiUrl}/estudante`, registro);
  }

  deletar(registro: any): Observable<any> {
    const userId = this.tokenService.getUser();
    registro.idUsuario = userId;
    return this.http.post<any>(`${this.apiUrl}/delete`, registro);
  }
}
