package com.erik.gestao_aprendizagem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.models.Curso;
import com.erik.gestao_aprendizagem.repositorys.ICursoRepository;

@Service
public class CursoService {

    private final ICursoRepository repository;
    private final UsuarioCursoService usuarioCursoService;

    public CursoService(ICursoRepository repository, UsuarioCursoService usuarioCursoService) {
        this.repository = repository;
        this.usuarioCursoService = usuarioCursoService;
    }

    public Page<CursoDTO> buscarTodos(int page, int count) {
        return repository.buscarTodosAtivos(PageRequest.of(page, count));
    }

    public Page<CursoDTO> buscarTodosPorEstudante(int page, int count, Long usuarioId) {
        return usuarioCursoService.buscarTodos(page, count, usuarioId);
    }

    public void matricular(Long cursoId, Long usuarioId) {
        usuarioCursoService.matricular(cursoId, usuarioId);
    }

    public CursoDTO buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public CursoDTO salvar(CursoDTO evento) {
        Curso curso = repository.save(new Curso(evento));
        return new CursoDTO(curso.getId(), curso.getNome(), curso.getDataInicio());
    }

    public CursoDTO atualizar(Long id, CursoDTO evento) {
        Curso curso = new Curso(evento);
        curso.setId(id);
        Curso cursoBd = repository.save(curso);
        return new CursoDTO(cursoBd.getId(), cursoBd.getNome(), cursoBd.getDataInicio());
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

}
