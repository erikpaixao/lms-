import { AsyncPipe, NgIf } from '@angular/common';
import { Component, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import {
  NavigationEnd,
  Router,
  RouterModule,
  RouterOutlet,
} from '@angular/router';
import { NgToastComponent, TOAST_POSITIONS } from 'ng-angular-popup';
import { filter } from 'rxjs';
import { Loader } from './core/components/loader/loader';
import { LoaderService } from './core/services/loader/loader';
import { TokenService } from './core/services/token-service';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    Loader,
    NgIf,
    AsyncPipe,
    NgToastComponent,
    MatSidenavModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    RouterModule,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('gestao-eventos');

  loading: typeof this.loaderService.loading$;
  TOAST_POSITIONS = TOAST_POSITIONS;
  showMenu = signal(false);

  constructor(
    private loaderService: LoaderService,
    private tokenService: TokenService,
    private router: Router
  ) {
    this.loading = this.loaderService.loading$;
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.showMenu.set(!event.urlAfterRedirects.includes('/login'));
      });
  }

  onLogout() {
    // Chama o método de logout do serviço de token
    this.tokenService.removeToken();
    // Redireciona o usuário para a página de login ou outra página apropriada
    window.location.href = '/login'; // Ajuste a URL conforme necessário
  }
}
