package com.erik.gestao_aprendizagem.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario_curso")
public class UsuarioCurso {

    @EmbeddedId
    private UsuarioCursoPk id;

    @ManyToOne
    @JoinColumn(name = "idCurso", referencedColumnName = "id", insertable = false, updatable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

}
