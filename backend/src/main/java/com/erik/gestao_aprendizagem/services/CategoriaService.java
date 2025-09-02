package com.erik.gestao_aprendizagem.services;

import org.springframework.stereotype.Service;

import com.erik.gestao_aprendizagem.models.Categoria;
import com.erik.gestao_aprendizagem.repositorys.ICategoriaRepository;

@Service
public class CategoriaService {

    private final ICategoriaRepository repository;

    public CategoriaService(ICategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
    }

}
