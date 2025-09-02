package com.erik.gestao_aprendizagem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.dtos.RegistrarTarefaDTO;
import com.erik.gestao_aprendizagem.dtos.TarefaDTO;
import com.erik.gestao_aprendizagem.repositorys.ICursoRepository;

@Service
public class CursoService {

    private final ICursoRepository repository;
    private final UsuarioCursoService usuarioCursoService;
    private final TarefaService tarefaService;

    public CursoService(ICursoRepository repository, UsuarioCursoService usuarioCursoService,
            TarefaService tarefaService) {
        this.repository = repository;
        this.usuarioCursoService = usuarioCursoService;
        this.tarefaService = tarefaService;
    }

    public Page<CursoDTO> buscarTodos(int page, int count) {
        return repository.buscarTodosAtivos(PageRequest.of(page, count));
    }

    public Page<CursoDTO> buscarTodosPorEstudante(int page, int count, Long usuarioId) {
        return usuarioCursoService.buscarTodos(page, count, usuarioId);
    }

    public void cadastrarTarefa(RegistrarTarefaDTO tarefaDTO) {
        tarefaService.registrarNova(tarefaDTO);
    }

    public Page<TarefaDTO> buscarTarefasPorEstudante(int page, int count, Long usuarioId) {
        return tarefaService.buscarTodosPorIdUsuarioECurso(usuarioId, page, count);
    }

    public void matricular(Long cursoId, Long usuarioId) {
        usuarioCursoService.matricular(cursoId, usuarioId);
    }

    public CursoDTO buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public CursoDTO salvar(CursoDTO evento) {
        return null;// repository.save(evento.toEntity());
    }

    public CursoDTO atualizar(Long id, CursoDTO evento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    public void remover(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

}
