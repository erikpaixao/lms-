package com.erik.gestao_aprendizagem.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.models.UsuarioCurso;
import com.erik.gestao_aprendizagem.models.UsuarioCursoPk;

public interface IUsuarioCursoRepository extends JpaRepository<UsuarioCurso, UsuarioCursoPk> {

    @Query("SELECT new com.erik.gestao_aprendizagem.dtos.CursoDTO(e.id, e.nome, e.dataInicio) FROM Curso e JOIN UsuarioCurso uc on uc.id.idCurso = e.id WHERE uc.id.idUsuario = :id")
    public Page<CursoDTO> buscarTodosPorIdUsuario(@Param("id") Long id, Pageable page);

    @Query("SELECT count(*) FROM UsuarioCurso uc WHERE uc.id.idUsuario = :id")
    public long countByIdUsuario(Long id);

    @Query("SELECT count(*) FROM UsuarioCurso uc WHERE uc.id.idUsuario = :id AND uc.id.idCurso = :cursoId")
    public long countByIdUsuarioAndIdCurso(@Param("id") Long usuarioId, @Param("cursoId") Long cursoId);

}
