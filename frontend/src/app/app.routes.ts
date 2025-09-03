import { Routes } from '@angular/router';
import { CursoComponent } from './pages/curso/curso';
import { Home } from './pages/home/home';
import { Login } from './pages/login/login';
import { MeusCursos } from './pages/meus-cursos/meus-cursos';
import { NovoCursoComponent } from './pages/novo-curso/novo-curso';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'meus-cursos', component: MeusCursos },
  { path: 'curso', component: CursoComponent },
  { path: 'novo-curso', component: NovoCursoComponent },
  { path: 'curso/:id', component: CursoComponent },
];
