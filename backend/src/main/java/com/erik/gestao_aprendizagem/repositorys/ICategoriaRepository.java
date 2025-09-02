package com.erik.gestao_aprendizagem.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erik.gestao_aprendizagem.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
