package com.example.backend.service;

import com.example.backend.domain.Empleado;
import com.example.backend.domain.HistorialLabor;
import com.example.backend.domain.UserRole;
import com.example.backend.dto.ComentarioRequestDTO;
import com.example.backend.dto.CrearEmpleadoRequestDTO;
import com.example.backend.dto.CrearEmpleadoResponseDTO;
import com.example.backend.dto.EmpleadoDTO;
import com.example.backend.dto.HistorialLaborDTO;
import com.example.backend.repository.EmpleadoRepository;
import com.example.backend.repository.HistorialLaborRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final HistorialLaborRepository historialLaborRepository;
    private final PasswordEncoder passwordEncoder;

    public EmpleadoService(EmpleadoRepository empleadoRepository, HistorialLaborRepository historialLaborRepository, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.historialLaborRepository = historialLaborRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<EmpleadoDTO> listar() {
        return empleadoRepository.findAll().stream()
                .map(MapperService::toEmpleadoDto)
                .collect(Collectors.toList());
    }

    public CrearEmpleadoResponseDTO crear(CrearEmpleadoRequestDTO request) {
        UserRole rol = UserRole.valueOf(request.getRol());
        String rawPassword = request.getPassword();
        if (rawPassword == null || rawPassword.isBlank()) {
            // Mantiene compatibilidad: si no envían password usamos el RUT como valor por defecto
            rawPassword = request.getRut();
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Empleado empleado = new Empleado(
                request.getNombre(),
                request.getRut(),
                request.getTelefono(),
                request.getCorreo(),
                encodedPassword,
                rol
        );
        return MapperService.toCrearEmpleadoResponseDto(empleadoRepository.save(empleado));
    }

    public List<HistorialLaborDTO> historial(Long empleadoId) {
        if (!empleadoRepository.existsById(empleadoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
        }
        return historialLaborRepository.findByEmpleado_Id(empleadoId).stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public void agregarComentario(Long empleadoId, ComentarioRequestDTO request) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));
        HistorialLabor comentario = new HistorialLabor(0L, LocalDate.now(), 0, request.getComentario(), empleado);
        historialLaborRepository.save(comentario);
    }

    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
        }
        empleadoRepository.deleteById(id);
    }

    public void actualizar(Long id, EmpleadoDTO empleadoDTO) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));

        empleadoExistente.setNombre(empleadoDTO.getNombre());
        empleadoExistente.setRut(empleadoDTO.getRut());
        empleadoExistente.setTelefono(empleadoDTO.getTelefono());
        empleadoExistente.setCorreo(empleadoDTO.getCorreo());
        if (empleadoDTO.getRol() != null) {
            try {
                empleadoExistente.setRol(UserRole.valueOf(empleadoDTO.getRol()));
            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no valido");
            }
        }

        empleadoRepository.save(empleadoExistente);
    }

}
