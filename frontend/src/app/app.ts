import { AsyncPipe, NgIf } from '@angular/common';
import { Component, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NgToastComponent, TOAST_POSITIONS } from 'ng-angular-popup';
import { Loader } from './core/components/loader/loader';
import { LoaderService } from './core/services/loader/loader';

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

  constructor(private loaderService: LoaderService) {
    this.loading = this.loaderService.loading$;
  }
}
