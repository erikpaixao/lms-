import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { TokenService } from '../services/token-service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private tokenService = inject(TokenService);
  private router = inject(Router);

  /**
   * Intercepta todas as requisições HTTP para injetar o token de autenticação.
   * @param req A requisição HTTP original.
   * @param next O manipulador para a próxima etapa da requisição.
   * @returns Um Observable com a requisição modificada ou a resposta da API.
   */
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const authToken = this.tokenService.getToken();

    let authReq = req;
    if (authToken) {
      authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${authToken}`,
        },
      });
    }

    return next.handle(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 || error.status === 403) {
          this.tokenService.removeToken();
          this.router.navigate(['/login']);
        }
        return throwError(() => error);
      })
    );
  }
}
