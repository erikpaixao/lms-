import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { env } from '../environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Injeta o HttpClient para fazer requisições HTTP
  private http = inject(HttpClient);

  private apiUrl = env.backend_api;

  // Define as URLs das APIs de login e registro
  private loginUrl = this.apiUrl + '/auth/login';
  private registerUrl = this.apiUrl + '/auth/registro';

  /**
   * Envia as credenciais de login para a API.
   * @param credentials O objeto com e-mail e senha.
   * @returns Uma Promise que resolve com a resposta da API em caso de sucesso.
   */
  login(credentials: { username: string; password: string }): Promise<any> {
    // Retorna uma Promise para manter a compatibilidade com o código do componente.
    // O ideal é que o componente lide diretamente com o Observable.
    return firstValueFrom(this.http.post<any>(this.loginUrl, credentials));
  }

  /**
   * Envia os dados de registro para a API.
   * @param userData O objeto com os dados do usuário.
   * @returns Uma Promise que resolve com a resposta da API em caso de sucesso.
   */
  register(userData: any): Promise<any> {
    // Retorna uma Promise que resolve com a resposta da API
    return firstValueFrom(this.http.post<any>(this.registerUrl, userData));
  }
}
