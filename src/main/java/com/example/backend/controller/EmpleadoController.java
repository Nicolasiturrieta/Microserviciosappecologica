package com.example.backend.controller;

import com.example.backend.dto.ComentarioRequestDTO;
import com.example.backend.dto.CrearEmpleadoRequestDTO;
import com.example.backend.dto.CrearEmpleadoResponseDTO;
import com.example.backend.dto.EmpleadoDTO;
import com.example.backend.dto.HistorialLaborDTO;
import com.example.backend.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<EmpleadoDTO> listar() {
        return empleadoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CrearEmpleadoResponseDTO crear(@RequestBody @Valid CrearEmpleadoRequestDTO request) {
        return empleadoService.crear(request);
    }

    @GetMapping("/{id}/historial")
    public List<HistorialLaborDTO> historial(@PathVariable Long id) {
        return empleadoService.historial(id);
    }

    @PostMapping("/{id}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public void comentar(@PathVariable Long id, @RequestBody @Valid ComentarioRequestDTO request) {
        empleadoService.agregarComentario(id, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id, @RequestBody @Valid EmpleadoDTO empleadoDTO) {
        empleadoService.actualizar(id, empleadoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
