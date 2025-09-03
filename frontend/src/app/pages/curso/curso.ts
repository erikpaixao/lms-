import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { MATERIAL_MODULES } from '../../core/shared/material';
import { CategotiaService } from '../../services/categoria';
import { CursoService } from '../../services/curso';
import { TarefaService } from '../../services/tarefa';

@Component({
  selector: 'app-curso',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, ...MATERIAL_MODULES],
  templateUrl: './curso.html',
  styleUrl: './curso.css',
})
export class CursoComponent implements OnInit {
  private courseId: any = null;
  public categorias: any = [];
  public tarefas: any = [];

  curso: any = null;
  isEditMode = signal(false);

  private cursoService = inject(CursoService);
  private categoriaService = inject(CategotiaService);
  private tarefaService = inject(TarefaService);
  private toast = inject(NgToastService);
  private route = inject(ActivatedRoute);

  editCourseForm = new FormGroup({
    nome: new FormControl('', [Validators.required]),
  });

  novaTarefaForm = new FormGroup({
    descricao: new FormControl('', [Validators.required]),
    categoria: new FormControl('', [Validators.required]),
  });

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.courseId = params['id'];
      this.buscarCurso();
    });
    this.buscarCategorias();
    this.buscarTarefas();
  }

  buscarCurso() {
    this.cursoService.getById(this.courseId).subscribe((cursoCompleto) => {
      this.curso = cursoCompleto;
      this.editCourseForm.setValue({ nome: cursoCompleto.nome });
    });
  }

  buscarCategorias() {
    this.categoriaService.getAll(0, 10).subscribe((response) => {
      this.categorias = response.content;
    });
  }

  buscarTarefas() {
    this.tarefaService
      .getAllByUsuarioECurso(this.courseId, 0, 10)
      .subscribe((response) => {
        this.tarefas = response.content;
      });
  }

  toggleEditMode() {
    this.isEditMode.set(!this.isEditMode());
    if (this.isEditMode()) {
      this.editCourseForm.patchValue({ nome: this.curso?.nome });
    }
  }

  async onEditCourseSubmit() {
    if (this.editCourseForm.valid) {
      // try {
      //   const novoNome = this.editCourseForm.value.nome as string;
      //   await this.cursoService.updateCourse(this.courseId, novoNome);
      //   this.curso.update((c) => (c ? { ...c, nome: novoNome } : null));
      //   this.toggleEditMode();
      // } catch (error) {
      //   console.error('Erro ao atualizar o curso:', error);
      // }
    }
  }

  async onAddTaskSubmit() {
    if (this.novaTarefaForm.valid) {
      const tarefaNOva = {
        idUsuario: 1,
        idCurso: this.courseId,
        descricao: this.novaTarefaForm.value.descricao!,
        idCategoria: this.novaTarefaForm.value.categoria!,
      };

      this.tarefaService.create(tarefaNOva).subscribe(
        () => {
          this.toast.success('Tarefa adicionada com sucesso!', 'Sucesso', 5000);
          this.buscarTarefas();
          this.novaTarefaForm.reset();
          this.novaTarefaForm.markAsUntouched();
        },
        () => {
          this.toast.danger('Erro ao adicionar tarefa.', 'Erro', 10000);
        }
      );
    }
  }

  async incrementarTempo(tarefa: any) {
    const incremntDTO = {
      idCurso: this.courseId,
      idUsuario: 1,
      idCategoria: tarefa.idCategoria,
      dataTarefa: tarefa.dataTarefa,
    };

    this.tarefaService.incrementar(incremntDTO).subscribe(
      () => {
        this.openToastSucess('Tempo incrementado com sucesso!');
        this.buscarTarefas();
        this.novaTarefaForm.reset();
        this.novaTarefaForm.markAsUntouched();
      },
      (error) => {
        this.openToastError('Erro ao incrementar tempo: ' + error.error.erro);
      }
    );
  }

  removerTarefa(tarefa: any) {
    const deleteDTO = {
      idCurso: this.courseId,
      idCategoria: tarefa.idCategoria,
      dataTarefa: tarefa.dataTarefa,
    };

    this.tarefaService.deletar(deleteDTO).subscribe(
      () => {
        this.openToastSucess('Tarefa removida com sucesso!');
        this.buscarTarefas();
        this.novaTarefaForm.reset();
        this.novaTarefaForm.markAsUntouched();
      },
      (error) => {
        this.openToastError('Erro ao remover tarefa: ' + error.error.erro);
      }
    );
  }

  formatTime(isoDateString: string): string {
    const date = new Date(isoDateString);
    return date.toLocaleString('pt-BR', {
      dateStyle: 'short',
      timeStyle: 'short',
    });
  }

  private openToastError(message: string) {
    this.toast.danger(message, 'Erro', 10000);
  }

  private openToastSucess(message: string) {
    this.toast.success(message, 'Sucesso', 10000);
  }
}
