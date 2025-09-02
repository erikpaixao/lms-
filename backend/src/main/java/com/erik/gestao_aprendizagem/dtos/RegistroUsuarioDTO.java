package com.erik.gestao_aprendizagem.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class RegistroUsuarioDTO {

    @NotBlank(message = "O primeiro nome é obrigatório.")
    private String primeiroNome;

    @NotBlank(message = "O último nome é obrigatório.")
    private String ultimoNome;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail deve ser válido.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

}
