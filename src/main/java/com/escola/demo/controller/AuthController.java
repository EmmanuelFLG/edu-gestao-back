package com.escola.demo.controller;

import com.escola.demo.model.Role;
import com.escola.demo.model.Usuario;
import com.escola.demo.repository.UsuarioRepository;
import com.escola.demo.service.JwtService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest credentials) {

        Optional<Usuario> usuarioOpt = usuarioRepo.findByEmail(credentials.getEmail());

        if (usuarioOpt.isEmpty() ||
            !passwordEncoder.matches(credentials.getPassword(), usuarioOpt.get().getSenha())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciais incorretas"));
        }

        Usuario usuario = usuarioOpt.get();
        String token = jwtService.gerarToken(usuario.getEmail());

        List<String> roles = usuario.getRoles().stream()
                .map(Role::getNome)
                .collect(Collectors.toList());

        Map<String, Object> usuarioMap = Map.of(
                "id", usuario.getId(),
                "nome", usuario.getNome(),
                "email", usuario.getEmail(),
                "roles", roles
        );

        return ResponseEntity.ok(
                Map.of(
                        "token", token,
                        "usuario", usuarioMap
                )
        );
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
