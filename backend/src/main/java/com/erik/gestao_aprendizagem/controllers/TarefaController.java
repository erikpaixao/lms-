package com.erik.gestao_aprendizagem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.dtos.IncrementarTarefaDTO;
import com.erik.gestao_aprendizagem.dtos.RegistrarTarefaDTO;
import com.erik.gestao_aprendizagem.dtos.TarefaDTO;
import com.erik.gestao_aprendizagem.models.TarefaPk;
import com.erik.gestao_aprendizagem.services.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tarefa")
@Tag(name = "Tarefa", description = "Gerenciamento de Aprendizagem")
public class TarefaController {

    @Autowired
    TarefaService service;

    @Operation(summary = "Adicionar tarefa por curso e estudante")
    @PostMapping("/criar")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Page<CursoDTO>> criarTarefa(@Valid @RequestBody RegistrarTarefaDTO tarefaDTO) {
        service.registrarNova(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar tarefa por estudante")
    @GetMapping("/estudante/{usuarioId}/curso/{cursoId}")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Page<TarefaDTO>> buscarTarefaPorUsuario(@PathVariable Long usuarioId,
            @PathVariable Long cursoId,
            @Parameter(description = "Número da página (iniciando em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("count") int count) {
        return ResponseEntity.ok(service.buscarTodosPorIdUsuario(usuarioId, cursoId, page, count));
    }

    @Operation(summary = "Acrescentar tarefa por estudante")
    @PostMapping("/estudante")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Void> buscarTarefaPorUsuario(
            @Valid @RequestBody IncrementarTarefaDTO incrementarTarefaDTO) {
        service.incrementarTarefaPorIdUsuarioECurso(incrementarTarefaDTO.getIdUsuario(),
                incrementarTarefaDTO.getIdCategoria(), incrementarTarefaDTO.getIdCurso(),
                incrementarTarefaDTO.getDataTarefa());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Remover uma tarefa")
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ESTUDANTE')")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do curso") @RequestBody TarefaPk id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
