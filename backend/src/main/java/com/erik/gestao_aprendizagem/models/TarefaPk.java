package com.erik.gestao_aprendizagem.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class TarefaPk {

    @Column(name = "idCurso")
    private Long idCurso;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "idCategoria")
    private Long idCategoria;

    @Column(name = "dataTarefa")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataTarefa;

}
