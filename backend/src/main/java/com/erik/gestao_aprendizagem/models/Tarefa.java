package com.erik.gestao_aprendizagem.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Tarefa implements Serializable {

    @EmbeddedId
    private TarefaPk id;

    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime tempoGasto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria", referencedColumnName = "id", insertable = false, updatable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCurso", referencedColumnName = "id", insertable = false, updatable = false)
    private Curso curso;

}
