package com.erik.gestao_aprendizagem.dtos;

import java.util.Set;

import com.erik.gestao_aprendizagem.enums.TipoPermissao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTokenDTO {

    private String token;
    private String userId;
    private Set<TipoPermissao> permissoes;

}
