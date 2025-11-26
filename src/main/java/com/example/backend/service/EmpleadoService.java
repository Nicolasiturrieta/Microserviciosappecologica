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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final HistorialLaborRepository historialLaborRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository, HistorialLaborRepository historialLaborRepository) {
        this.empleadoRepository = empleadoRepository;
        this.historialLaborRepository = historialLaborRepository;
    }

    public List<EmpleadoDTO> listar() {
        return empleadoRepository.findAll().stream()
                .map(MapperService::toEmpleadoDto)
                .collect(Collectors.toList());
    }

    public CrearEmpleadoResponseDTO crear(CrearEmpleadoRequestDTO request) {
        UserRole rol = UserRole.valueOf(request.getRol());
        Empleado empleado = new Empleado(
                request.getNombre(),
                request.getRut(),
                request.getTelefono(),
                request.getCorreo(),
                "demo",
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
}
