package com.erik.gestao_aprendizagem.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erik.gestao_aprendizagem.enums.TipoPermissao;
import com.erik.gestao_aprendizagem.models.Permissao;

public interface IPermissaoRepository extends JpaRepository<Permissao, Long> {

    Optional<Permissao> findByNome(TipoPermissao tipo);

}
