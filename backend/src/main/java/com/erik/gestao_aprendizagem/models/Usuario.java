package com.erik.gestao_aprendizagem.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.erik.gestao_aprendizagem.dtos.RegistroUsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O primeiro nome é obrigatório.")
    @Column(nullable = false)
    private String primeiroNome;

    @NotBlank(message = "O último nome é obrigatório.")
    @Column(nullable = false)
    private String ultimoNome;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioPermissao", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idPermissao"))
    private Set<Permissao> permissoes;

    public Usuario(RegistroUsuarioDTO dto, Permissao permissao, String senha) {
        this.primeiroNome = dto.getPrimeiroNome();
        this.ultimoNome = dto.getUltimoNome();
        this.dataNascimento = dto.getDataNascimento();
        this.email = dto.getEmail();
        this.telefone = dto.getTelefone();
        this.senha = senha;
        this.permissoes = Set.of(permissao);
    }

}
