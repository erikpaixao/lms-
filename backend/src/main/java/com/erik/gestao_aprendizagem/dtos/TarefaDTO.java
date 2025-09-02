package com.erik.gestao_aprendizagem.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TarefaDTO {

    private String descricao;
    private String categoria;
    private LocalDateTime tempoGasto;
    private String curso;

    public TarefaDTO(String descricao, String categoria, LocalDateTime tempoGasto, String curso) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.tempoGasto = tempoGasto;
        this.curso = curso;
    }

}
