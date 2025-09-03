package com.erik.gestao_aprendizagem.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.erik.gestao_aprendizagem.dtos.RegistrarTarefaDTO;
import com.erik.gestao_aprendizagem.dtos.TarefaDTO;
import com.erik.gestao_aprendizagem.models.Tarefa;
import com.erik.gestao_aprendizagem.models.TarefaPk;
import com.erik.gestao_aprendizagem.repositorys.ITarefaRepository;

@Service
public class TarefaService {

    private final ITarefaRepository repository;

    public TarefaService(ITarefaRepository repository) {
        this.repository = repository;
    }

    public void registrarNova(RegistrarTarefaDTO registration) {

        Tarefa tarefa = new Tarefa();

        TarefaPk tarefaPk = new TarefaPk();
        tarefaPk.setIdCurso(registration.getIdCurso());
        tarefaPk.setIdUsuario(registration.getIdUsuario());
        tarefaPk.setDataTarefa(LocalDateTime.now());
        tarefaPk.setIdCategoria(registration.getIdCategoria());
        tarefa.setId(tarefaPk);

        tarefa.setTempoGasto(LocalDateTime.of(0, 1, 1, 0, 0));

        tarefa.setDescricao(registration.getDescricao());

        repository.save(tarefa);

    }

    public Page<TarefaDTO> buscarTodosPorIdUsuario(Long id, int page, int count) {
        return repository.buscarTodosPorIdUsuario(id, PageRequest.of(page, count));
    }

    public void incrementarTarefaPorIdUsuarioECurso(Long idUsuario, Long idCategoria, Long idCurso,
            LocalDateTime dataTarefa) {
        Tarefa tarefa = repository.findById(new TarefaPk(idCurso, idUsuario, idCategoria, dataTarefa)).orElseThrow();
        tarefa.setTempoGasto(tarefa.getTempoGasto().plusMinutes(30));

        repository.save(tarefa);
    }

}
