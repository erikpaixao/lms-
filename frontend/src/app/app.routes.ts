import { Routes } from '@angular/router';
import { CursoComponent } from './pages/curso/curso';
import { Home } from './pages/home/home';
import { Login } from './pages/login/login';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'curso', component: CursoComponent },
  { path: 'curso/:id', component: CursoComponent },
];
