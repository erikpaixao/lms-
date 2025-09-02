package com.erik.gestao_aprendizagem.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erik.gestao_aprendizagem.dtos.TarefaDTO;
import com.erik.gestao_aprendizagem.models.Tarefa;
import com.erik.gestao_aprendizagem.models.TarefaPk;

public interface ITarefaRepository extends JpaRepository<Tarefa, TarefaPk> {

    @Query("SELECT new com.erik.gestao_aprendizagem.dtos.TarefaDTO(e.descricao, e.categoria.nome, e.tempoGasto, e.curso.nome) FROM Tarefa e  WHERE e.id.idUsuario = :id")
    public Page<TarefaDTO> buscarTodosPorIdUsuario(@Param("id") Long id, Pageable page);

}
