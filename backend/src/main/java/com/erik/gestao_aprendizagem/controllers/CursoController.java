package com.erik.gestao_aprendizagem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.dtos.IncrementarTarefaDTO;
import com.erik.gestao_aprendizagem.dtos.MatriculaDTO;
import com.erik.gestao_aprendizagem.dtos.RegistrarTarefaDTO;
import com.erik.gestao_aprendizagem.dtos.TarefaDTO;
import com.erik.gestao_aprendizagem.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/curso")
@Tag(name = "Curso", description = "Gerenciamento de Aprendizagem")
public class CursoController {

    @Autowired
    CursoService service;

    @Operation(summary = "Listar todos os cursos paginados")
    @GetMapping("/all")
    public ResponseEntity<Page<CursoDTO>> getAll(
            @Parameter(description = "Número da página (iniciando em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("count") int count) {
        return ResponseEntity.ok(service.buscarTodos(page, count));
    }

    @Operation(summary = "Buscar um curso pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> get(
            @Parameter(description = "ID do curso") @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Matricular em um curso")
    @PostMapping("/matricula")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Void> matricular(@Valid @RequestBody MatriculaDTO evento) {
        service.matricular(evento.getCursoId(), evento.getUsuarioId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar os cursos por estudante")
    @GetMapping("/matricula/estudante/{usuarioId}")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Page<CursoDTO>> matricular(@PathVariable Long usuarioId,
            @Parameter(description = "Número da página (iniciando em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("count") int count) {
        return ResponseEntity.ok(service.buscarTodosPorEstudante(page, count, usuarioId));
    }

    @Operation(summary = "Adicionar tarefa por curso e estudante")
    @PostMapping("/tarefa")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Page<CursoDTO>> criarTarefa(@Valid @RequestBody RegistrarTarefaDTO tarefaDTO) {
        service.cadastrarTarefa(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar tarefa por estudante")
    @GetMapping("/tarefa/estudante/{usuarioId}")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Page<TarefaDTO>> buscarTarefaPorUsuario(@PathVariable Long usuarioId,
            @Parameter(description = "Número da página (iniciando em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("count") int count) {
        return ResponseEntity.ok(service.buscarTarefasPorEstudante(page, count, usuarioId));
    }

    @Operation(summary = "Acrescentar tarefa por estudante")
    @PostMapping("/tarefa/estudante")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Page<TarefaDTO>> buscarTarefaPorUsuario(
            @Valid @RequestBody IncrementarTarefaDTO incrementarTarefaDTO) {
        service.incrementarTarefasPorEstudanteECurso(incrementarTarefaDTO.getIdUsuario(),
                incrementarTarefaDTO.getIdCategoria(), incrementarTarefaDTO.getIdCurso(),
                incrementarTarefaDTO.getDataTarefa());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Criar um novo curso")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoDTO> save(@Valid @RequestBody CursoDTO evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(evento));
    }

    @Operation(summary = "Atualizar um curso existente")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CursoDTO> update(
            @Parameter(description = "ID do curso") @PathVariable("id") Long id,
            @Valid @RequestBody CursoDTO evento) {
        return ResponseEntity.ok(service.atualizar(id, evento));
    }

    @Operation(summary = "Remover um curso")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do curso") @PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
