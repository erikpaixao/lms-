import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { MATERIAL_MODULES } from '../../core/shared/material';
import { CursoService } from '../../services/curso';

@Component({
  selector: 'app-novo-curso',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ...MATERIAL_MODULES],
  templateUrl: './novo-curso.html',
  styleUrl: './novo-curso.css',
})
export class NovoCursoComponent implements OnInit {
  private courseId: any = null;
  public categorias: any = [];
  public tarefas: any = [];

  curso: any = null;
  isEditMode = signal(false);

  private cursoService = inject(CursoService);
  private toast = inject(NgToastService);
  private router = inject(Router);

  editCourseForm = new FormGroup({
    nome: new FormControl('', [Validators.required]),
    dataInicio: new FormControl<Date | null>(null, [Validators.required]),
  });

  ngOnInit() {}

  buscarCurso() {
    this.cursoService.getById(this.courseId).subscribe((cursoCompleto) => {
      this.curso = cursoCompleto;
      this.editCourseForm.setValue({
        nome: cursoCompleto.nome,
        dataInicio: cursoCompleto.dataInicio
          ? new Date(cursoCompleto.dataInicio)
          : null,
      });
    });
  }

  async onEditCourseSubmit() {
    if (this.editCourseForm.valid) {
      const curso = {
        nome: this.editCourseForm.value.nome!,
        dataInicio: this.editCourseForm.value.dataInicio!,
      };

      this.cursoService.create(curso).subscribe(
        (updatedCurso) => {
          this.curso = updatedCurso;
          this.openToastSucess('Curso criado com sucesso!');
          this.router.navigate(['']);
        },
        (error) => {
          this.openToastError('Erro ao criar o curso: ' + error.error.erro);
        }
      );
    }
  }

  private openToastError(message: string) {
    this.toast.danger(message, 'Erro', 10000);
  }

  private openToastSucess(message: string) {
    this.toast.success(message, 'Sucesso', 10000);
  }
}
