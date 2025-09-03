package com.erik.gestao_aprendizagem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erik.gestao_aprendizagem.models.Categoria;
import com.erik.gestao_aprendizagem.services.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categoria")
@Tag(name = "Categoria", description = "Gerenciamento de Aprendizagem")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @Operation(summary = "Listar todas as categorias paginados")
    @GetMapping("/all")
    public ResponseEntity<Page<Categoria>> getAll(
            @Parameter(description = "Número da página (iniciando em 0)") @RequestParam("page") int page,
            @Parameter(description = "Quantidade de registros por página") @RequestParam("count") int count) {
        return ResponseEntity.ok(service.buscarTodos(page, count));
    }
}
