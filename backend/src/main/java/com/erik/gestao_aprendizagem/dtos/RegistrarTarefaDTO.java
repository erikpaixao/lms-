package com.erik.gestao_aprendizagem.dtos;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class RegistrarTarefaDTO {

    @NonNull
    private Long idUsuario;
    @NonNull
    private Long idCurso;
    @NonNull
    private Long idCategoria;
    @NonNull
    private String descricao;

}
