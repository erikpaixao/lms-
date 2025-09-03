package com.erik.gestao_aprendizagem.dtos;

import java.time.LocalDateTime;

import com.erik.gestao_aprendizagem.models.Tarefa;

import lombok.Data;

@Data
public class TarefaDTO {

    private Long idCategoria;
    private LocalDateTime dataTarefa;
    private String descricao;
    private String categoria;
    private LocalDateTime tempoGasto;
    private String curso;

    public TarefaDTO(String descricao, String categoria, LocalDateTime tempoGasto, String curso, Long idCategoria,
            LocalDateTime dataTarefa) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.tempoGasto = tempoGasto;
        this.curso = curso;
        this.idCategoria = idCategoria;
        this.dataTarefa = dataTarefa;
    }

    public TarefaDTO(Tarefa tarefa) {
        this.idCategoria = tarefa.getId().getIdCategoria();
        this.dataTarefa = tarefa.getId().getDataTarefa();
        this.descricao = tarefa.getDescricao();
        this.tempoGasto = tarefa.getTempoGasto();
        this.categoria = tarefa.getCategoria().getNome();
        this.curso = tarefa.getCurso().getNome();
    }

}
