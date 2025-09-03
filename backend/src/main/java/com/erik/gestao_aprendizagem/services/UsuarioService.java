package com.erik.gestao_aprendizagem.services;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erik.gestao_aprendizagem.dtos.RegistroUsuarioDTO;
import com.erik.gestao_aprendizagem.enums.TipoPermissao;
import com.erik.gestao_aprendizagem.models.Permissao;
import com.erik.gestao_aprendizagem.models.Usuario;
import com.erik.gestao_aprendizagem.repositorys.IPermissaoRepository;
import com.erik.gestao_aprendizagem.repositorys.IUsuarioRepository;

@Service
public class UsuarioService {

    private final IUsuarioRepository repository;
    private final IPermissaoRepository permissaoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository repository, PasswordEncoder passwordEncoder,
            IPermissaoRepository permissaoRepository) {
        this.repository = repository;
        this.permissaoRepository = permissaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarNovo(RegistroUsuarioDTO registration) {
        if (repository.findByEmail(registration.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (registration.getDataNascimento().isAfter(LocalDate.now().minusYears(16))) {
            throw new IllegalArgumentException("Usuário deve ter ao menos 16 anos.");
        }

        Permissao estudante = permissaoRepository.findByNome(TipoPermissao.ESTUDANTE)
                .orElseThrow(() -> new RuntimeException("Erro: Papel de ESTUDANTE não encontrado."));

        return repository.save(new Usuario(registration, estudante, passwordEncoder.encode(registration.getSenha())));
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o e-mail: " + email));
    }

}
