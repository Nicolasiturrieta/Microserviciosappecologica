package com.example.backend.controller;

import com.example.backend.dto.CrearRutaRequestDTO;
import com.example.backend.dto.RutaDTO;
import com.example.backend.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @GetMapping("/chofer/{choferId}")
    public List<RutaDTO> rutasParaChofer(@PathVariable Long choferId) {
        return rutaService.rutasParaChofer(choferId);
    }

    @GetMapping("/{rutaId}")
    public RutaDTO rutaPorId(@PathVariable Long rutaId) {
        return rutaService.buscarPorId(rutaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RutaDTO crear(@RequestBody @Valid CrearRutaRequestDTO request) {
        return rutaService.crear(request);
    }

    @PutMapping("/{id}")
    public RutaDTO actualizar(@PathVariable Long id, @RequestBody @Valid CrearRutaRequestDTO request) {
        return rutaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        rutaService.eliminar(id);
    }
}
