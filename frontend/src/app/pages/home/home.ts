import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { NgToastService } from 'ng-angular-popup';

import { Router } from '@angular/router';
import { AlertDialogComponent } from '../../core/components/alert-dialog/alert-dialog';
import { AuthPermissionDirective } from '../../core/diretiva/permissao-diretiva';
import { Curso } from '../../core/models/curso.model';
import { MATERIAL_MODULES } from '../../core/shared/material';
import { CursoService } from '../../services/curso';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, AuthPermissionDirective, ...MATERIAL_MODULES],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit {
  displayedColumns: string[] = ['nome', 'dataInicio', 'dataFim', 'actions'];
  cursos: Curso[] = [];
  totalElements = 0;
  pageSize = 5;
  pageIndex = 0;

  constructor(
    private service: CursoService,
    private dialog: MatDialog,
    private toast: NgToastService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load(page: number = 0, count: number = this.pageSize): void {
    this.service.getAll(page, count).subscribe({
      next: (data) => {
        this.cursos = data.content;
        this.totalElements = data.totalElements;
        this.pageIndex = data.number;
        this.pageSize = data.size;
      },
      error: (err) => {
        this.openToastError(`Erro ao carregar cursos: ${err.error.erro}`);
      },
    });
  }

  onPageChange(event: PageEvent): void {
    this.load(event.pageIndex, event.pageSize);
  }

  openCreateDialog() {
    this.router.navigate(['novo-curso']);
  }

  openEditDialog(event: Curso) {
    this.router.navigate(['curso', event.id], {
      queryParams: { readonly: false, data: JSON.stringify(event) },
    });
  }

  openViewDialog(event: Curso) {
    this.router.navigate(['curso', event.id], {
      queryParams: { readonly: true, data: JSON.stringify(event) },
    });
  }

  deleteEvent(event: Curso) {
    this.openAlert(`Deseja excluir o curso "${event.nome}"?`)
      .afterClosed()
      .subscribe((result) => {
        if (result) {
          this.service.delete(event.id).subscribe({
            next: () => {
              this.openToastSucess(`Dados removidos com sucesso!`);
              this.load();
            },
            error: (err) => {
              this.openToastError(`Erro ao excluir curso: ${err.error.erro}`);
            },
          });
        }
      });
  }

  matricular(event: Curso) {
    this.openAlert(`Deseja se matricular no curso "${event.nome}"?`)
      .afterClosed()
      .subscribe((result) => {
        if (result) {
          this.service.matricular(event.id).subscribe(
            () => {
              this.openToastSucess(`Matricula realizada com sucesso!`);
              this.openEditDialog(event);
            },
            (err: any) => {
              this.openToastError(
                `Erro ao se matricular no curso: ${err.error.erro}`
              );
            }
          );
        }
      });
  }

  private openToastError(message: string) {
    this.toast.danger(message, 'Erro', 10000);
  }

  private openToastSucess(message: string) {
    this.toast.success(message, 'Sucesso', 10000);
  }

  private openAlert(message: string) {
    return this.dialog.open(AlertDialogComponent, {
      data: { message },
    });
  }
}
