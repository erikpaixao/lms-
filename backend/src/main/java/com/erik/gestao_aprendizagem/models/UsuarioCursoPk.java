package com.erik.gestao_aprendizagem.models;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioCursoPk {

    @Column(name = "idCurso")
    private Long idCurso;
    
    @Column(name = "idUsuario")
    private Long idUsuario;

}
