package com.example.backend.controller;

import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        logger.info("📨 Received login request for RUT: {}", requestDTO.getRut());
        
        try {
            LoginResponseDTO response = authService.login(requestDTO);
            
            // Devolver ResponseEntity con el status code apropiado
            return ResponseEntity.status(response.getStatusCode()).body(response);
            
        } catch (Exception e) {
            logger.error("💥 Unexpected error in AuthController: {}", e.getMessage(), e);
            
            // Respuesta de error genérica
            LoginResponseDTO errorResponse = new LoginResponseDTO(
                null, 
                "Error interno del servidor", 
                false, 
                500
            );
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}