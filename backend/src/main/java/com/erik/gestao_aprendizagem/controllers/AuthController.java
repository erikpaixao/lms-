package com.erik.gestao_aprendizagem.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erik.gestao_aprendizagem.config.security.JwtTokenProvider;
import com.erik.gestao_aprendizagem.dtos.LoginDTO;
import com.erik.gestao_aprendizagem.dtos.RegistroUsuarioDTO;
import com.erik.gestao_aprendizagem.dtos.ResponseTokenDTO;
import com.erik.gestao_aprendizagem.models.Usuario;
import com.erik.gestao_aprendizagem.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UsuarioService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseTokenDTO> authenticateUser(@RequestBody LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        Usuario user = userService.buscarPorEmail(loginDto.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new ResponseTokenDTO(token, user.getId().toString(),
                user.getPermissoes().stream().map(p -> p.getNome()).collect(Collectors.toSet())));
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registerUser(@RequestBody RegistroUsuarioDTO registerDto) {
        try {
            userService.registrarNovo(registerDto);
            return new ResponseEntity<>("Usuário registrado com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
