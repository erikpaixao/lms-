package com.erik.gestao_aprendizagem.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erik.gestao_aprendizagem.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
