import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly TOKEN_KEY = 'authToken';
  private readonly USER_ID = 'userId';
  private readonly PERMISSION_KEY = 'permissions';

  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  saveUserId(user: string): void {
    localStorage.setItem(this.USER_ID, user);
  }

  savePermissoes(permissao: string[]): void {
    localStorage.setItem(this.PERMISSION_KEY, permissao.join(','));
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getPermissoes(): string | null {
    return localStorage.getItem(this.PERMISSION_KEY);
  }

  getUser(): string | null {
    return localStorage.getItem(this.USER_ID);
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_ID);
    localStorage.removeItem(this.PERMISSION_KEY);
  }
}
