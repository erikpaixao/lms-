package com.erik.gestao_aprendizagem.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncrementarTarefaDTO {

    private Long idCurso;

    private Long idUsuario;

    private Long idCategoria;

    private LocalDateTime dataTarefa;

}
