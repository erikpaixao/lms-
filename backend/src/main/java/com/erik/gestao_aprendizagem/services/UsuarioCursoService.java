package com.erik.gestao_aprendizagem.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.models.UsuarioCurso;
import com.erik.gestao_aprendizagem.models.UsuarioCursoPk;
import com.erik.gestao_aprendizagem.repositorys.IUsuarioCursoRepository;

@Service
public class UsuarioCursoService {

    private final IUsuarioCursoRepository repository;

    public UsuarioCursoService(IUsuarioCursoRepository repository) {
        this.repository = repository;
    }

    public Page<CursoDTO> buscarTodos(int page, int count, Long usuarioId) {
        return repository.buscarTodosPorIdUsuario(usuarioId, PageRequest.of(page, count));
    }

    public void matricular(Long cursoId, Long usuarioId) {

        if (repository.countByIdUsuario(usuarioId) > 3) {
            throw new IllegalArgumentException("Usuário já atingiu o limite de 3 cursos.");
        }

        UsuarioCurso usuarioCurso = new UsuarioCurso();

        UsuarioCursoPk usuarioCursoPk = new UsuarioCursoPk();
        usuarioCursoPk.setIdCurso(cursoId);
        usuarioCursoPk.setIdUsuario(usuarioId);

        usuarioCurso.setId(usuarioCursoPk);

        repository.save(usuarioCurso);
    }

}
