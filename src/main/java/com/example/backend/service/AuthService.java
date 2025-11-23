package com.example.backend.service;

import com.example.backend.domain.Empleado;
import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.UsuarioDTO;
import com.example.backend.repository.EmpleadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final EmpleadoRepository empleadoRepository;

    public AuthService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public UsuarioDTO login(LoginRequestDTO requestDTO) {
        Empleado empleado = empleadoRepository.findByRut(requestDTO.getRut())
                .filter(e -> e.getPassword().equals(requestDTO.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        return MapperService.toDto(empleado);
    }
}
