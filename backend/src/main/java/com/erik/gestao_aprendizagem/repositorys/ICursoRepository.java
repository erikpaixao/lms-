package com.erik.gestao_aprendizagem.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erik.gestao_aprendizagem.dtos.CursoDTO;
import com.erik.gestao_aprendizagem.models.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT new com.erik.gestao_aprendizagem.dtos.CursoDTO(e.id, e.nome, e.dataInicio) FROM Curso e WHERE e.id = :id")
    public CursoDTO buscarPorId(@Param("id") Long id);

    @Query("SELECT new com.erik.gestao_aprendizagem.dtos.CursoDTO(e.id, e.nome, e.dataInicio) FROM Curso e")
    public Page<CursoDTO> buscarTodosAtivos(Pageable page);

}
