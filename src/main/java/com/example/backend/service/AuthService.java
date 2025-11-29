package com.example.backend.service;

import com.example.backend.domain.Empleado;
import com.example.backend.dto.EmpleadoDTO;
import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(EmpleadoRepository empleadoRepository, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        if (requestDTO.getRut() == null || requestDTO.getRut().isBlank()) {
            logger.warn("Login rejected: empty RUT");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RUT es requerido");
        }
        if (requestDTO.getPassword() == null || requestDTO.getPassword().isBlank()) {
            logger.warn("Login rejected for RUT {}: empty password", requestDTO.getRut());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password es requerido");
        }

        Empleado empleado = empleadoRepository.findByRut(requestDTO.getRut())
                .filter(e -> passwordMatches(e.getPassword(), requestDTO.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas"));

        EmpleadoDTO empleadoDTO = MapperService.toEmpleadoDto(empleado);
        return new LoginResponseDTO(empleadoDTO, "");
    }

    private boolean passwordMatches(String storedPassword, String rawPassword) {
        if (storedPassword == null || rawPassword == null) {
            return false;
        }
        boolean isEncoded = storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$");
        if (isEncoded) {
            return passwordEncoder.matches(rawPassword, storedPassword);
        }
        // Compatibilidad con contraseñas antiguas guardadas en texto plano
        return storedPassword.equals(rawPassword);
    }
}
