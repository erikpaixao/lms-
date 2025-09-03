import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly TOKEN_KEY = 'authToken';
  private readonly USER_ID = 'userId';
  private readonly PERMISSION_KEY = 'permissions';
  /**
   * Armazena o token no localStorage.
   * @param token O token de autenticação a ser salvo.
   */
  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  saveUserId(user: string): void {
    localStorage.setItem(this.USER_ID, user);
  }

  savePermissoes(permissao: string[]): void {
    localStorage.setItem(this.PERMISSION_KEY, permissao.join(','));
  }

  /**
   * Recupera o token do localStorage.
   * @returns O token de autenticação, ou null se não existir.
   */
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getUser(): string | null {
    return localStorage.getItem(this.USER_ID);
  }

  /**
   * Remove o token do localStorage.
   */
  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
